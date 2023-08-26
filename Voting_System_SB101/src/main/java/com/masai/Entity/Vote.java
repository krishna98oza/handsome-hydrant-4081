package com.masai.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int electionId;
    private int candidateId;
    private int voterId;

    public Vote() {
        // Empty constructor required by JPA
    }

    public Vote(int electionId, int candidateId, int voterId) {
        this.electionId = electionId;
        this.candidateId = candidateId;
        this.voterId = voterId;
    }

	public int getId() {
		return id;
	}

	public int getElectionId() {
		return electionId;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public int getVoterId() {
		return voterId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setElectionId(int electionId) {
		this.electionId = electionId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public void setVoterId(int voterId) {
		this.voterId = voterId;
	}

    // Getters and setters

    // ...
}
