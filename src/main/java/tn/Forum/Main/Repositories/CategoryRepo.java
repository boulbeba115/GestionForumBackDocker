package tn.Forum.Main.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Forum.Main.Models.Category;
import tn.Forum.Main.queryManaging.Category.SimpleCategory;

public interface CategoryRepo extends JpaRepository<Category, Long>{

	Category findByCategoryId(String categoryId);

	Category findFirstByOrderByIdDesc();

	List<SimpleCategory> findAllProjectedBy();

}
