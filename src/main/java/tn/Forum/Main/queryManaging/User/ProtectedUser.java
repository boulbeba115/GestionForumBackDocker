package tn.Forum.Main.queryManaging.User;

import java.util.Date;
import java.util.List;

import tn.Forum.Main.Models.Role;
import tn.Forum.Main.Models.User;
import tn.Forum.Main.Models.User.AccountStatus;

public interface ProtectedUser {
	Long getId();

	String getUserId();

	String getUserName();

	String getFirstName();

	String getLastName();

	String getUserMail();

	String getAbouMe();

	String getUserPic();

	Date getBirthDate();

	Role getUserRole();

	Boolean getIsAnonym();

	AccountStatus getAccountStatus();

	List<User> getFollowers();

	List<User> getFollowing();

	int countposts();

}
