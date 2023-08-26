package com.masai.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "voters")
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private int age;
    private boolean hasVoted;
	public Voter() {
		super();
	}
	public Voter(Long id, String name, String email, String password,int age, boolean hasVoted) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.hasVoted = hasVoted;
	}
	public Voter(String email, String password, String name, int age) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.hasVoted = false; 
    }
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public boolean isHasVoted() {
		return hasVoted;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setHasVoted(boolean hasVoted) {
		this.hasVoted = hasVoted;
	}
	@Override
	public String toString() {
		return "Voter [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", hasVoted="
				+ hasVoted + "]";
	}

	
    // Constructors, getters, and setters
}
