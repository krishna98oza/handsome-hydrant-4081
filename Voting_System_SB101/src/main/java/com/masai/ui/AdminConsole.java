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
    	System.out.println(ConsoleFormatter.applyBorderColor("Admin - Login", ConsoleFormatter.GREEN));

        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleFormatter.applyBorderColor("Enter Admin User ID:", ConsoleFormatter.GREEN));
        String userId = scanner.nextLine();

        System.out.println(ConsoleFormatter.applyBorderColor("Enter Admin Password:", ConsoleFormatter.GREEN));
        String password = scanner.nextLine();

        if (userId.equals("admin") && password.equals("admin")) {
            isAdminLoggedIn = true;
            System.out.println(ConsoleFormatter.applyBorderColor("Admin login successful!", ConsoleFormatter.GREEN));
            adminConsoleOptions();
        } else {
            System.out.println(ConsoleFormatter.applyBorderColor("Invalid credentials. Admin login failed!", ConsoleFormatter.GREEN));
        }
    }
    public void adminConsoleOptions() {
    	if (!isAdminLoggedIn) {
            System.out.println("Please login as an admin first.");
            return;
        }
        System.out.println(ConsoleFormatter.applyBorderColor("Welcome to the Administator !", ConsoleFormatter.GREEN));

        boolean exit = false;
        while (!exit) {
        	System.out.println(ConsoleFormatter.applyBorderTop("", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderLine(" Please choose an option:         ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderLine(" 1. Add Candidate                 ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderLine(" 2. Update Candidate              ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderLine(" 3. Delete Candidate              ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderLine(" 4. View All Candidates           ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderLine(" 5. Exit                          ", ConsoleFormatter.GREEN));
            System.out.println(ConsoleFormatter.applyBorderBottom("", ConsoleFormatter.GREEN));

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
                    System.out.println(ConsoleFormatter.applyBorderColor("Invalid choice. Please try again.", ConsoleFormatter.RED));
            }
        }

        System.out.println(ConsoleFormatter.applyBorderColor("Exiting Admin Console...", ConsoleFormatter.YELLOW));
    }

    private void addCandidate() {
        System.out.println(ConsoleFormatter.applyBorderColor("Enter Candidate Name:", ConsoleFormatter.YELLOW));
        String name = scanner.nextLine();

        System.out.println(ConsoleFormatter.applyBorderColor("Enter Party Name:", ConsoleFormatter.YELLOW));
        String party = scanner.nextLine();

        System.out.println(ConsoleFormatter.applyBorderColor("Enter Candidate Agenda:", ConsoleFormatter.YELLOW));
        String agenda = scanner.nextLine();

        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setParty(party);
        candidate.setAgenda(agenda);

        candidateService.saveCandidate(candidate);
        System.out.println(ConsoleFormatter.applyBorderColor("Candidate added successfully!", ConsoleFormatter.CYAN));
    }

    private void updateCandidate() {
        // Implement update logic
    	System.out.println(ConsoleFormatter.applyBorderColor("Enter Candidate ID to update:", ConsoleFormatter.YELLOW));
        int candidateId = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character

        Candidate candidate = candidateService.getCandidateById(candidateId);
        if (candidate != null) {
            System.out.println(ConsoleFormatter.applyBorderColor("Enter updated Candidate Name:", ConsoleFormatter.YELLOW));
            String name = scanner.nextLine();

            System.out.println(ConsoleFormatter.applyBorderColor("Enter updated Party Name:", ConsoleFormatter.YELLOW));
            String party = scanner.nextLine();

            System.out.println(ConsoleFormatter.applyBorderColor("Enter updated Candidate Agenda:", ConsoleFormatter.YELLOW));
            String agenda = scanner.nextLine();

            candidate.setName(name);
            candidate.setParty(party);
            candidate.setAgenda(agenda);

            candidateService.updateCandidate(candidate);
            System.out.println(ConsoleFormatter.applyBorderColor("Candidate updated successfully!", ConsoleFormatter.YELLOW));
        } else {
            System.out.println(ConsoleFormatter.applyBorderColor("Candidate with ID " + candidateId + " not found.", ConsoleFormatter.YELLOW));
        }
    }

    private void deleteCandidate() {
        // Implement delete logic
    	System.out.println(ConsoleFormatter.applyBorderColor("Enter Candidate ID to delete:", ConsoleFormatter.YELLOW));
        int candidateId = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character

        Candidate candidate = candidateService.getCandidateById(candidateId);
        if (candidate != null) {
            candidateService.deleteCandidate(candidateId);
            System.out.println(ConsoleFormatter.applyBorderColor("Candidate deleted successfully!", ConsoleFormatter.YELLOW));
        } else {
            System.out.println(ConsoleFormatter.applyBorderColor("Candidate with ID " + candidateId + " not found.", ConsoleFormatter.YELLOW));
        }
    }
    private void viewAllCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        if (!candidates.isEmpty()) {
            System.out.println(ConsoleFormatter.applyBorderColor("All Candidates:", ConsoleFormatter.YELLOW));
            for (Candidate candidate : candidates) {
            	System.out.println(ConsoleFormatter.applyBorderTop("", ConsoleFormatter.CYAN));
                System.out.println(ConsoleFormatter.applyBorderLineLeft(" ID: " + candidate.getId(), ConsoleFormatter.CYAN));
                System.out.println(ConsoleFormatter.applyBorderLineLeft(" Name: " + candidate.getName(), ConsoleFormatter.CYAN));
                System.out.println(ConsoleFormatter.applyBorderLineLeft(" Party: " + candidate.getParty(), ConsoleFormatter.CYAN));
                System.out.println(ConsoleFormatter.applyBorderLineLeft(" Agenda: " + candidate.getAgenda(), ConsoleFormatter.CYAN));
                System.out.println(ConsoleFormatter.applyBorderBottom("", ConsoleFormatter.CYAN));
            }
        } else {
            System.out.println(ConsoleFormatter.applyBorderColor("No candidates found.", ConsoleFormatter.YELLOW));
        }
    }
}
