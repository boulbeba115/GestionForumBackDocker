package tn.Forum.Main.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ReportPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_user")
	User userReport;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_post")
	Post reportedPost;

	String cause;

	ReplyType type;

	public static enum ReplyType {
		Racism, Inappropriate_content, Violent_language, Beside_the_point, Others
	}

	public ReportPost() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserReport() {
		return userReport;
	}

	public void setUserReport(User userReport) {
		this.userReport = userReport;
	}

	public Post getReportedPost() {
		return reportedPost;
	}

	public void setReportedPost(Post reportedPost) {
		this.reportedPost = reportedPost;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public ReplyType getType() {
		return type;
	}

	public void setType(ReplyType type) {
		this.type = type;
	}

}
