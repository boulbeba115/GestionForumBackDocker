package tn.Forum.Main.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Reply Identifier Is Required")
	@Column(updatable = false, unique = true)
	private String replyId;

	@NotBlank(message = "The Reply Content Is Required")
	@Type(type = "text")
	private String replyContent;

	private ReplyStatus replyStatus = ReplyStatus.Active;

	public static enum ReplyStatus {
		Active, Closed, Archived, Hidden, Deleted
	}

	@JsonFormat(pattern = "yyyy-mm-dd")
	@Column(updatable = false)
	private Date created_At;

	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date updated_At;

	/* Relations */
	@JsonBackReference
	@ManyToOne
	private Post post;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "reportedReply")
	private List<ReportReply> reports;

	@OneToMany(mappedBy = "reply")
	private List<ReviewingReplies> reviews;

	/* Constructor,Getter And Setter */
	public Reply() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public ReplyStatus getReplyStatus() {
		return replyStatus;
	}

	public void setReplyStatus(ReplyStatus replyStatus) {
		this.replyStatus = replyStatus;
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ReportReply> getReports() {
		return reports;
	}

	public void setReports(List<ReportReply> reports) {
		this.reports = reports;
	}

	public List<ReviewingReplies> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewingReplies> reviews) {
		this.reviews = reviews;
	}

	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();
	}

}
