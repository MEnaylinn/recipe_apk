package com.hmdy.recipe.domain;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Notes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	private String description;
	
	public Notes() {
		// TODO Auto-generated constructor stub
	}

	public Notes(String description) {
		super();
		this.description = description;
	}

	@Override
	public String toString() {
		return "Notes [id=" + id + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notes other = (Notes) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}
	
}
