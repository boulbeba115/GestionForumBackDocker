package tn.Forum.Main.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Topic Identifier Is Required")
	@Column(updatable = false, unique = true)
	private String topicId;

	@NotBlank(message = "Topic Identifier Is Required")
	private String topicTitle;

	@NotBlank(message = "Topic Identifier Is Required")
	private String topicDescription;

	private String topicLogo;

	private String topicImage;

	/* Relations */
	@OneToMany
	private List<Post> postList;

	@JsonBackReference
	@ManyToOne
	private Category category;

	/* Constructor,Getter And Setter */
	public Topic() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public String getTopicDescription() {
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

	public String getTopicLogo() {
		return topicLogo;
	}

	public void setTopicLogo(String topicLogo) {
		this.topicLogo = topicLogo;
	}

	public String getTopicImage() {
		return topicImage;
	}

	public void setTopicImage(String topicImage) {
		this.topicImage = topicImage;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void addPosts(Post p) {
		this.postList.add(p);
	}

}
