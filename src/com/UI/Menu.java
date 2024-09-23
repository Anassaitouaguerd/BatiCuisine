package com.UI;

import com.service.ProjectService;

public class Menu {
private static final int CREATE_PROJECT = 1;
private static final int DISPLAY_PROJECTS = 2;
private static final int CALCULETE_COST = 3;
private static final int CHANGE_STATUS = 4;
        public void showMenu() {
            while(true) {
                System.out.println("========= Project Management Menu =========");
                System.out.println("1. Create Project");
                System.out.println("2. Show existing projects");
                System.out.println("3. Calculate the cost of a project");
                System.out.println("4. Change the status of a quote");
                System.out.println("5. Exit");
                System.out.println("===========================================");
                int choice = 0;
                try {
                    choice = Integer.parseInt(System.console().readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number");
                }
                switch (choice) {
                    case CREATE_PROJECT:
                        MenuClientSearch.searchClient();
                        break;
                    case DISPLAY_PROJECTS:
                        new ProjectService().getAllProjects();
                        break;
                    case CALCULETE_COST:
                        CalculeCostProjectUI.calculeCostProject();
                        break;
                    case CHANGE_STATUS:
                        HandleQuoteStatus.handleQuoteStatus();
                        break;
                    case 5:
                        System.out.println("Exiting");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }

        }
}
