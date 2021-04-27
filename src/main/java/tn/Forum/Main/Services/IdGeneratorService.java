package tn.Forum.Main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Forum.Main.Repositories.CategoryRepo;
import tn.Forum.Main.Repositories.PostRepo;
import tn.Forum.Main.Repositories.ReplyRepo;
import tn.Forum.Main.Repositories.TopicRepo;
import tn.Forum.Main.Repositories.UserRepo;
import tn.Forum.Main.queryManaging.User.UserIdSolo;

@Service
public class IdGeneratorService {

	@Autowired
	UserRepo userRepo;
	@Autowired
	CategoryRepo catRepo;
	@Autowired
	TopicRepo topicRepo;
	@Autowired
	PostRepo postRepo;
	@Autowired
	ReplyRepo replyRepo;
	
	public String userIdGenerator() {
		UserIdSolo u = userRepo.findFirstByOrderByIdDesc();
		long n= 0;
		if(u!=null)
			n=u.getId()+1;			
		return "USER_"+n;
	}
	public String categoryIdGenerator() {
		long catId=0;
		try {
			catId = catRepo.findFirstByOrderByIdDesc().getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long n= 0;
		n=catId+1;			
		return "CAT_"+n;
	}
	public String topicIdGenerator() {
		long topicId=0;
		try {
			topicId = topicRepo.findFirstByOrderByIdDesc().getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long n= 0;
		n=topicId+1;			
		return "TOPIC_"+n;
	}
	public String postIdGenerator() {
		long postId=0;
		try {
			postId = postRepo.findFirstByOrderByIdDesc().getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long n= 0;
		n=postId+1;			
		return "POST_"+n;
	}
	
	public String replyIdGenerator() {
		long replyId=0;
		try {
			replyId = replyRepo.findFirstByOrderByIdDesc().getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long n= 0;
		n=replyId+1;			
		return "REPLY_"+n;
	}
}
