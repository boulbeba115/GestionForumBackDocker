package tn.Forum.Main.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(updatable = false, unique = true)
	private String postId;

	@NotBlank(message = "The Post Title Is Required")
	private String postTitle;

	@NotBlank(message = "The Post Content Is Required")
	@Type(type = "text")
	private String postContent;

	private PostSatus postSatus;

	private Boolean isAnnouncment = false;
	
	private int views=0;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(updatable = false)
	private Date createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	public static enum PostSatus {
		Active, Closed, Archived, Hidden , Pinned
	}

	/* Relations */

	@JsonBackReference
	@ManyToOne
	private Topic postTopic;

	@ManyToOne
	private User postOwner;

	@OneToMany
	private List<Reply> replies;
	
	@OneToMany(mappedBy = "reportedPost")
	private List<ReportPost> reports;
	
	@OneToMany(mappedBy = "post")
	List<ReviewingPosts> usersReviews;
	
	/* Constructor,Getter And Setter */

	public Post() {
		super();
	}

	public void addReply(Reply r) {
		this.replies.add(r);
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public PostSatus getPostSatus() {
		return postSatus;
	}

	public void setPostSatus(PostSatus postSatus) {
		this.postSatus = postSatus;
	}

	public Topic getPostTopic() {
		return postTopic;
	}

	public void setPostTopic(Topic postTopic) {
		this.postTopic = postTopic;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getPostOwner() {
		return postOwner;
	}

	public void setPostOwner(User postOwner) {
		this.postOwner = postOwner;
	}

	public Boolean isAnnouncment() {
		return isAnnouncment;
	}

	public void setAnnouncment(Boolean isAnnouncment) {
		this.isAnnouncment = isAnnouncment;
	}
	
	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public Boolean getIsAnnouncment() {
		return isAnnouncment;
	}

	public void setIsAnnouncment(Boolean isAnnouncment) {
		this.isAnnouncment = isAnnouncment;
	}

	public List<ReportPost> getReports() {
		return reports;
	}

	public void setReports(List<ReportPost> reports) {
		this.reports = reports;
	}

	public List<ReviewingPosts> getUsersReviews() {
		return usersReviews;
	}

	public void setUsersReviews(List<ReviewingPosts> usersReviews) {
		this.usersReviews = usersReviews;
	}

	public void incViews() {
		this.views++;
	}

	
	
	
}
