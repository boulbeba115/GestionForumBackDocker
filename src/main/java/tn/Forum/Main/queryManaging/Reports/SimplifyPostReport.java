package tn.Forum.Main.queryManaging.Reports;

import tn.Forum.Main.Models.ReportPost.ReplyType;
import tn.Forum.Main.queryManaging.Post.SimplePost;
import tn.Forum.Main.queryManaging.User.ProtectedUser;

public interface SimplifyPostReport {
	Long getId();

	ProtectedUser getUserReport();

	String getCause();

	ReplyType getType();
	
	SimplePost getReportedPost();
}
