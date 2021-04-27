package tn.Forum.Main.Web;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.Forum.Main.Models.Topic;
import tn.Forum.Main.Services.MapValidationErrorService;
import tn.Forum.Main.Services.TopicService;
import tn.Forum.Main.configuration.FileUploadUtil;
import tn.Forum.Main.queryManaging.Topic.SimpleTopic;
import tn.Forum.Main.queryManaging.Topic.TopicDto;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin
public class TopicController {

	@Autowired
	TopicService topicService;
	@Autowired
	MapValidationErrorService mapValErrSev;

	// get topic By Identifier
	@GetMapping("/{topicId}")
	public ResponseEntity<?> getByTopicIdentifier(@PathVariable String topicId) {
		Topic t = topicService.getTopicByIdentifier(topicId);
		return new ResponseEntity<Topic>(t, HttpStatus.OK);
	}

	// get all topics
	@GetMapping("/all")
	public Iterable<Topic> getAlltopics() {
		return topicService.getAllTopics();
	}

	// get all topics projected
	@GetMapping("simple/all")
	public Iterable<SimpleTopic> getAllTopicSimple() {
		return topicService.getAllTopicSimple();
	}

	// physical delete
	@DeleteMapping("/permanent/{topicId}")
	public ResponseEntity<?> DeleteTopic(@PathVariable String topicId) {
		topicService.deleteTopic(topicId);
		return new ResponseEntity<String>(
				"The  Topic with identifier : '" + topicId.toUpperCase() + "' was permanently deleted", HttpStatus.OK);
	}

	// add with file Upload
	@PostMapping(value = "/img")
	public ResponseEntity<?> addneTopic(@RequestParam("file") MultipartFile[] file, @RequestParam("topic") String topic,
			@RequestParam("path") String path) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Topic formTopic = objectMapper.readValue(topic, Topic.class);
		try {
			if (file[0] == null)
				return new ResponseEntity<String>("You need to add a topic logo image", HttpStatus.ACCEPTED);
			else if (file[0] == null)
				return new ResponseEntity<String>("You need to add a topic cover image", HttpStatus.ACCEPTED);
			else {
				MultipartFile logo = file[0];
				MultipartFile cover = file[1];
				String logoName = "Topic_" + UUID.randomUUID().toString()
						+ StringUtils.cleanPath(logo.getOriginalFilename());
				String coverName = "Topic_" + UUID.randomUUID().toString()
						+ StringUtils.cleanPath(cover.getOriginalFilename());
				formTopic.setTopicLogo(logoName);
				formTopic.setTopicImage(coverName);
				String uploadDir = path;
				FileUploadUtil.saveFile(uploadDir, logoName, logo);
				FileUploadUtil.saveFile(uploadDir, coverName, cover);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		TopicDto t;
		if (formTopic.getId() == null || formTopic.getId() < 1) {
			System.out.println("add");
			t = topicService.AddTopic(formTopic);
		} else {
			System.out.println("edit");
			t = topicService.editTopic(formTopic);
		}
		return new ResponseEntity<TopicDto>(t, HttpStatus.ACCEPTED);
	}

}
