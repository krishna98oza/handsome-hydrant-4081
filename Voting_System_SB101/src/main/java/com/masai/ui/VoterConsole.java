package com.masai.ui;

import com.masai.utility.ConsoleFormatter;
import com.masai.Entity.Candidate;
import com.masai.Entity.Vote;
import com.masai.Entity.Voter;
import com.masai.service.VoterService;
import com.masai.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class VoterConsole {
    private final VoterService voterService;
    private final Scanner scanner;
    public Voter loggedInVoter;
    @Autowired
    public VoterConsole(VoterService voterService) {
        this.voterService = voterService;
        this.scanner = new Scanner(System.in);
    }
    
    public void createVoterAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(ConsoleFormatter.applyBorderColor("Enter Voter Email:", ConsoleFormatter.YELLOW));
        String email = scanner.nextLine();

        System.out.println(ConsoleFormatter.applyBorderColor("Enter Voter Password:", ConsoleFormatter.YELLOW));
        String password = scanner.nextLine();

        System.out.println(ConsoleFormatter.applyBorderColor("Enter Voter Name:", ConsoleFormatter.YELLOW));
        String name = scanner.nextLine();

        System.out.println(ConsoleFormatter.applyBorderColor("Enter Voter Age:", ConsoleFormatter.YELLOW));
        int age = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character

        if (age < 18) {
            System.out.println(ConsoleFormatter.applyBorderColor("Your age is below 18. You are not eligible to vote.", ConsoleFormatter.YELLOW));
        } else {
            Voter newVoter = new Voter(email, password, name, age);
            voterService.createVoterAccount(newVoter);
            System.out.println(ConsoleFormatter.applyBorderColor("Voter account created successfully!", ConsoleFormatter.YELLOW));
        }
    }
    
    public void startVoterConsole() {
    	 System.out.println(ConsoleFormatter.applyBorderColor("Voter Account - Login", ConsoleFormatter.YELLOW));

    	    Scanner scanner = new Scanner(System.in);
    	    System.out.println(ConsoleFormatter.applyBorderColor("Enter Voter Email:", ConsoleFormatter.YELLOW));
    	    String email = scanner.nextLine();

    	    System.out.println(ConsoleFormatter.applyBorderColor("Enter Voter Password:", ConsoleFormatter.YELLOW));
    	    String password = scanner.nextLine();

    	    // Check if the voter account exists in the database
    	    Voter voter = voterService.getVoterByEmail(email);
    	    if (voter != null && voter.getPassword().equals(password)) {
    	        System.out.println(ConsoleFormatter.applyBorderColor("Voter login successful!", ConsoleFormatter.YELLOW));
    	        loggedInVoter = voter;
    	        voterConsoleOptions();
    	    } else {
    	        System.out.println(ConsoleFormatter.applyBorderColor("Invalid credentials. Voter login failed!", ConsoleFormatter.YELLOW));
    	    }
    }
    public void voterConsoleOptions() {
        if (loggedInVoter == null) {
            System.out.println(ConsoleFormatter.applyBorderColor("Please login as a voter first.", ConsoleFormatter.YELLOW));
            return;
        }
        System.out.println(ConsoleFormatter.applyBorderColor("Welcome "+loggedInVoter.getName()+" to your Voting Account", ConsoleFormatter.YELLOW));

        boolean exit = false;
        while (!exit) {
        	System.out.println();
        	System.out.println(ConsoleFormatter.applyBorderTop("", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderLine(" Please choose an option:         ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderLine(" 1. View Candidate Profiles       ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderLine(" 2. Vote for a Candidate          ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderLine(" 3. View Voting History           ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderLine(" 4. Change Password               ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderLine(" 5. Exit                          ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderBottom("", ConsoleFormatter.GREEN));

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            switch (choice) {
                case 1:
                    viewCandidateProfiles();
                    break;
                case 2:
                    voteForCandidate();
                    break;
                case 3:
                    viewVotingHistory();
                    break;
                case 4:
                    ChangePassword();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println(ConsoleFormatter.applyBorderColor("Invalid choice. Please try again.", ConsoleFormatter.YELLOW));
            }
        }

        System.out.println(ConsoleFormatter.applyBorderColor("Exiting Voter Account...", ConsoleFormatter.YELLOW));
    }


	private static void viewCandidateProfiles() {
		List<Candidate> candidates = CandidateService.getAllCandidates();
	    if (candidates.isEmpty()) {
	        System.out.println(ConsoleFormatter.applyBorderColor("No candidates found.", ConsoleFormatter.YELLOW));
	    } else {
	        System.out.println(ConsoleFormatter.applyBorderColor("Available Candidates:", ConsoleFormatter.YELLOW));
	        for (Candidate candidate : candidates) {
	            System.out.println(candidate);
	        }
	    }
    }

    private void voteForCandidate() {
    	 if (loggedInVoter == null) {
    	        System.out.println(ConsoleFormatter.applyBorderColor("Please login as a voter first.", ConsoleFormatter.YELLOW));
    	        return;
    	    }

    	    viewCandidateProfiles();
    	    
    	    Scanner scanner = new Scanner(System.in);
    	    System.out.println(ConsoleFormatter.applyBorderColor("Enter Candidate ID to Vote:", ConsoleFormatter.YELLOW));
    	    int candidateId = scanner.nextInt();
    	    scanner.nextLine();
    	    
    	    Candidate candidate = CandidateService.getCandidateById(candidateId);

    	    if (candidate == null) {
    	        System.out.println(ConsoleFormatter.applyBorderColor("Invalid Candidate ID. Voting failed.", ConsoleFormatter.YELLOW));
    	    } else if (loggedInVoter.isHasVoted()) {
    	        System.out.println(ConsoleFormatter.applyBorderColor("You have already voted. You cannot vote again.", ConsoleFormatter.YELLOW));
    	    } else {
    	        // Update the voter's hasVoted status to true
    	        loggedInVoter.setHasVoted(true);
    	        voterService.updateVoter(loggedInVoter);

    	        // Increment the candidate's vote count
    	        candidate.setVoteCount(candidate.getVoteCount() + 1);
    	        CandidateService.updateCandidate(candidate);

    	        System.out.println(ConsoleFormatter.applyBorderColor("Vote casted successfully! Thank you for voting.", ConsoleFormatter.YELLOW));
    	    }
    }

   public void viewVotingHistory() {
    if (loggedInVoter == null) {
        System.out.println(ConsoleFormatter.applyBorderColor("Please login as a voter first.", ConsoleFormatter.YELLOW));
        return;
    }

    System.out.println(ConsoleFormatter.applyBorderColor("Your Voting History:", ConsoleFormatter.YELLOW));
    List<Vote> votingHistory = voterService.getVotingHistory(loggedInVoter);
    if (votingHistory.isEmpty()) {
        System.out.println(ConsoleFormatter.applyBorderColor("You have not voted in any election yet.", ConsoleFormatter.YELLOW));
    } else {
        for (Vote vote : votingHistory) {
            Candidate candidate = CandidateService.getCandidateById(vote.getCandidateId());
            System.out.println(ConsoleFormatter.applyBorderColor("Election ID: " + vote.getElectionId() + ", Voted for: " + candidate.getName(), ConsoleFormatter.CYAN));
        }
    }
}
    
    private void ChangePassword() {
    	if (loggedInVoter == null) {
            System.out.println(ConsoleFormatter.applyBorderColor("Please login as a voter first.", ConsoleFormatter.YELLOW));
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleFormatter.applyBorderColor("Enter Old Password:", ConsoleFormatter.CYAN));
        String oldPassword = scanner.nextLine();

        if (!oldPassword.equals(loggedInVoter.getPassword())) {
            System.out.println(ConsoleFormatter.applyBorderColor("Incorrect old password. Password change failed.", ConsoleFormatter.YELLOW));
        } else {
            System.out.println(ConsoleFormatter.applyBorderColor("Enter New Password:", ConsoleFormatter.CYAN));
            String newPassword = scanner.nextLine();

            loggedInVoter.setPassword(newPassword);
            voterService.updateVoter(loggedInVoter);

            System.out.println(ConsoleFormatter.applyBorderColor("Password changed successfully.", ConsoleFormatter.CYAN));
        }
		
	}
}
