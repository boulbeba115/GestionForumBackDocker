package tn.Forum.Main.queryManaging.Reports;

import tn.Forum.Main.Models.ReportReply.ReplyType;
import tn.Forum.Main.queryManaging.Replies.ReplyProtected;
import tn.Forum.Main.queryManaging.User.ProtectedUser;

public interface SimplifiedReplyRep {

	Long getId();

	ProtectedUser getUserRepReport();

	String getCause();

	ReplyType getType();
	
	ReplyProtected getReportedReply();
}
