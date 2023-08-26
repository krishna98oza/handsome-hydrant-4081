package com.masai.service;

import com.masai.DAO.CandidateDAO;
import com.masai.Entity.Candidate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CandidateService {
    private static CandidateDAO candidateDAO;

    @Autowired
    public CandidateService(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO;
    }

    public void saveCandidate(Candidate candidate) {
        candidateDAO.saveCandidate(candidate);
    }

    public static Candidate getCandidateById(int candidateId) {
        return candidateDAO.getCandidateById(candidateId);
    }

    public static void updateCandidate(Candidate candidate) {
        candidateDAO.updateCandidate(candidate);
    }

    public void deleteCandidate(int candidateId) {
        candidateDAO.deleteCandidate(candidateId);
    }

	public static List<Candidate> getAllCandidates() {
		// TODO Auto-generated method stub
		return candidateDAO.getAllCandidates();
		
	}
}
