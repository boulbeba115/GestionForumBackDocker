package tn.Forum.Main.Models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;


@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Category Identifier Is Required")
	@Column(updatable = false, unique = true)
	private String categoryId;

	@NotBlank(message = "The Category Title Is Required")
	private String categoryTitle;

	@NotBlank(message = "The Category Description Is Required")
	@Type(type = "text")
	private String categoryDescription;

	private String categoryLogo;

	private String categoryImage;

	/* Relations */

	@OneToMany
	private List<Topic> topicList;

	/* Constructor,Getter And Setter */
	public Category() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getCategoryLogo() {
		return categoryLogo;
	}

	public void setCategoryLogo(String categoryLogo) {
		this.categoryLogo = categoryLogo;
	}

	public String getCategoryImage() {
		return categoryImage;
	}

	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}

	public List<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}

	public void addTopic(Topic t) {
		this.topicList.add(t);
		
	}

	public void flush(Topic oldTopic) {
			this.topicList.remove(oldTopic);
	}

	

}
