package com.hmdy.recipe.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Lob
	private String description;
	
	private Integer prepTime;
	private Integer cookTime;
	private String source;
	private Integer servings;
	
	@Lob
	private String directions;
	
	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;
	
	@Lob
	private Byte[] image;
	
	@OneToMany(mappedBy = "recipe",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<Ingredient> ingredients = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "note_id")
	private Notes note;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "recipe_category",joinColumns = @JoinColumn(name = "recipe_id"),
	inverseJoinColumns = @JoinColumn(name = "category_id")
		)
	private Set<Category> categories = new HashSet<>();
	
	public Recipe() {}


	public Recipe(String name, String description, Integer prepTime, Integer cookTime, String source, Integer servings,
			String directions, Difficulty difficulty) {
		super();
		this.name = name;
		this.description = description;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.source = source;
		this.servings = servings;
		this.directions = directions;
		this.difficulty = difficulty;
	}

	public Integer getServings() {
		return servings;
	}


	public void setServings(Integer servings) {
		this.servings = servings;
	}


	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Notes getNote() {
		return note;
	}

	public void setNote(Notes note) {
		this.note = note;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
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
				+ ", cookTime=" + cookTime + ", source=" + source + ", servings=" + servings + ", directions="
				+ directions + ", difficulty=" + difficulty + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(cookTime, description, difficulty, directions, id, name, prepTime, servings, source);
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
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(prepTime, other.prepTime) && Objects.equals(servings, other.servings)
				&& Objects.equals(source, other.source);
	}

	
	
}
