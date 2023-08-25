package com.masai;

import com.masai.service.CandidateService;
import com.masai.service.VoterService;
import com.masai.ui.AdminConsole;
import com.masai.ui.VoterConsole;
import com.masai.DAO.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Voting System!");
        
        CandidateDAO candidateDAO = new CandidateDAOImpl();
        VoterDAO voterDAO = new VoterDAOImpl();

        // Manually create instances of CandidateService and VoterService
        CandidateService candidateService = new CandidateService(candidateDAO);
        VoterService voterService = new VoterService(voterDAO);

        // Instantiate the AdminConsole and VoterConsole classes with the required services
        AdminConsole adminConsole = new AdminConsole(candidateService);
        VoterConsole voterConsole = new VoterConsole(voterService);

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
        	System.out.println("+----------------------------+");
            System.out.println("| Please choose an option:   |");
            System.out.println("| 1. Administrator Login     |");
            System.out.println("| 2. Voter Account Create    |");
            System.out.println("| 3. Voter Login             |");
            System.out.println("| 4. Exit                    |");
            System.out.println("+----------------------------+");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            switch (choice) {
                case 1:
                    adminConsole.startAdminConsole();
                    break;
                case 2:
                    voterConsole.createVoterAccount();
                    break;
                case 3:
                    voterConsole.startVoterConsole();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Exiting Voting System...");
        scanner.close();
    }
}
