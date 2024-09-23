package com.UI;

import com.model.Client;
import com.model.Labor;
import com.model.Material;
import com.model.Project;
import com.service.ComponentService;
import com.service.ProjectService;

import java.util.List;
import java.util.ArrayList;

public class CalculateCostsUI {

    public static void calculateCosts(Long lastProjectId , Client client , Project project  ) {
        List<Object> components = new ComponentService().getComponent(lastProjectId);
        // Separate Labor and Material
        List<Material> materials = new ArrayList<>();
        List<Labor> labors = new ArrayList<>();

        for (Object component : components) {
            if (component instanceof Material) {
                materials.add((Material) component); // Add to Material list
            } else if (component instanceof Labor) {
                labors.add((Labor) component); // Add to Labor list
            }
        }
        System.out.println("---- Client ----");
        System.out.println("ID: " + client.getId());
        System.out.println("Name: " + client.getName());
        System.out.println("Phone: " + client.getPhone());
        System.out.println("Address: " + client.getAddress());
        System.out.println("Is Professional: " + client.isProfessional());
        System.out.println("Surface: " + project.getSurface());
        System.out.println("-------------------\n");

        // Display Materials
        double totalMaterialCost = 0;
        double totalMaterialCostWithVAT = 0;
        System.out.println("---- Materials ----");
        for (Material material : materials) {
            System.out.println("ID: " + material.getId());
            System.out.println("Name: " + material.getName());
            System.out.println("Unit Cost: " + material.getUnitCost());
            System.out.println("Quantity: " + material.getQuantity());
            System.out.println("Transport Cost: " + material.getTransportCost());
            System.out.println("Quality Coefficient: " + material.getQualityCoefficient());
            System.out.println("VAT Rate: " + material.getVATRate());
            System.out.println("Project ID: " + material.getProject().getId());
            System.out.println("-------------------");
            Double totalCostMateriale = totalCostMateriale(material.getQuantity(), material.getUnitCost(), material.getTransportCost(), material.getQualityCoefficient());
            Double totalCostMaterialWithVAT = calculeTva(totalCostMateriale, material.getVATRate());
            System.out.println("Total Cost: " + totalCostMateriale);
            System.out.println("Total Cost with VAT: " + totalCostMaterialWithVAT);
            System.out.println("-------------------\n");
            totalMaterialCost += totalCostMateriale;
            totalMaterialCostWithVAT += totalCostMaterialWithVAT;
        }

        //Totale cost materials
        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│         Total All Materials Cost          │");
        System.out.println("└───────────────────────────────────────────┘");
        System.out.println("Total Material Cost: " + totalMaterialCost);
        System.out.println("Total Material Cost with VAT: " + totalMaterialCostWithVAT);
        System.out.println("-------------------\n");

        // Display Labors
        double totalLaborCost = 0;
        double totalLaborCostWithVAT = 0;
        System.out.println("---- Labors ----");
        for (Labor labor : labors) {
            System.out.println("ID: " + labor.getId());
            System.out.println("Name: " + labor.getName());
            System.out.println("Labor Type: " + labor.getType());
            System.out.println("Hourly Rate: " + labor.getHourlyRate());
            System.out.println("Work Hours: " + labor.getWorkHours());
            System.out.println("Worker Productivity: " + labor.getWorkerProductivity());
            System.out.println("VAT Rate: " + labor.getVATRate());
            System.out.println("Project ID: " + labor.getProject().getId());
            System.out.println("-------------------");
            Double totalCostLabor = totalCostLabor(labor.getHourlyRate(), labor.getWorkHours(), labor.getWorkerProductivity());
            Double totalCostLaborWithVAT = calculeTva(totalCostLabor, labor.getVATRate());

            System.out.println("Total Cost: " + totalCostLabor);
            System.out.println("Total Cost with VAT: " + totalCostLaborWithVAT);
            System.out.println("-------------------\n");
            totalLaborCost += totalCostLabor;
            totalLaborCostWithVAT += totalCostLaborWithVAT;
        }
        System.out.println("┌────────────────────────────────────────┐");
        System.out.println("│         Total All Labors Cost          │");
        System.out.println("└────────────────────────────────────────┘");
        System.out.println("Total Labor Cost: " + totalLaborCost);
        System.out.println("Total Labor Cost with VAT: " + totalLaborCostWithVAT);
        System.out.println("-------------------\n");

        // Display grand totals
        System.out.println("┌────────────────────────────────────┐");
        System.out.println("│            Grand Totals            │");
        System.out.println("└────────────────────────────────────┘");
        System.out.println("Total Project Cost (without VAT): " + (totalMaterialCost + totalLaborCost));
        System.out.println("Total Project Cost (with VAT): " + (totalMaterialCostWithVAT + totalLaborCostWithVAT));
        new ProjectService().updateProject((totalMaterialCost + totalLaborCost) , lastProjectId);
        System.out.println("\n");

    }

    public static Double totalCostMateriale(Double quantity , Double unitCost , Double transportCost , Double qualityCoefficient) {
        return ((quantity * unitCost) * qualityCoefficient) + transportCost;
    }
    public static Double calculeTva(Double totalCost , Double vatRate) {
        double tva = vatRate / 100;
        return totalCost * (tva + 1);
    }
    public static Double totalCostLabor(Double hourlyRate , Double workHours , Double workerProductivity) {
        return hourlyRate * workHours * workerProductivity;
    }
}
