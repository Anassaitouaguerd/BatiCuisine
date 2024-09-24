package com.baticuisine.service;

import com.baticuisine.interfaces.ProjectInterface;
import com.baticuisine.model.Project;
import com.baticuisine.repository.ProjectRepository;

import java.util.List;

public class ProjectService implements ProjectInterface {

    public long createProject(String name , Double surface , Long clientId) {
        return new ProjectRepository().createProject(new Project(name,surface,clientId));
    }
    public void updateProject(Double totalCost , Long projectId) {
        if (totalCost > 0) {
            new ProjectRepository().updateTotalCostProject(totalCost , projectId);
        } else {
            System.out.println("The total cost is not valid to save");
        }
    }

    public void deleteProject() {
    }

    public Project getProject(Long lastProjectId) {
        return new ProjectRepository().getProject(lastProjectId);
    }

    public void getAllProjects() {
        List<Project> projects = new ProjectRepository().getAllProjects();
        // Print the table header with borders
        System.out.println("---------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-10s | %-10s |%n", "Project ID", "Project Name", "Surface", "Client ID");
        System.out.println("---------------------------------------------------------------");

        for (Project project : projects) {
            // Print each project's details in a table format with borders
            System.out.printf("| %-10s | %-20s | %-10s | %-10s |%n",
                    project.getId(),
                    project.getProjectName(),
                    project.getSurface(),
                    project.getClient().getId()
            );
        }

// Print the bottom border
        System.out.println("---------------------------------------------------------------");

    }

    public void profitMargin(Double profitMargin, Long projectId) {
        new ProjectRepository().updateProject(profitMargin, projectId);
    }

    public boolean checkQuoteExist(Long projectId){
        return new ProjectRepository().checkQuoteExist(projectId);
    }
}
