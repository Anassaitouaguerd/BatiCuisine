package com.baticuisine.UI;

import com.baticuisine.model.Project;
import com.baticuisine.model.Quote;
import com.baticuisine.service.ProjectService;
import com.baticuisine.service.QuoteService;

public class HandleQuoteStatus {
    public static void handleQuoteStatus() {
        choiceProject();
        }
        public static void getALLprojects(){
            new ProjectService().getAllProjects();
        }
        public static void choiceProject(){
            getALLprojects();
            Long projectId = null;
            while (projectId == null) {
                System.out.println("Enter the project ID to change the status : ");
                try {
                    projectId = Long.parseLong(System.console().readLine());
                } catch (NumberFormatException e) {
                    System.out.println("The project ID is not valid");
                }
            }
            Project project = new ProjectService().getProject(projectId);
            if (project != null) {
                getQuote(projectId);
            } else {
                System.out.println("The project ID is not Found");
            }
        }
        public static void getQuote(Long projectId){
            Quote quote = new QuoteService().getQuote(projectId);
            if (quote != null) {
                System.out.println("Quote ID: " + quote.getId());
                System.out.println("Issue Date: " + quote.getIssueDate());
                System.out.println("Validity Date: " + quote.getValidityDate());
                System.out.println("Accepted: " + quote.isAccepted());
                System.out.println("\n--------------------------------------------------\n");
                updateQuoteStatus(quote.getId());
            } else {
                System.out.println("The Quote for this project ID : " + projectId + " is not found");
            }
        }
        public static void updateQuoteStatus(Long quoteId) {
            System.out.println("Do you want to change the status of the Quote? (yes/no)");
            String answer = System.console().readLine().trim().toLowerCase();
            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
                String status;
                do {
                    System.out.println("Enter the status (CANCELED OR VALIDATED): ");
                    status = System.console().readLine().trim().toUpperCase();
                } while (!status.equals("CANCELED") && !status.equals("VALIDATED"));
                new QuoteService().updateQuote(status, quoteId);
            } else {
                System.out.println("The status of the Quote was not changed");
            }
        }
}
