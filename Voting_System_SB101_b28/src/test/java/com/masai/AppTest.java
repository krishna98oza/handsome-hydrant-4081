//package com.masai;
//
//import com.masai.Entity.Candidate;
//import com.masai.Entity.Voter;
//import com.masai.Exception.CandidateNotFoundException;
//import com.masai.Exception.VoterNotFoundException;
//import com.masai.service.CandidateService;
//import com.masai.service.VoterService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringJUnitConfig
//@SpringBootTest
//public class AppTest {
//
//    @Autowired
//    private CandidateService candidateService;
//
//    @Autowired
//    private VoterService voterService;
//
//    @Test
//    public void testCandidateCRUD() {
//        Candidate candidate = new Candidate();
//        candidate.setName("John Doe");
//        candidate.setParty("Demo Party");
//        candidate.setAgenda("Promote Education");
//
//        // Test Create
//        candidateService.saveCandidate(candidate);
//
//        // Test Read
//        Candidate savedCandidate = candidateService.getCandidateById(candidate.getId());
//        assertEquals(candidate.getName(), savedCandidate.getName());
//        assertEquals(candidate.getParty(), savedCandidate.getParty());
//        assertEquals(candidate.getAgenda(), savedCandidate.getAgenda());
//
//        // Test Update
//        savedCandidate.setAgenda("Promote Healthcare");
//        candidateService.updateCandidate(savedCandidate);
//        Candidate updatedCandidate = candidateService.getCandidateById(savedCandidate.getId());
//        assertEquals(savedCandidate.getAgenda(), updatedCandidate.getAgenda());
//
//        // Test Delete
//        candidateService.deleteCandidate(savedCandidate.getId());
//        assertThrows(CandidateNotFoundException.class, () -> candidateService.getCandidateById(savedCandidate.getId()));
//    }
//
//    @Test
//    public void testVoterCRUD() {
//        Voter voter = new Voter();
//        voter.setName("Alice");
//        voter.setEmail("alice@example.com");
//        voter.setPassword("password");
//
//        // Test Create
//        voterService.saveVoter(voter);
//
//        // Test Read
//        Voter savedVoter = voterService.getVoterById(voter.getId());
//        assertEquals(voter.getName(), savedVoter.getName());
//        assertEquals(voter.getEmail(), savedVoter.getEmail());
//        assertEquals(voter.getPassword(), savedVoter.getPassword());
//
//        // Test Update
//        savedVoter.setPassword("newpassword");
//        voterService.updateVoter(savedVoter);
//        Voter updatedVoter = voterService.getVoterById(savedVoter.getId());
//        assertEquals(savedVoter.getPassword(), updatedVoter.getPassword());
//
//        // Test Delete
//        voterService.deleteVoter(savedVoter.getId());
//        assertThrows(VoterNotFoundException.class, () -> voterService.getVoterById(savedVoter.getId()));
//    }
//}
