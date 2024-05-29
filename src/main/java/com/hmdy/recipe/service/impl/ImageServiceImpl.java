package com.hmdy.recipe.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hmdy.recipe.domain.Recipe;
import com.hmdy.recipe.repository.RecipeRepository;
import com.hmdy.recipe.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	
	private final RecipeRepository recipeRepository;
	

	public ImageServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}


	@Override
	public void saveImageToDB(Long recipeId, MultipartFile file) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOpt=recipeRepository.findById(recipeId);
		if(recipeOpt.isEmpty())throw new RuntimeException("Recipe is not found");
		
		Recipe recipe=recipeOpt.get();
		
		try {
			Byte[] imageBytes=new Byte[file.getBytes().length];
			
			int i=0;
			for(final Byte b:file.getBytes() ) {
				imageBytes[i++] = b;
//				System.out.println(b);
			}
			
			recipe.setImage(imageBytes);
			recipeRepository.save(recipe);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
