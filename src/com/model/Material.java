package com.model;

import com.enums.CompnentEnum;

public class Material extends Component {
    private double transportCost;
    private double qualityCoefficient;

    // Default constructor

    // Parameterized constructor
    public Material(String name, double unitCost, double quantity, Project project,
                    double transportCost, double qualityCoefficient) {
        super(name, unitCost, quantity, project , CompnentEnum.MATERIAL);
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }


    public Material(Long id, String name, double unitCost, double quantity, double VATRate, Project project,
                    double transportCost, double qualityCoefficient) {
        super(id, name, unitCost, quantity, CompnentEnum.MATERIAL, VATRate, project);
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }
    // Getters and setters
    public double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    public double getQualityCoefficient() {
        return qualityCoefficient;
    }

    public void setQualityCoefficient(double qualityCoefficient) {
        this.qualityCoefficient = qualityCoefficient;
    }

    // toString method
    @Override
    public String toString() {
        return "Material{" +
                "transportCost=" + transportCost +
                ", qualityCoefficient=" + qualityCoefficient +
                '}';
    }
}
