package com.hmdy.recipe.domain;

import java.util.Arrays;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Recipe {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Lob
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private String source;
	@Lob
	private String directions;
	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;
	@Lob
	private Byte[] image;	
	
	public Recipe() {
		// TODO Auto-generated constructor stub
	}

	public Recipe(String name, String description, Integer prepTime, Integer cookTime, String source,
			String directions, Difficulty difficulty, Byte[] image) {
		super();
		this.name = name;
		this.description = description;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.source = source;
		this.directions = directions;
		this.difficulty = difficulty;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	public Integer getCookTime() {
		return cookTime;
	}

	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", prepTime=" + prepTime
				+ ", cookTime=" + cookTime + ", source=" + source + ", directions=" + directions + ", difficulty="
				+ difficulty + ", image=" + Arrays.toString(image) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result
				+ Objects.hash(cookTime, description, difficulty, directions, id, name, prepTime, source);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		return Objects.equals(cookTime, other.cookTime) && Objects.equals(description, other.description)
				&& difficulty == other.difficulty && Objects.equals(directions, other.directions)
				&& Objects.equals(id, other.id) && Arrays.equals(image, other.image) && Objects.equals(name, other.name)
				&& Objects.equals(prepTime, other.prepTime) && Objects.equals(source, other.source);
	}
	
}
