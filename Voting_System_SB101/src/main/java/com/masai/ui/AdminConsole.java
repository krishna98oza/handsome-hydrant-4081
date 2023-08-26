package com.masai.ui;

import com.masai.utility.ConsoleFormatter;
import com.masai.Entity.Candidate;
import com.masai.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class AdminConsole {
    private final CandidateService candidateService;
    private final Scanner scanner;
    private boolean isAdminLoggedIn = false;
    
    @Autowired
    public AdminConsole(CandidateService candidateService) {
        this.candidateService = candidateService;
        this.scanner = new Scanner(System.in);
    }

    public void startAdminConsole() {
    	System.out.println(ConsoleFormatter.applyBorder("Admin - Login", ConsoleFormatter.GREEN));

        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleFormatter.applyBorder("Enter Admin User ID:", ConsoleFormatter.GREEN));
        String userId = scanner.nextLine();

        System.out.println(ConsoleFormatter.applyBorder("Enter Admin Password:", ConsoleFormatter.GREEN));
        String password = scanner.nextLine();

        if (userId.equals("admin") && password.equals("admin")) {
            isAdminLoggedIn = true;
            System.out.println(ConsoleFormatter.applyBorder("Admin login successful!", ConsoleFormatter.GREEN));
            adminConsoleOptions();
        } else {
            System.out.println(ConsoleFormatter.applyBorder("Invalid credentials. Admin login failed!", ConsoleFormatter.GREEN));
        }
    }
    public void adminConsoleOptions() {
    	if (!isAdminLoggedIn) {
            System.out.println("Please login as an admin first.");
            return;
        }
        System.out.println(ConsoleFormatter.applyBorder("Welcome to the Administator !", ConsoleFormatter.GREEN));

        boolean exit = false;
        while (!exit) {
        	
            System.out.println(ConsoleFormatter.applyBorder(" Please choose an option: ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorder(" 1. Add Candidate         ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorder(" 2. Update Candidate      ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorder(" 3. Delete Candidate      ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorder(" 4. View All Candidates   ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorder(" 5. Exit                  ", ConsoleFormatter.GREEN));
            

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            switch (choice) {
                case 1:
                    addCandidate();
                    break;
                case 2:
                    updateCandidate();
                    break;
                case 3:
                    deleteCandidate();
                    break;
                case 4:
                	viewAllCandidates();
                	break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Exiting Admin Console...");
    }

    private void addCandidate() {
        System.out.println("Enter Candidate Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Party Name:");
        String party = scanner.nextLine();

        System.out.println("Enter Candidate Agenda:");
        String agenda = scanner.nextLine();

        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setParty(party);
        candidate.setAgenda(agenda);

        candidateService.saveCandidate(candidate);
        System.out.println("Candidate added successfully!");
    }

    private void updateCandidate() {
        // Implement update logic
    	System.out.println("Enter Candidate ID to update:");
        int candidateId = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character

        Candidate candidate = candidateService.getCandidateById(candidateId);
        if (candidate != null) {
            System.out.println("Enter updated Candidate Name:");
            String name = scanner.nextLine();

            System.out.println("Enter updated Party Name:");
            String party = scanner.nextLine();

            System.out.println("Enter updated Candidate Agenda:");
            String agenda = scanner.nextLine();

            candidate.setName(name);
            candidate.setParty(party);
            candidate.setAgenda(agenda);

            candidateService.updateCandidate(candidate);
            System.out.println("Candidate updated successfully!");
        } else {
            System.out.println("Candidate with ID " + candidateId + " not found.");
        }
    }

    private void deleteCandidate() {
        // Implement delete logic
    	System.out.println("Enter Candidate ID to delete:");
        int candidateId = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character

        Candidate candidate = candidateService.getCandidateById(candidateId);
        if (candidate != null) {
            candidateService.deleteCandidate(candidateId);
            System.out.println("Candidate deleted successfully!");
        } else {
            System.out.println("Candidate with ID " + candidateId + " not found.");
        }
    }
    private void viewAllCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        if (!candidates.isEmpty()) {
            System.out.println("All Candidates:");
            for (Candidate candidate : candidates) {
            	System.out.println("+----------------------------+");
                System.out.println(" ID: " + candidate.getId());
                System.out.println(" Name: " + candidate.getName());
                System.out.println(" Party: " + candidate.getParty());
                System.out.println(" Agenda: " + candidate.getAgenda());
                System.out.println("+----------------------------+");
            }
        } else {
            System.out.println("No candidates found.");
        }
    }
}
