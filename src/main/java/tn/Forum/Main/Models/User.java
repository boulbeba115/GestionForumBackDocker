package tn.Forum.Main.Models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

@Entity
public class User {

	public static enum AccountStatus {
		Active, Suspended, Banned
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(updatable = false, unique = true)
	private String userId;

	private String firstName;

	private String lastName;

	@NotBlank(message = "UserName Is Required")
	@Size(min = 5, message = "The Minimal Size of UserName is 5 Characters ")

	@Column(unique = true)
	private String userName;

	@NotBlank(message = "Email Address Is Required")
	@Email(message = "The Email Format is invalid")
	@Column(unique = true)
	private String userMail;

	@NotNull(message = "BirthDate Is Required")
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date birthDate;

	private Boolean isAnonym;

	@NotBlank(message = "User Password Is Required")
	private String userPassword;

	private AccountStatus accountStatus;

	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date suspensionStart;

	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date suspensionEnd;

	@JsonFormat(pattern = "yyyy-mm-dd")
	@Column(updatable = false)
	private Date created_At;

	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date updated_At;

	private String userPic;

	@Type(type = "text")
	private String abouMe;
	
	private String salt;
	/* Relations */
	@ManyToOne
	private Role userRole;

	@JsonBackReference(value = "postUser")
	@OneToMany
	private List<Post> postList;

	@JsonBackReference(value = "ref1")
	@OneToMany
	private List<User> followers;

	@JsonBackReference(value = "ref2")
	@OneToMany
	private List<User> following;

	@JsonBackReference(value = "ref3")
	@OneToMany
	private List<Reply> myReplies;

	@JsonBackReference(value = "ref4")
	@OneToMany(mappedBy = "userReport")
	private List<ReportPost> reportedPosts;

	@JsonBackReference(value = "ref5")
	@OneToMany(mappedBy = "userRepReport")
	private List<ReportReply> reportedReplies;
	
	@JsonBackReference(value="ref6")
	@OneToMany(mappedBy = "user")
	List<ReviewingPosts> likedpostes;
	
	@JsonBackReference(value="ref7")
	@OneToMany(mappedBy = "user")
	List<ReviewingReplies> likedReplies;
	
	/* Constructor + getters and Setters */
	public User() {
		super();
	}

	public User(String userId, String firstName, String lastName, String userName, String userMail, boolean isAnonym,
			String userPassword, AccountStatus accountStatus, Role userRole, Date birthDate) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.userMail = userMail;
		this.birthDate = birthDate;
		this.isAnonym = isAnonym;
		this.userPassword = userPassword;
		this.accountStatus = accountStatus;
		this.userRole = userRole;
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

	public String getFirstName() {
		if (!this.isAnonym)
			return firstName;
		return "";
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		if (!this.isAnonym)
			return lastName;
		return "";
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean getIsAnonym() {
		return isAnonym;
	}

	public void setIsAnonym(boolean isAnonym) {
		this.isAnonym = isAnonym;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}

	public Date getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Date getSuspensionStart() {
		return suspensionStart;
	}

	public void setSuspensionStart(Date suspensionStart) {
		this.suspensionStart = suspensionStart;
	}

	public Date getSuspensionEnd() {
		return suspensionEnd;
	}

	public void setSuspensionEnd(Date suspensionEnd) {
		this.suspensionEnd = suspensionEnd;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public List<Reply> getMyReplies() {
		return myReplies;
	}

	public void setMyReplies(List<Reply> myReplies) {
		this.myReplies = myReplies;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
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

	public void addPosts(Post p) {
		this.postList.add(p);
	}

	public List<ReportPost> getReportedPosts() {
		return reportedPosts;
	}

	public void setReportedPosts(List<ReportPost> reportedPosts) {
		this.reportedPosts = reportedPosts;
	}

	public List<ReportReply> getReportedReplies() {
		return reportedReplies;
	}

	public void setReportedReplies(List<ReportReply> reportedReplies) {
		this.reportedReplies = reportedReplies;
	}
	
	

	public List<ReviewingPosts> getLikedpostes() {
		return likedpostes;
	}

	public void setLikedpostes(List<ReviewingPosts> likedpostes) {
		this.likedpostes = likedpostes;
	}

	public void setIsAnonym(Boolean isAnonym) {
		this.isAnonym = isAnonym;
	}
	
	public List<ReviewingReplies> getLikedReplies() {
		return likedReplies;
	}

	public void setLikedReplies(List<ReviewingReplies> likedReplies) {
		this.likedReplies = likedReplies;
	}
	

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", userMail=" + userMail + ", birthDate=" + birthDate + ", isAnonym="
				+ isAnonym + ", userPassword=" + userPassword + ", abouMe=" + abouMe + "]";
	}

}
