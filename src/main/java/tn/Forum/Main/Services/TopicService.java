package tn.Forum.Main.Services;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Forum.Main.Exceptions.ForumException;
import tn.Forum.Main.Models.Category;
import tn.Forum.Main.Models.Topic;
import tn.Forum.Main.Repositories.CategoryRepo;
import tn.Forum.Main.Repositories.TopicRepo;
import tn.Forum.Main.queryManaging.Category.CategoryDto;
import tn.Forum.Main.queryManaging.Topic.SimpleTopic;
import tn.Forum.Main.queryManaging.Topic.TopicDto;

@Service
public class TopicService {

	@Autowired
	TopicRepo topicRepo;
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired 
	IdGeneratorService idGen;
	
	DozerBeanMapper mapper =new DozerBeanMapper();
	 
	// add Topic
	public TopicDto AddTopic(Topic t) {
		
		try {
			Category c = categoryRepo.findByCategoryId(t.getCategory().getCategoryId().toUpperCase());
			if (c == null)
				throw new ForumException("Category not found");
			
			if(t.getId()==null || !(t.getId()>0))
			t.setTopicId(idGen.topicIdGenerator());
			t.setCategory(c);
			Topic topic = topicRepo.save(t);
			c.addTopic(topic);
			categoryRepo.save(c);
			CategoryDto catDto = mapper.map(c, CategoryDto.class);
		    TopicDto topicDto = mapper.map(topic, TopicDto.class);
		    topicDto.setCategorydto(catDto);
			return topicDto;
		} catch (Exception e) {
			throw new ForumException(e.getMessage());
		}
	}
	public TopicDto editTopic(Topic t) {
		try {
			Topic oldTopic = topicRepo.findByTopicId(t.getTopicId().toUpperCase());
			Category c = categoryRepo.findByCategoryId(t.getCategory().getCategoryId().toUpperCase());
			if (c == null)
				throw new ForumException("Category not found");
			Topic topic;
			if(c.getCategoryId().toUpperCase()!=oldTopic.getCategory().getCategoryId().toUpperCase()) {
				Category oldCategory = oldTopic.getCategory();
				oldCategory.flush(oldTopic);
				t.setCategory(c);
				topic = topicRepo.save(t);
				c.addTopic(topic);
				categoryRepo.save(c);
			}
			else
			{
				t.setCategory(c);
				topic = topicRepo.save(t);
			}
			CategoryDto catDto = mapper.map(c, CategoryDto.class);
		    TopicDto topicDto = mapper.map(topic, TopicDto.class);
		    topicDto.setCategorydto(catDto);
			return topicDto;
		} catch (Exception e) {
			throw new ForumException(e.getMessage());
		}
	} 
	// get Topic By Identifier
	public Topic getTopicByIdentifier(String topicId) {
		Topic t = topicRepo.findByTopicId(topicId.toUpperCase());
		if (t == null)
			throw new ForumException("No Topic Match Found With Category Identifier :" + topicId.toUpperCase());
		return t;
	}

	// get all
	public Iterable<Topic> getAllTopics() {
		return topicRepo.findAll();
	}
	
	// get all simple
	public Iterable<SimpleTopic> getAllTopicSimple() {
		return topicRepo.findAllProjectedBy();
	}

	// Delete
	public void deleteTopic(String topicId) {
		Topic t = topicRepo.findByTopicId(topicId.toUpperCase());
		if (t == null || t.getPostList().size() > 0)
			throw new ForumException("This Topic Cannot Be Deleted");
		try {
			topicRepo.deleteById(t.getId());
		} catch (Exception e) {
			throw new ForumException("This Topic Cannot Be Deleted");
		}

	}
}
