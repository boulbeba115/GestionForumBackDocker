package tn.Forum.Main.queryManaging.Post;

import java.util.Date;
import java.util.List;

import tn.Forum.Main.Models.Post.PostSatus;
import tn.Forum.Main.queryManaging.Replies.ReplyProtected;
import tn.Forum.Main.queryManaging.Reports.ProtectedReport;
import tn.Forum.Main.queryManaging.Reviews.ProtectedReviewingPost;
import tn.Forum.Main.queryManaging.Topic.SimpleTopic;
import tn.Forum.Main.queryManaging.User.ProtectedUser;

public interface SimplePost {
	
	Long getId();
	String getPostId();
	String getPostTitle();
	String getPostContent();
	PostSatus getPostSatus();
	Boolean getIsAnnouncment();
    List<ReplyProtected> getReplies();
	SimpleTopic getPostTopic();
	Date getCreatedAt();
	Date getUpdatedAt();
	ProtectedUser getPostOwner();
	int getViews();
	List<ProtectedReport> getReports();
	List<ProtectedReviewingPost> getUsersReviews();

}
