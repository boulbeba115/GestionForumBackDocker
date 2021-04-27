package tn.Forum.Main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.Forum.Main.Models.Post;
import tn.Forum.Main.queryManaging.Post.SimplePost;
import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long>{

	Post findByPostId(String postIdentifer);
	
	List<SimplePost> findAllProjectedBy();
	
	SimplePost findProjectedByPostId(String postIdentifer);
	
	@Query("SELECT p FROM Post p WHERE p.postSatus != 4")
	List<Post> findAllPosts();

	Post findFirstByOrderByIdDesc();
	
	List<SimplePost> findProjectedByPostOwnerUserId(String userId);

	List<SimplePost> findFirst7ByOrderByIdDesc();

	List<SimplePost> findFirst25ByOrderByIdDesc();
	

}
