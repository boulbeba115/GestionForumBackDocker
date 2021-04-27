package tn.Forum.Main.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.status.Status;
import tn.Forum.Main.Exceptions.ForumException;
import tn.Forum.Main.Models.Post;
import tn.Forum.Main.Models.Post.PostSatus;
import tn.Forum.Main.Models.ReportPost;
import tn.Forum.Main.Models.ReportReply;
import tn.Forum.Main.Models.ReviewingPosts;
import tn.Forum.Main.Models.ReviewingReplies;
import tn.Forum.Main.Models.Topic;
import tn.Forum.Main.Models.User;
import tn.Forum.Main.Repositories.PostRepo;
import tn.Forum.Main.Repositories.ReplyRepo;
import tn.Forum.Main.Repositories.ReplyReportRepo;
import tn.Forum.Main.Repositories.ReportRepo;
import tn.Forum.Main.Repositories.ReviewPostRepo;
import tn.Forum.Main.Repositories.ReviewReplyRepo;
import tn.Forum.Main.Repositories.TopicRepo;
import tn.Forum.Main.Repositories.UserRepo;
import tn.Forum.Main.queryManaging.Post.SimplePost;
import tn.Forum.Main.queryManaging.Replies.ReplyProtected;
import tn.Forum.Main.queryManaging.Reports.SimplifiedReplyRep;
import tn.Forum.Main.queryManaging.Reports.SimplifyPostReport;

@Service
public class PostService {

	@Autowired
	PostRepo postRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	TopicRepo topicRepo;
	@Autowired
	ReportRepo reportRepo;
	@Autowired
	ReplyReportRepo replyReportRepo;
	@Autowired
	ReviewPostRepo reviewPostRepo;
	@Autowired
	ReviewReplyRepo reviewReplyRepo;
	@Autowired 
	ReplyRepo replyRepo;
	@Autowired
	IdGeneratorService idGeneratorService;

	// add a post
	public Post AddPost(Post p, String currentUser) {
		System.out.println("currentUser");
		User u = userRepo.findByUserId(currentUser.toUpperCase());
		if (u == null)
			throw new ForumException("You are not allowed to add posts");
		Topic t = topicRepo.findByTopicId(p.getPostTopic().getTopicId().toUpperCase());
		if (t == null)
			throw new ForumException("failed to add this post,Try again");
		try {
			p.setPostId(idGeneratorService.postIdGenerator());
			p.setPostTopic(t);
			p.setPostOwner(u);
			Post post = postRepo.save(p);
			t.addPosts(post);
			u.addPosts(post);
			topicRepo.save(t);
			userRepo.save(u);
			return post;
		} catch (Exception e) {
			throw new ForumException(e.getMessage());
		}
	}

	// get post By Project Identifier
	public Post getPostByPostIdentifier(String postIdentifer) {
		Post p = postRepo.findByPostId(postIdentifer.toUpperCase());
		if (p == null)
			throw new ForumException("No Post Match Found With Post Identifier :" + postIdentifer.toUpperCase());
		return p;
	}

	// get all
	public List<SimplePost> getAllPosts() {
		return postRepo.findAllProjectedBy();
	}

	// changeStatus
	public boolean changeStatus(Post p) {
		Post pf = postRepo.findByPostId(p.getPostId());
		pf.setPostSatus(p.getPostSatus());
		pf = postRepo.save(pf);
		if (pf != null)
			return true;
		return false;
	}

	// physical delete
	public void deletePost(String postId, String currentUser) {
		/* TODO */
		/* Add Spring Sec or manage access */
		User u = userRepo.findByUserId(currentUser.toUpperCase());
		if (u == null)
			throw new ForumException("You are not allowed to delete posts");
		Post post = postRepo.findByPostId(postId.toUpperCase());
		if (post == null)
			throw new ForumException("This Post Is Not Avaible To Delete");
		try {
			postRepo.deleteById(post.getId());
		} catch (Exception e) {
			throw new ForumException("This Post Is Not Avaible To Delete");
		}

	}

	// logic delete
	public void HidePost(String postId, String currentUser) {
		/* TODO */
		/* Add Spring Sec or manage access */
		User u = userRepo.findByUserId(currentUser.toUpperCase());
		if (u == null)
			throw new ForumException("You are not allowed to delete posts");
		Post post = postRepo.findByPostId(postId.toUpperCase());
		if (post == null || post.getPostSatus() == PostSatus.Hidden)
			throw new ForumException("This Post Is Not Avaible To Delete");
		else {
			post.setPostSatus(PostSatus.Hidden);
			this.AddPost(post, currentUser);
		}

	}

	public SimplePost getFullDetailsPost(String postId) {
		return postRepo.findProjectedByPostId(postId);
	}

	public List<SimplePost> getUserPosts(String userId) {
		return postRepo.findProjectedByPostOwnerUserId(userId);
	}

	public List<SimplePost> getLastestPosts() {
		return postRepo.findFirst7ByOrderByIdDesc();
	}

	public boolean reportPost(ReportPost rp) {
		User u = userRepo.findByUserId(rp.getUserReport().getUserId());
		if (u == null)
			throw new ForumException("You are not allowed to do this action");
		// check if user already report the post
		ReportPost checkRp = reportRepo.findByUserReportUserIdAndReportedPostPostId(rp.getUserReport().getUserId(),
				rp.getReportedPost().getPostId());
		if (checkRp != null)
			throw new ForumException("You already report this post");
		rp.setUserReport(u);
		// save the Report
		ReportPost newRp = reportRepo.save(rp);
		if (newRp != null)
			return true;
		return false;
	}

	public boolean reportReply(ReportReply rportReply) {
		User u = userRepo.findByUserId(rportReply.getUserRepReport().getUserId());
		if (u == null)
			throw new ForumException("You are not allowed to do this action");
		// check if user already report the post

		ReportReply checkRp = replyReportRepo.findByUserRepReportUserIdAndReportedReplyReplyId(
				rportReply.getUserRepReport().getUserId(), rportReply.getReportedReply().getReplyId());
		if (checkRp != null)
			throw new ForumException("You already report this post");

		rportReply.setUserRepReport(u);
		// save the Report
		ReportReply newRp = replyReportRepo.save(rportReply);
		if (newRp != null)
			return true;
		return false;
	}

	public List<SimplifyPostReport> getAllReports() {
		return reportRepo.findAllProjectedBy();
	}

	public List<SimplifiedReplyRep> getAllReportedReplies() {
		return replyReportRepo.findAllProjectedBy();
	}

	// likes & dislikes
	public int reviewing(ReviewingPosts reviewPost) {
		User u = userRepo.findByUserId(reviewPost.getUser().getUserId());
		if (u == null)
			throw new ForumException("You are not allowed to do this action");
		ReviewingPosts checkRev = reviewPostRepo.findByUserUserIdAndPostPostId(reviewPost.getUser().getUserId(),
				reviewPost.getPost().getPostId());
		if (checkRev != null) {
			if (checkRev.isLiked() && reviewPost.isLiked() || checkRev.isDislike() && reviewPost.isDislike())
				return 0;
			if (reviewPost.isLiked()) {
				checkRev.likePost();
				reviewPostRepo.save(checkRev);
				return 1;
			} else {
				checkRev.dislike();
				reviewPostRepo.save(checkRev);
				return 2;
			}
		} else {
			reviewPost.setUser(u);
			if (reviewPost.isLiked()) {
				reviewPostRepo.save(reviewPost);
				return 3;
			} else {
				reviewPostRepo.save(reviewPost);
				return 4;
			}
		}
	}

	public ReviewingReplies reviewReplies(ReviewingReplies reviewreply) {
		User u = userRepo.findByUserId(reviewreply.getUser().getUserId());
		if (u == null)
			throw new ForumException("You are not allowed to do this action");
		ReviewingReplies checkRev = reviewReplyRepo.findByUserUserIdAndReplyReplyId(reviewreply.getUser().getUserId(),
				reviewreply.getReply().getReplyId());
		if (checkRev != null) {
			if (checkRev.isLiked() && reviewreply.isLiked() || checkRev.isDislike() && reviewreply.isDislike())
				return null;
			if (reviewreply.isLiked())
				checkRev.likePost();
			else
				checkRev.dislike();
			return reviewReplyRepo.save(checkRev);

		} else {
			reviewreply.setUser(u);
			return reviewReplyRepo.save(reviewreply);
		}
	}

	public List<ReplyProtected> getAllReplies() {
		return replyRepo.findAllProjectedReplyBy();
	}
	
	public int incrementVues(String PostId) {
		Post p = postRepo.findByPostId(PostId);
		if (p==null)
			throw new ForumException("Not A valid Post");
		p.incViews();
		postRepo.save(p);
		return p.getViews();
	}

}
