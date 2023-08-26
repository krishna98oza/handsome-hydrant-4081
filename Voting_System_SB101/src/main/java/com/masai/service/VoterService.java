package com.masai.service;

import com.masai.DAO.VoterDAO;
import com.masai.Entity.Vote;
import com.masai.Entity.Voter;
import com.masai.Exception.EmailAlreadyExistsException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class VoterService {
    private final VoterDAO voterDAO;

    @Autowired
    public VoterService(VoterDAO voterDAO) {
        this.voterDAO = voterDAO;
    }

    public void saveVoter(Voter voter) {
        voterDAO.saveVoter(voter);
    }

    public Voter getVoterById(Long id) {
        return voterDAO.getVoterById(id);
    }

    public void updateVoter(Voter voter) {
        voterDAO.updateVoter(voter);
    }

    public void deleteVoter(Long id) {
        voterDAO.deleteVoter(id);
    }
    
    public List<Vote> getVotingHistory(Voter voter) {
        return voterDAO.getVotingHistory(voter);
    }
    
    public void createVoterAccount(Voter voter) {
        // Check if the email already exists
        if (voterDAO.isEmailExists(voter.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists. Please choose a different email.");
        }

        // Save the new voter account to the database
        voterDAO.saveVoter(voter);
    }

    public Voter getVoterByEmail(String email) {
        return voterDAO.getVoterByEmail(email);
    }
}
