package com.masai.DAO;

import java.util.List;

import com.masai.Entity.Vote;
import com.masai.Entity.Voter;
import com.masai.Exception.VoterNotFoundException;

public interface VoterDAO {
    void saveVoter(Voter voter);

    Voter getVoterById(Long id) throws VoterNotFoundException;

    void updateVoter(Voter voter);

    void deleteVoter(Long id) throws VoterNotFoundException;

    boolean isEmailExists(String email);
    
    Voter getVoterByEmail(String email);

    List<Vote> getVotingHistory(Voter voter);
}
