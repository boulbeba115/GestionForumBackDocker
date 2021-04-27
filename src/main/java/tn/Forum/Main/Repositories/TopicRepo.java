package tn.Forum.Main.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Forum.Main.Models.Topic;
import tn.Forum.Main.queryManaging.Topic.SimpleTopic;
import tn.Forum.Main.queryManaging.User.UserIdSolo;

public interface TopicRepo extends JpaRepository<Topic, Long>{

	List<SimpleTopic> findAllProjectedBy();

	Topic findByTopicId(String upperCase);

	UserIdSolo findFirstByOrderByIdDesc();
	
}
