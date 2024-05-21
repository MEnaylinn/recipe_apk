package com.hmdy.recipe.domain;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Category {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	@Lob
	private String description;
	
public Category() {
	// TODO Auto-generated constructor stub
}

public Category(String name, String description) {
	super();
	this.name = name;
	this.description = description;
}

@Override
public String toString() {
	return "Category [id=" + id + ", name=" + name + ", description=" + description + "]";
}

@Override
public int hashCode() {
	return Objects.hash(description, id, name);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Category other = (Category) obj;
	return Objects.equals(description, other.description) && Objects.equals(id, other.id)
			&& Objects.equals(name, other.name);
}

}
