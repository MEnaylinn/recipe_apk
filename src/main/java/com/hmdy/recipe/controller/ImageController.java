package com.hmdy.recipe.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hmdy.recipe.domain.Recipe;
import com.hmdy.recipe.service.ImageService;
import com.hmdy.recipe.service.RecipeService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ImageController {
	private final ImageService imageService;
	private final RecipeService recipeService;

	public ImageController(ImageService imageService, RecipeService recipeService) {
		super();
		this.imageService = imageService;
		this.recipeService = recipeService;
	}
	@GetMapping("/recipe/{recipeId}/image")
	public String showImageForm(@PathVariable Long recipeId,Model model) {
		model.addAttribute("recipeId",recipeId);
		return "recipe/image-upload";
	}

	
	@PostMapping("/recipe/{recipeId}/image")
	public String saveImage(@PathVariable Long recipeId,@RequestParam MultipartFile imagefile) {
		imageService.saveImageToDB(recipeId, imagefile);
		
		return "redirect:/recipe/show/"+recipeId;
		
	}
	
	@GetMapping("/recipe/{recipeId}/recipeimage")
	public void renderImage(@PathVariable Long recipeId,HttpServletResponse response) {
		Optional<Recipe> recipeOpt=recipeService.getRecipeById(recipeId);
		
		if(recipeOpt.isEmpty())throw new RuntimeException("Recipe is not found");
		
		Recipe recipe=recipeOpt.get();
		
		byte[] imageBytes=new byte[recipe.getImage().length];
		
		int id = 0;
		for(final byte b:recipe.getImage()) {
			imageBytes[id++] = b;
		}
		
		InputStream is=new ByteArrayInputStream(imageBytes);
		
		response.setContentType("image/jpeg");
		try {
			IOUtils.copy(is, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
