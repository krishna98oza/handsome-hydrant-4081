package com.masai.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String party;
    private String agenda;
	public Candidate() {
//		super();
	}
	
	public Candidate(Long id, String name, String party, String agenda) {
		super();
		this.id = id;
		this.name = name;
		this.party = party;
		this.agenda = agenda;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getParty() {
		return party;
	}
	public String getAgenda() {
		return agenda;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", name=" + name + ", party=" + party + ", agenda=" + agenda + "]";
	}
    // Constructors, getters, and setters

	public int getVoteCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setVoteCount(int i) {
		// TODO Auto-generated method stub
		
	}
    
}
