package com.masai.DAO;

import java.util.List;

import com.masai.Entity.Candidate;
import com.masai.Exception.CandidateNotFoundException;

public interface CandidateDAO {
    void saveCandidate(Candidate candidate);
    void updateCandidate(Candidate candidate);
    void deleteCandidate(int candidateId);
    Candidate getCandidateById(int candidateId) throws CandidateNotFoundException;
    List<Candidate> getAllCandidates();
}
