package tn.Forum.Main.queryManaging.Reports;

import tn.Forum.Main.Models.ReportPost.ReplyType;
import tn.Forum.Main.queryManaging.User.ProtectedUser;

public interface ProtectedReport {

	Long getId();
	ProtectedUser getUserReport();
	String getCause();
	ReplyType getType();
	
}
