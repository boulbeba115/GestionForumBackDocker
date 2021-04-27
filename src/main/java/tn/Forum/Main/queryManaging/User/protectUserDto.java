package tn.Forum.Main.queryManaging.User;

import java.util.Date;

public class protectUserDto {

	private long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String userMail;
	private Date birthDate;
	private Boolean isAnonym;
	private String userPic;
	private String abouMe;
	
	public protectUserDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Boolean getIsAnonym() {
		return isAnonym;
	}

	public void setIsAnonym(Boolean isAnonym) {
		this.isAnonym = isAnonym;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getAbouMe() {
		return abouMe;
	}

	public void setAbouMe(String abouMe) {
		this.abouMe = abouMe;
	}



}
