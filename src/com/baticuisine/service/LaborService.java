package com.baticuisine.service;

import com.baticuisine.enums.LaborType;
import com.baticuisine.model.Labor;
import com.baticuisine.model.Project;
import com.baticuisine.repository.ComponentRepository;
import com.baticuisine.repository.LaborRepository;

public class LaborService {
    public void createLabor(String name , String type , Double hourlyRate , Double workHours , Double workerProductivity , Long projectId) {
        Project project = new Project(projectId);
        if(type.equals("Basic Worker")){
            type = LaborType.valueOf("Basic_worker").toString();
        }else if(type.equals("Specialist")){
            type = LaborType.valueOf("Specialist").toString();
        }
        Labor labor = new Labor(name , type , hourlyRate , workHours , workerProductivity, project);
        long lastComponentInserted = new ComponentRepository().createComponentLabor(labor);
        new LaborRepository().creatLabor(labor , lastComponentInserted);

    }

    public void updateLabor() {
        System.out.println("LaborService.updateLabor");
    }

    public void deleteLabor() {
        System.out.println("LaborService.deleteLabor");
    }

    public void getLabor() {
        System.out.println("LaborService.getLabor");
    }

    public void getAllLabors() {
        System.out.println("LaborService.getAllLabors");
    }
}
