package tn.Forum.Main.Services;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Forum.Main.Exceptions.ForumException;
import tn.Forum.Main.Models.Category;
import tn.Forum.Main.Repositories.CategoryRepo;
import tn.Forum.Main.queryManaging.Category.SimpleCategory;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	IdGeneratorService generator;
	
	// add Category
	public Category AddCategory(Category c) {
		try {
			if(Objects.isNull(c.getId())) {
				c.setCategoryId(generator.categoryIdGenerator().toUpperCase());
			}
			return categoryRepo.save(c);
		} catch (Exception e) {
			throw new ForumException(
					"The Category Identifier : '" + c.getCategoryId().toUpperCase() + "' already exists");
		}
	}

	// get By  Identifier
	public Category getCategoryByIdentifier(String categoryIdentifer) {
		Category c = categoryRepo.findByCategoryId(categoryIdentifer.toUpperCase());
		if (c == null)
			throw new ForumException(
					"No Category Match Found With Category Identifier :" + categoryIdentifer.toUpperCase());
		return c;
	}

	// get all
	public Iterable<Category> getAllCategory() {
		return categoryRepo.findAll();
	}
	
	// Delete
		public void deleteCategory(String categoryIdentifer) {
			Category c = categoryRepo.findByCategoryId(categoryIdentifer.toUpperCase());
			if (c == null || c.getTopicList().size()>0)
				throw new ForumException("This category Cannot Be Deleted");
			try {
				categoryRepo.deleteById(c.getId());
			} catch (Exception e) {
				throw new ForumException("This category Cannot Be Deleted");
			}

		}

		public List<SimpleCategory> getAllSimpleCategories() {
			return categoryRepo.findAllProjectedBy();
		}
}
