package tn.Forum.Main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Forum.Main.Models.ReviewingPosts;

public interface ReviewPostRepo extends JpaRepository<ReviewingPosts, Long>{

	ReviewingPosts findByUserUserIdAndPostPostId(String userId, String postId);

}
