package com.masai.ui;

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

        System.out.println("Enter Voter Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Voter Password:");
        String password = scanner.nextLine();

        System.out.println("Enter Voter Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Voter Age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character

        if (age < 18) {
            System.out.println("Your age is below 18. You are not eligible to vote.");
        } else {
            Voter newVoter = new Voter(email, password, name, age);
            voterService.createVoterAccount(newVoter);
            System.out.println("Voter account created successfully!");
        }
    }
    
    public void startVoterConsole() {
    	 System.out.println("Voter Account - Login");

    	    Scanner scanner = new Scanner(System.in);
    	    System.out.println("Enter Voter Email:");
    	    String email = scanner.nextLine();

    	    System.out.println("Enter Voter Password:");
    	    String password = scanner.nextLine();

    	    // Check if the voter account exists in the database
    	    Voter voter = voterService.getVoterByEmail(email);
    	    if (voter != null && voter.getPassword().equals(password)) {
    	        System.out.println("Voter login successful!");
    	        loggedInVoter = voter;
    	        voterConsoleOptions();
    	    } else {
    	        System.out.println("Invalid credentials. Voter login failed!");
    	    }
    }
    public void voterConsoleOptions() {
        if (loggedInVoter == null) {
            System.out.println("Please login as a voter first.");
            return;
        }
        System.out.println("Welcome "+loggedInVoter.getName()+" to your Voting Account" );

        boolean exit = false;
        while (!exit) {
        	System.out.println();
        	System.out.println("+-----------------------------+");
            System.out.println("| Please choose an option:    |");
            System.out.println("| 1. View Candidate Profiles  |");
            System.out.println("| 2. Vote for a Candidate     |");
            System.out.println("| 3. View Voting History      |");
            System.out.println("| 4. Change Password          |");
            System.out.println("| 5. Exit                     |");
            System.out.println("+-----------------------------+");

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
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Exiting Voter Account...");
    }


	private static void viewCandidateProfiles() {
		List<Candidate> candidates = CandidateService.getAllCandidates();
	    if (candidates.isEmpty()) {
	        System.out.println("No candidates found.");
	    } else {
	        System.out.println("Available Candidates:");
	        for (Candidate candidate : candidates) {
	            System.out.println(candidate);
	        }
	    }
    }

    private void voteForCandidate() {
    	 if (loggedInVoter == null) {
    	        System.out.println("Please login as a voter first.");
    	        return;
    	    }

    	    viewCandidateProfiles();
    	    
    	    Scanner scanner = new Scanner(System.in);
    	    System.out.println("Enter Candidate ID to Vote:");
    	    int candidateId = scanner.nextInt();
    	    scanner.nextLine();
    	    
    	    Candidate candidate = CandidateService.getCandidateById(candidateId);

    	    if (candidate == null) {
    	        System.out.println("Invalid Candidate ID. Voting failed.");
    	    } else if (loggedInVoter.isHasVoted()) {
    	        System.out.println("You have already voted. You cannot vote again.");
    	    } else {
    	        // Update the voter's hasVoted status to true
    	        loggedInVoter.setHasVoted(true);
    	        voterService.updateVoter(loggedInVoter);

    	        // Increment the candidate's vote count
    	        candidate.setVoteCount(candidate.getVoteCount() + 1);
    	        CandidateService.updateCandidate(candidate);

    	        System.out.println("Vote casted successfully! Thank you for voting.");
    	    }
    }

   public void viewVotingHistory() {
    if (loggedInVoter == null) {
        System.out.println("Please login as a voter first.");
        return;
    }

    System.out.println("Your Voting History:");
    List<Vote> votingHistory = voterService.getVotingHistory(loggedInVoter);
    if (votingHistory.isEmpty()) {
        System.out.println("You have not voted in any election yet.");
    } else {
        for (Vote vote : votingHistory) {
            Candidate candidate = CandidateService.getCandidateById(vote.getCandidateId());
            System.out.println("Election ID: " + vote.getElectionId() + ", Voted for: " + candidate.getName());
        }
    }
}
    
    private void ChangePassword() {
    	if (loggedInVoter == null) {
            System.out.println("Please login as a voter first.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Old Password:");
        String oldPassword = scanner.nextLine();

        if (!oldPassword.equals(loggedInVoter.getPassword())) {
            System.out.println("Incorrect old password. Password change failed.");
        } else {
            System.out.println("Enter New Password:");
            String newPassword = scanner.nextLine();

            loggedInVoter.setPassword(newPassword);
            voterService.updateVoter(loggedInVoter);

            System.out.println("Password changed successfully.");
        }
		
	}
}
