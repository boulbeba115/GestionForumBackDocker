package tn.Forum.Main.queryManaging.User;

import java.util.Date;
import tn.Forum.Main.Models.Role;
import tn.Forum.Main.Models.User.AccountStatus;

public interface SimpleUser {

	Long getId();
	String getUserId();
	String getUserName();
	String getUserMail();
	Date getUserBirthDate();
	boolean isAnonym();
	String getUserPic();
	String getUserPassword();
	Date getCreated_At();
	Date getUpdated_At();
	AccountStatus getAccountStatus();
	Date getSuspensionStart();
	Date getSuspensionEnd();
	Role getUserRole();

}
