package tn.Forum.Main.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ReportReply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_user")
	User userRepReport;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_reply")
	Reply reportedReply;

	String cause;

	ReplyType type;

	public ReportReply() {
		super();
	}

	public static enum ReplyType {
		Racism, Inappropriate_content, Violent_language, Beside_the_point, Others
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserRepReport() {
		return userRepReport;
	}

	public void setUserRepReport(User userRepReport) {
		this.userRepReport = userRepReport;
	}

	public Reply getReportedReply() {
		return reportedReply;
	}

	public void setReportedReply(Reply reportedReply) {
		this.reportedReply = reportedReply;
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
