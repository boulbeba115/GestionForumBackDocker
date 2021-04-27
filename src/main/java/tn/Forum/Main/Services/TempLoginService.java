package tn.Forum.Main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Forum.Main.Models.User;
import tn.Forum.Main.Models.User.AccountStatus;
import tn.Forum.Main.Repositories.UserRepo;
import tn.Forum.Main.configuration.PasswordUtils;
import tn.Forum.Main.queryManaging.User.UserAcess;

@Service
public class TempLoginService {

	@Autowired
	UserRepo userRepo;

	public UserAcess login(User u) {
		UserAcess ua = new UserAcess();
		User user = userRepo.findByUserName(u.getUserName());
		if (user == null)
			return null;
        boolean passwordMatch = PasswordUtils.verifyUserPassword(u.getUserPassword(), user.getUserPassword(), user.getSalt());
		if (passwordMatch) {
			if (user.getAccountStatus() == AccountStatus.Banned) {
				ua.setBanned(true);
			} else
				ua.setBanned(false);
			ua.setRole(user.getUserRole().getUserRole());
			ua.setUserId(user.getUserId());
			return ua;
		} else
			return null;
	}
}
