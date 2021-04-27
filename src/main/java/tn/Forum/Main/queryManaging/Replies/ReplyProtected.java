package tn.Forum.Main.queryManaging.Replies;

import java.util.Date;
import java.util.List;

import tn.Forum.Main.queryManaging.Reports.ProtectedReplyRep;
import tn.Forum.Main.queryManaging.Reviews.ProtectedReviewsReply;
import tn.Forum.Main.queryManaging.User.ProtectedUser;
import tn.Forum.Main.Models.Reply.ReplyStatus;

public interface ReplyProtected {

	Long getId();

	String getReplyId();

	String getReplyContent();

	ReplyStatus getReplyStatus();

	Date getCreated_At();

	Date getUpdated_At();

	ProtectedUser getUser();

	List<ProtectedReplyRep> getReports();

	List<ProtectedReviewsReply> getReviews();

}
