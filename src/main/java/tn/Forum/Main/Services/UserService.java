package tn.Forum.Main.Services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import tn.Forum.Main.Exceptions.ForumException;
import tn.Forum.Main.Models.Role;
import tn.Forum.Main.Models.User;
import tn.Forum.Main.Models.User.AccountStatus;
import tn.Forum.Main.Repositories.RoleRepo;
import tn.Forum.Main.Repositories.UserRepo;
import tn.Forum.Main.configuration.PasswordUtils;
import tn.Forum.Main.queryManaging.User.ProtectedUser;
import tn.Forum.Main.queryManaging.User.UserAcess;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;
	@Autowired
	IdGeneratorService idGenrator;

	// Member signUp

	public User memberSingUp(User u) {
		String salt = PasswordUtils.getSalt(30);
		String securePassword = PasswordUtils.generateSecurePassword(u.getUserPassword(), salt);
		u.setUserPassword(securePassword);
		u.setSalt(salt);
		u.setAccountStatus(User.AccountStatus.Active);
		Role r = this.getRoles("ROLE_MEMEBER");
		u.setUserRole(r);
		u.setUserId(idGenrator.userIdGenerator().toUpperCase());
		u.setUserPic("default.png");
		u.setAbouMe("");
		return userRepo.save(u);
	}

	// Get member by identifier
	public User getMemberByIdentifier(String userId) {
		User u = userRepo.findByUserId(userId.toUpperCase());
		if (u == null || u.getUserRole().getUserRole() != "ROLE_MEMEBER")
			throw new ForumException("No Member Found With The Identifier :" + userId.toUpperCase());
		return u;
	}

	// Get Moderator by identifier
	public User getModeratorByIdentifier(String userId) {
		User u = userRepo.findByUserId(userId.toUpperCase());
		if (u == null || u.getUserRole().getUserRole() != "ROLE_MODERATOR")
			throw new ForumException("No Moderator Found With The Identifier :" + userId.toUpperCase());
		return u;
	}

	// changeMemberStatus
	public User changeMemberStatus(String userId, String currentUserId, User.AccountStatus status) {
		User currentUser = userRepo.findByUserId(currentUserId.toUpperCase());
		if (currentUser == null)
			throw new ForumException("Acces denied");
		User u = userRepo.findByUserId(userId.toUpperCase());
		if (u == null)
			throw new ForumException("No Member Found With The Identifier :" + userId.toUpperCase());
		else if (currentUser.getUserRole().getUserRole() != "ROLE_MODERATOR")
			throw new ForumException("You dont hava acess to change this user status !");
		try {
			u.setAccountStatus(status);
			u = userRepo.save(u);
		} catch (Exception e) {
			throw new ForumException("can't change the state of this user\n error cause " + e.getMessage());
		}
		return u;
	}

	// get All members with all states
	public Iterable<User> getAllMembers() {
		return userRepo.findAllMembers();
	}

	// get all moderator
	public Iterable<User> getAllmoderators() {
		return userRepo.findAllModerators();
	}

	// get Members with only active state
	public Iterable<User> getAllActiveMembers() {
		List<User> listMembers = userRepo.findAllMembers();
		listMembers.removeIf(m -> m.getAccountStatus() == User.AccountStatus.Active);
		return listMembers;
	}

	// Member To Moderator
	public User MemberToModerator(String UserId, String currentUserId) {
		User currentUser = userRepo.findByUserId(currentUserId.toUpperCase());
		if (currentUser == null || !currentUser.getUserRole().getUserRole().equals("ROLE_ADMIN"))
			throw new ForumException("Acces denied");
		User u = userRepo.findByUserId(UserId.toUpperCase());
		if (u.getUserRole().getUserRole() == "ROLE_MODERATOR")
			throw new ForumException("This user is already a moderator !");
		try {
			Role r = this.getRoles("ROLE_MODERATOR");
			u.setUserRole(r);
			u = userRepo.save(u);
		} catch (Exception e) {
			throw new ForumException("Error While Changing User Role\n error cause " + e.getMessage());
		}
		return u;
	}

	// Moderator to Member
	public User ModeratorToMember(String UserId, String currentUserId) {
		User currentUser = userRepo.findByUserId(currentUserId.toUpperCase());
		if (currentUser == null || !currentUser.getUserRole().getUserRole().equals("ROLE_ADMIN"))
			throw new ForumException("Acces denied");
		User u = userRepo.findByUserId(UserId.toUpperCase());
		try {
			Role r = this.getRoles("ROLE_MEMEBER");
			u.setUserRole(r);
			u = userRepo.save(u);
		} catch (Exception e) {
			throw new ForumException("Error While Changing User Role\n error cause " + e.getMessage());
		}
		return u;
	}

	// getRoles
	public Role getRoles(String role) {
		return roleRepo.findFirstByUserRole(role);
	}

	public Iterable<User> getAllUsers() {
		return userRepo.findAll();
	}

	private static String getBetweenStrings(String text, String textFrom, String textTo) {
		String result = "";
		result = text.substring(text.indexOf(textFrom) + textFrom.length(), text.length());
		result = result.substring(0, result.indexOf(textTo));
		return result;
	}

	public static boolean isValidEmailAddress(String email) {
		Pattern pattern = Pattern.compile("^.+@.+\\..+$");
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}

	public List<ProtectedUser> getAllUsersProtected() {
		return userRepo.findAllProjectedBy();
	}

	public ProtectedUser getUserProtected(String userId) {
		return userRepo.findAllProjectedByUserId(userId);
	}

	public User getUserById(String userId) {
		return userRepo.findByUserId(userId);
	}

	public User saveUser(User u) {
		User user = userRepo.findByUserId(u.getUserId());
		u.setUserRole(user.getUserRole());
		return userRepo.save(u);

	}

	public List<ProtectedUser> getAllProtectedUsers() {
		return userRepo.findAllProjectedBy();
	}

	public boolean changeUserStatus(User u) {
		User user = userRepo.findByUserId(u.getUserId());
		if (user == null)
			return false;
		user.setAccountStatus(u.getAccountStatus());
		userRepo.save(user);
		return true;
	}

	public UserAcess checkAcountStatus(String userId) {
		UserAcess ua = new UserAcess();
		User u = userRepo.findByUserId(userId);
		if (u.getAccountStatus() == AccountStatus.Banned) {
			ua.setBanned(true);
			return ua;
		}
		ua.setRole(u.getUserRole().getUserRole());
		return ua;

	}

}
