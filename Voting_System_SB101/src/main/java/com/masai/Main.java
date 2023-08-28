package com.masai;

import com.masai.utility.ConsoleFormatter;
import com.masai.service.CandidateService;
import com.masai.service.VoterService;
import com.masai.ui.AdminConsole;
import com.masai.ui.VoterConsole;
import com.masai.DAO.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(ConsoleFormatter.applyBorderColor("Welcome to the Voting System!", ConsoleFormatter.YELLOW));
        
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
        	 System.out.println(ConsoleFormatter.applyBorderTop("", ConsoleFormatter.GREEN));
        	 System.out.println(ConsoleFormatter.applyBorderLine(" Please choose an option:         ", ConsoleFormatter.GREEN));
             System.out.println(ConsoleFormatter.applyBorderLine(" 1. Administrator Login           ", ConsoleFormatter.GREEN));
             System.out.println(ConsoleFormatter.applyBorderLine(" 2. Voter Account Create          ", ConsoleFormatter.GREEN));
             System.out.println(ConsoleFormatter.applyBorderLine(" 3. Voter Login                   ", ConsoleFormatter.GREEN));
             System.out.println(ConsoleFormatter.applyBorderLine(" 4. Exit                          ", ConsoleFormatter.GREEN));
             System.out.println(ConsoleFormatter.applyBorderBottom("", ConsoleFormatter.GREEN));

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
                    System.out.println(ConsoleFormatter.applyBorderColor("Invalid choice. Please try again.", ConsoleFormatter.RED));
            }
        }

        System.out.println(ConsoleFormatter.applyBorderColor("Exiting Voting System...", ConsoleFormatter.YELLOW));
        scanner.close();
    }
}
