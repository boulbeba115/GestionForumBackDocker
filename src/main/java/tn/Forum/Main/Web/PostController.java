package tn.Forum.Main.Web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.Forum.Main.Models.Post;
import tn.Forum.Main.Models.Reply;
import tn.Forum.Main.Models.ReportPost;
import tn.Forum.Main.Models.ReportReply;
import tn.Forum.Main.Models.ReviewingPosts;
import tn.Forum.Main.Models.ReviewingReplies;
import tn.Forum.Main.Models.Post.PostSatus;
import tn.Forum.Main.Services.MapValidationErrorService;
import tn.Forum.Main.Services.PostService;
import tn.Forum.Main.Services.ReplyService;
import tn.Forum.Main.queryManaging.Post.SimplePost;
import tn.Forum.Main.queryManaging.Replies.ReplyProtected;
import tn.Forum.Main.queryManaging.Reports.SimplifiedReplyRep;
import tn.Forum.Main.queryManaging.Reports.SimplifyPostReport;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostController {

	@Autowired
	PostService postService;
	@Autowired
	MapValidationErrorService mapValErrSev;
	@Autowired
	ReplyService replyService;

	// add a post
	@PostMapping("/{currentUser}")
	public ResponseEntity<?> createNewPost(@PathVariable String currentUser, @Valid @RequestBody Post post,
			BindingResult result) {
		ResponseEntity<?> errorMap = mapValErrSev.MapValidationService(result);
		if (errorMap != null)
			return errorMap;
		post.setPostSatus(PostSatus.Active);
		Post p = postService.AddPost(post, currentUser);
		return new ResponseEntity<Post>(p, HttpStatus.CREATED);
	}

	// get post By Post Identifier
	@GetMapping("/{postId}")
	public ResponseEntity<?> getPostByPostIdentifier(@PathVariable String postId) {
		Post p = postService.getPostByPostIdentifier(postId);
		return new ResponseEntity<Post>(p, HttpStatus.OK);
	}

	// get all posts
	@GetMapping("/all")
	public List<SimplePost> getAllPosts() {
		return postService.getAllPosts();
	}
	
	//changeStatus
	@PostMapping("/change/status")
	public boolean changeStatus(@RequestBody Post p) {
		return postService.changeStatus(p);
	}
	
	// physical delete
	@DeleteMapping("/permanent/{postId}/{currentUser}")
	public ResponseEntity<?> permDeletePost(@PathVariable String postId, @PathVariable String currentUser) {
		postService.deletePost(postId, currentUser);
		return new ResponseEntity<String>(
				"The with identifier : '" + postId.toUpperCase() + "' was permanently deleted", HttpStatus.OK);
	}

	// logic delete
	@DeleteMapping("/{postId}/currentUser")
	public ResponseEntity<?> logicDeletePost(@PathVariable String postId, @PathVariable String currentUser) {
		postService.HidePost(postId, currentUser);
		return new ResponseEntity<String>("The Post with identifier : '" + postId.toUpperCase() + "' was deleted",
				HttpStatus.OK);
	}

	@GetMapping("/getPostBy/{postId}")
	public ResponseEntity<?> getFullDetailPost(@PathVariable String postId) {
		SimplePost p = postService.getFullDetailsPost(postId);
		return new ResponseEntity<SimplePost>(p, HttpStatus.OK);
	}

	@PostMapping("/add/reply")
	public ResponseEntity<?> addReply(@RequestBody Reply reply) {
		Reply r = replyService.addReply(reply);
		return new ResponseEntity<Reply>(r, HttpStatus.CREATED);
	}

	@GetMapping("/byUser/{userId}")
	public ResponseEntity<?> getUserPosts(@PathVariable String userId) {
		List<SimplePost> posts = postService.getUserPosts(userId);
		return new ResponseEntity<List<SimplePost>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/all/Replies")
	public List<ReplyProtected> getAllReplies() {
		return postService.getAllReplies();
	}
	
	@PostMapping("report/post")
	public boolean reportPost(@RequestBody ReportPost rp) {
		return postService.reportPost(rp);
	}

	@GetMapping("/all/reports")
	public List<SimplifyPostReport> getAllReports() {
		return postService.getAllReports();
	}
	
	@PostMapping("report/reply")
	public boolean reportReply(@RequestBody ReportReply rp) {
		System.out.println(rp.getType());
		return postService.reportReply(rp);
	}

	@GetMapping("/all/reports/replies")
	public List<SimplifiedReplyRep> getAllRepliesReports() {
		return postService.getAllReportedReplies();
	}
	
	@PostMapping("/review")
	public int ReviewPost(@RequestBody ReviewingPosts rp) {
		return postService.reviewing(rp);
	}
	
	@PostMapping("/reply/review")
	public ReviewingReplies ReviewPost(@RequestBody ReviewingReplies rp) {
		return postService.reviewReplies(rp);
	}
	
	@GetMapping("/latest/posts")
	public List<SimplePost> getLastestPosts() {
		return postService.getLastestPosts();
	}
	
	@PostMapping("/views/{PostId}")
	public int incrementVues(@PathVariable String PostId) {
		return postService.incrementVues(PostId);
	}
}
