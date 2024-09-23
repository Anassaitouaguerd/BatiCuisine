package com.UI;

import com.model.Client;
import com.model.Project;
import com.service.ClientService;
import com.service.ProjectService;


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
}
