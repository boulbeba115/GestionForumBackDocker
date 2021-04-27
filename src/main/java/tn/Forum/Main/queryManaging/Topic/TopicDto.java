package tn.Forum.Main.queryManaging.Topic;


import tn.Forum.Main.queryManaging.Category.CategoryDto;

public class TopicDto {

	private Long id;
	private String topicId;
	private String topicTitle;
	private String topicDescription;
	private String topicLogo;
	private String topicImage;
	private CategoryDto category;
	
	
	
	public TopicDto() {
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
	public CategoryDto getCategorydto() {
		return category;
	}
	public void setCategorydto(CategoryDto category) {
		this.category = category;
	}


	

}
