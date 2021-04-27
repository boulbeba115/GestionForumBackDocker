package tn.Forum.Main.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Forum.Main.Exceptions.ForumException;
import tn.Forum.Main.Models.Post;
import tn.Forum.Main.Models.Reply;
import tn.Forum.Main.Models.User;
import tn.Forum.Main.Repositories.PostRepo;
import tn.Forum.Main.Repositories.ReplyRepo;
import tn.Forum.Main.Repositories.UserRepo;

@Service
public class ReplyService {
	
	@Autowired
	ReplyRepo replyRepo;
	@Autowired
	PostRepo postRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	IdGeneratorService idGeneratorService;
	
	public Reply addReply(Reply r){
		Post p = postRepo.findByPostId(r.getPost().getPostId().toUpperCase());
		if(p == null)
			throw new ForumException("Sorry, This Reply Cannont be Added");
		User u = userRepo.findByUserId(r.getUser().getUserId().toUpperCase()); 
		if(u == null)
			throw new ForumException("Sorry, This Reply Cannont be Added");
		r.setReplyId((idGeneratorService.replyIdGenerator().toUpperCase()));
		r.setUser(u);
		Reply reply = replyRepo.save(r);
		p.addReply(r);
		postRepo.save(p);
		return reply;
		
	}
	
}
