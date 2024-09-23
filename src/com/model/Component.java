package com.model;

import com.enums.CompnentEnum;  // Import the enum

public abstract class Component {
    private Long id;
    private String name;
    private double unitCost;
    private double quantity;
    private CompnentEnum componentType;  // Change from String to CompnentEnum
    private double VATRate;
    private Project project;

    // Constructor for Labor and component
    public Component(String name, Project project,  CompnentEnum componentType) {
        this.name = name;
        this.project = project;
        this.componentType = componentType;
    }

    // Constractor for Labor
    public Component(Long id ,String name,Double VATRate ,Project project , CompnentEnum componentType) {
        this.id = id;
        this.name = name;
        this.VATRate = VATRate;
        this.project = project;
        this.componentType = componentType;
    }

    // Constractor for Material
    public Component(String name, double unitCost, double quantity, Project project,  CompnentEnum componentType) {
        this.name = name;
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.project = project;
        this.componentType = componentType;  // Updated

    }

    // Parameterized constructor
    public Component(Long id, String name, double unitCost, double quantity, CompnentEnum componentType, double VATRate, Project project) {  // Updated componentType
        this.id = id;
        this.name = name;
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.componentType = componentType;
        this.VATRate = VATRate;
        this.project = project;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public CompnentEnum getComponentType() {  // Updated to return enum
        return componentType;
    }

    public void setComponentType(CompnentEnum componentType) {  // Updated setter
        this.componentType = componentType;
    }

    public double getVATRate() {
        return VATRate;
    }

    public void setVATRate(double VATRate) {
        this.VATRate = VATRate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    // toString method
    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitCost=" + unitCost +
                ", quantity=" + quantity +
                ", componentType=" + componentType +  // Updated to print enum
                ", VATRate=" + VATRate +
                ", project=" + project +
                '}';
    }
}
