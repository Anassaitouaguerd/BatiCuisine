package com.service;

import com.interfaces.MaterialInterface;
import com.model.Material;
import com.model.Project;
import com.repository.ComponentRepository;
import com.repository.MaterialRepository;

public class MaterialService implements MaterialInterface {
    public void createMaterial(String name, double quantity, double unitCost,
                               double transportCost, double qualityCoefficient, long projectId) {

        // Create the Project object
        Project project = new Project(projectId);

        // Create the Material object
        Material material = new Material(name, unitCost, quantity, project, transportCost, qualityCoefficient);

        // Insert into Component table (likely done via a repository for components)
        long lastComponentInserted = new ComponentRepository().createComponentMaterial(material);

        // Insert into Material table (likely done via a separate repository for materials)
        new MaterialRepository().createMaterial(material , lastComponentInserted);
    }

//
//    public void updateMaterial() {
//    }
//
//    public void deleteMaterial() {
//    }
//
//    public void getMaterial() {
//    }
//
//    public void getAllMaterials() {
//    }

    @Override
    public void createMatiere() {

    }

    @Override
    public void updateMatiere() {

    }

    @Override
    public void deleteMatiere() {

    }

    @Override
    public void getMatiere() {

    }

    @Override
    public void getAllMatieres() {

    }
}
