package tn.Forum.Main.queryManaging.Reports;

import tn.Forum.Main.queryManaging.User.ProtectedUser;
import tn.Forum.Main.Models.ReportReply.ReplyType;

public interface ProtectedReplyRep {

	Long getId();

	ProtectedUser getUserRepReport();

	String getCause();

	ReplyType getType();

}
