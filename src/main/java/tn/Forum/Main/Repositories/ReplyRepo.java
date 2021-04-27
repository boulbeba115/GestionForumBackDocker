package tn.Forum.Main.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Forum.Main.Models.Reply;
import tn.Forum.Main.queryManaging.Replies.ReplyProtected;
import tn.Forum.Main.queryManaging.User.UserIdSolo;

public interface ReplyRepo extends JpaRepository<Reply, Long>{

	UserIdSolo findFirstByOrderByIdDesc();

	List<ReplyProtected> findAllProjectedReplyBy();

}
