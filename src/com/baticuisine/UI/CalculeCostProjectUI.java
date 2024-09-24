package com.baticuisine.UI;

import com.baticuisine.model.Client;
import com.baticuisine.model.Project;
import com.baticuisine.service.ClientService;
import com.baticuisine.service.ProjectService;
import com.baticuisine.service.QuoteService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class CalculeCostProjectUI {
    public static void calculeCostProject() {
        System.out.println("Calculating the cost of a project");
        getProjects();
        System.out.println("Enter the project ID to calculate the cost");
        Long projectId = Long.parseLong(System.console().readLine());
           if (getProject(projectId) != null) {
               Long clientId = getProject(projectId).getClient().getId();
               Client client = new ClientService().getClient(clientId);
               CalculateCostsUI.calculateCosts(projectId, client, getProject(projectId));
               if (!checkQuoteExist(projectId)) {
                   List<Object> infoQuote = new MenuClientSearch().displayInputsSavingQuote();
                     new QuoteService().createQuote(
                             LocalDate.parse((String) infoQuote.get(0), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                             LocalDate.parse((String) infoQuote.get(1), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                             projectId);

               } else {
                   System.out.println("The project does not have a quote");
               }
           } else {
               System.out.println("The project ID is not valid");
           }
    }
    private static void getProjects(){
        new ProjectService().getAllProjects();
    }
    public static Project getProject(Long projectId){
        return new ProjectService().getProject(projectId);
    }
    public static boolean checkQuoteExist(Long projectId){
        return new ProjectService().checkQuoteExist(projectId);
    }
}
