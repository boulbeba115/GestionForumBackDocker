package tn.Forum.Main.Web;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.Forum.Main.Models.Category;
import tn.Forum.Main.Services.CategoryService;
import tn.Forum.Main.Services.MapValidationErrorService;
import tn.Forum.Main.configuration.FileUploadUtil;
import tn.Forum.Main.queryManaging.Category.SimpleCategory;

@RestController
@RequestMapping("/api/Categories")
@CrossOrigin
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	MapValidationErrorService mapValErrSev;

	// add a category
	@PostMapping("/{id}")
	public ResponseEntity<?> createNewCategory(@PathVariable Long id, @Valid @RequestBody Category category,
			BindingResult result) {
		ResponseEntity<?> errorMap = mapValErrSev.MapValidationService(result);
		if (errorMap != null)
			return errorMap;
		/* TODO */
		// call user service and check if the user is admin to allow delete
		/* TODO */
		Category c = categoryService.AddCategory(category);
		return new ResponseEntity<Category>(c, HttpStatus.CREATED);
	}

	// get category by Identifier
	@GetMapping("/{categoryId}")
	public ResponseEntity<?> getByCategoryIdentifier(@PathVariable String categoryId) {
		Category c = categoryService.getCategoryByIdentifier(categoryId);
		return new ResponseEntity<Category>(c, HttpStatus.ACCEPTED);
	}

	// get all categories
	@GetMapping("/all")
	public Iterable<Category> getAllCategories() {
		return categoryService.getAllCategory();
	}

	// get all categories
	@GetMapping("/simple/all")
	public List<SimpleCategory> getAllSimpleCategories() {
		return categoryService.getAllSimpleCategories();
	}

	// physical delete
	@DeleteMapping("/permanent/{categoryId}")
	public ResponseEntity<?> DeleteCategory(@PathVariable String categoryId) {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<String>(
				"The  Category with identifier : '" + categoryId.toUpperCase() + "' was permanently deleted",
				HttpStatus.ACCEPTED);
	}

	// add with file Upload
	@PostMapping(value = "/img")
	public ResponseEntity<?> addnewCat(@RequestParam("file") MultipartFile[] file,
			@RequestParam("category") String category, @RequestParam("path") String path) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Category cat = objectMapper.readValue(category, Category.class);
		try {
			if (file[0] == null)
				return new ResponseEntity<String>("You need to add a category logo image", HttpStatus.ACCEPTED);
			else if (file[0] == null)
				return new ResponseEntity<String>("You need to add a category logo image", HttpStatus.ACCEPTED);
			else {
				MultipartFile logo = file[0];
				MultipartFile cover = file[1];
				String logoName = "Cat_" + UUID.randomUUID().toString()
						+ StringUtils.cleanPath(logo.getOriginalFilename());
				String coverName = "Cat_" + UUID.randomUUID().toString()
						+ StringUtils.cleanPath(cover.getOriginalFilename());
				cat.setCategoryLogo(logoName);
				cat.setCategoryImage(coverName);
				String uploadDir = path;
				FileUploadUtil.saveFile(uploadDir, logoName, logo);
				FileUploadUtil.saveFile(uploadDir, coverName, cover);
			}
		} catch (Exception e) {
			System.out.println(e.getCause());
		} finally {
			Category c = categoryService.AddCategory(cat);
			return new ResponseEntity<Category>(c, HttpStatus.ACCEPTED);
		}
	}

}
