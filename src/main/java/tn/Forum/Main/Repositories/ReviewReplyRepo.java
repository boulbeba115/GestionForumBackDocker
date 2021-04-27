package tn.Forum.Main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Forum.Main.Models.ReviewingReplies;

public interface ReviewReplyRepo extends JpaRepository<ReviewingReplies, Long>{

	ReviewingReplies findByUserUserIdAndReplyReplyId(String userId, String replyId);

}
