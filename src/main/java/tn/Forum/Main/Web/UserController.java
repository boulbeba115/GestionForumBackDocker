package tn.Forum.Main.Web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.Forum.Main.Models.Topic;
import tn.Forum.Main.Models.User;
import tn.Forum.Main.Models.User.AccountStatus;
import tn.Forum.Main.Repositories.UserRepo;
import tn.Forum.Main.Services.MapValidationErrorService;
import tn.Forum.Main.Services.TempLoginService;
import tn.Forum.Main.Services.UserService;
import tn.Forum.Main.configuration.FileUploadUtil;
import tn.Forum.Main.queryManaging.Topic.TopicDto;
import tn.Forum.Main.queryManaging.User.ProtectedUser;
import tn.Forum.Main.queryManaging.User.UserAcess;
import tn.Forum.Main.queryManaging.User.UserDto;
import tn.Forum.Main.queryManaging.User.protectUserDto;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	MapValidationErrorService mapValErrSev;
	@Autowired
	TempLoginService loginServ;

	DozerBeanMapper mapper = new DozerBeanMapper();

	@PostMapping("")
	public ResponseEntity<?> UserSingUp(@Valid @RequestBody User member, BindingResult result) {
		ResponseEntity<?> errorMap = mapValErrSev.MapValidationService(result);
		if (errorMap != null)
			return errorMap;
		User u = userService.memberSingUp(member);
		return new ResponseEntity<String>(u.getUserId(), HttpStatus.CREATED);
	}

	// get Member By Identifier
	@GetMapping("member/{memberId}")
	public ResponseEntity<?> getMemberByIdentifier(@PathVariable String memberId) {
		User m = userService.getMemberByIdentifier(memberId);
		return new ResponseEntity<User>(m, HttpStatus.OK);
	}

	// Get Moderator by identifier
	@GetMapping("moderator/{userId}")
	public ResponseEntity<?> getModeratorByIdentifier(@PathVariable String userId) {
		User m = userService.getModeratorByIdentifier(userId);
		return new ResponseEntity<User>(m, HttpStatus.OK);
	}

	// Ban User Account
	@GetMapping("/accountState/{memberId}/{currentid}")
	public User changeMemberStatus(@PathVariable String memberId, @PathVariable String currentid) {
		return userService.changeMemberStatus(memberId, currentid, User.AccountStatus.Banned);
	}

	// get all members
	@GetMapping("/members/all")
	public Iterable<User> getAllMembers() {
		return userService.getAllMembers();
	}

	// get all moderators
	@GetMapping("/moderators/all")
	public Iterable<User> getAllModerators() {
		return userService.getAllmoderators();
	}

	// get by user id protected
	@GetMapping("/by/identifier/{userId}")
	public ProtectedUser getAllUsers(@PathVariable String userId) {
		return userService.getUserProtected(userId);
	}

	@GetMapping("/allUsers")
	public List<ProtectedUser> getAllProtectedUsers() {
		return userService.getAllProtectedUsers();
	}

	// get all valid members
	@GetMapping("/members/activeAcount/all")
	public Iterable<User> getAllActiveAcount() {
		return userService.getAllActiveMembers();
	}

	// member to moderator
	@GetMapping("/promote/{memberId}/{currentid}")
	public ResponseEntity<?> membertoModerator(@PathVariable String memberId, @PathVariable String currentid) {
		User u = userService.MemberToModerator(memberId, currentid);
		if (u == null)
			return new ResponseEntity<String>("An error was happen during this operation",
					HttpStatus.EXPECTATION_FAILED);
		return new ResponseEntity<String>("User Promted Successfully", HttpStatus.OK);
	}

	// moderator to member
	@GetMapping("/demote/{memberId}/{currentid}")
	public ResponseEntity<?> moderatorToMember(@PathVariable String memberId, @PathVariable String currentid) {
		User u = userService.ModeratorToMember(memberId, currentid);
		if (u == null)
			return new ResponseEntity<String>("An error was happen during this operation",
					HttpStatus.EXPECTATION_FAILED);
		return new ResponseEntity<String>("User Demoted Successfully", HttpStatus.OK);
	}

	// change Status
	@PostMapping("/change/Status")
	public boolean changeUserStatus(@RequestBody User u) {
		return userService.changeUserStatus(u);
	}

	@PostMapping("/login")
	public ResponseEntity<?> UserLogin(@RequestBody User user) {
		UserAcess res = loginServ.login(user);
		if (res != null)
			return new ResponseEntity<UserAcess>(res, HttpStatus.OK);
		return new ResponseEntity<String>("Error Login Check the inputed credential", HttpStatus.FORBIDDEN);
	}

	@PostMapping("/edit/profile")
	public ResponseEntity<?> editProfil(@RequestBody UserDto user) {
		if (user.getOldpass().isEmpty()) {
			return new ResponseEntity<String>("The current Password is Required to change profil informations",
					HttpStatus.BAD_REQUEST);
		}
		User u = new User();
		u.setUserName(user.getUserName());
		u.setUserPassword(user.getOldpass());
		UserAcess res = loginServ.login(u);
		if (res == null)
			return new ResponseEntity<String>("Password does not  match", HttpStatus.BAD_REQUEST);
		u = mapper.map(user, User.class);
		if (!(user.getUserPassword() == null || user.getUserPassword().isEmpty())) {
			u.setUserPassword(user.getUserPassword());
		} else
			u.setUserPassword(user.getOldpass());
		u = userService.saveUser(u);
		protectUserDto protectedUser = mapper.map(u, protectUserDto.class);
		return new ResponseEntity<protectUserDto>(protectedUser, HttpStatus.OK);
	}

	@PostMapping(value = "/img/{userId}")
	public ResponseEntity<?> addneTopic(@RequestParam("file") MultipartFile[] file, @PathVariable String userId)
			throws IOException {
		User updateUser = new User();
		try {
			if (file[0] == null)
				return new ResponseEntity<String>("You need to add a topic logo image", HttpStatus.ACCEPTED);
			else {
				MultipartFile img = file[0];
				int i = (int) (new Date().getTime() / 1000);
				String imgName = "User_" + i + StringUtils.cleanPath(img.getOriginalFilename());
				String uploadDir = "C:\\Users\\boulbeba\\Desktop\\IHM\\forums\\src\\assets\\img\\users";
				FileUploadUtil.saveFile(uploadDir, imgName, img);
				User u = userService.getUserById(userId);
				u.setUserPic(imgName);
				updateUser = userService.saveUser(u);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		protectUserDto protectedUser = mapper.map(updateUser, protectUserDto.class);
		return new ResponseEntity<protectUserDto>(protectedUser, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/check/Status/{userId}")
	public ResponseEntity<?> checkAcountStatus(@PathVariable String userId) {
		UserAcess ua= userService.checkAcountStatus(userId);
		return new ResponseEntity<UserAcess>(ua, HttpStatus.ACCEPTED);
	}
}
