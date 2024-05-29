package com.hmdy.recipe.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	
	void saveImageToDB(Long recipeId,MultipartFile file);

}
