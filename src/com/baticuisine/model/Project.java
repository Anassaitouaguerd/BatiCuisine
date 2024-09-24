package com.baticuisine.model;

import java.util.List;

public class Project {
    private Long id;
    private String projectName;
    private double profitMargin;
    private double surface;
    private double totalCost;
    private ProjectStatus projectStatus;
    private Client client;
    private List<Component> components;
    private Quote quote;

    // Enum for ProjectStatus
    public enum ProjectStatus {
        IN_PROGRESS, COMPLETED, CANCELLED
    }
    // Default constructor
    public Project() {
    }
    // constructor
    public Project(String projectName , double surface , Long clientId) {
        this.projectName = projectName;
        this.surface = surface;
        this.client = new Client(clientId);
    }
    public Project(long projectId){
        this.id = projectId;
    }

    public Project(Long id, String projectName, double profitMargin, double totalCost, ProjectStatus projectStatus, double surface,Client client){
        this.id = id;
        this.projectName = projectName;
        this.profitMargin = profitMargin;
        this.totalCost = totalCost;
        this.projectStatus = projectStatus;
        this.surface = surface;
        this.client = client;
    }
    // Parameterized constructor
    public Project(Long id, String projectName, double profitMargin, double totalCost, ProjectStatus projectStatus, Client client, List<Component> components, Quote quote) {
        this.id = id;
        this.projectName = projectName;
        this.profitMargin = profitMargin;
        this.totalCost = totalCost;
        this.projectStatus = projectStatus;
        this.client = client;
        this.components = components;
        this.quote = quote;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }
    public double getSurface() {
        return surface;
    }
    public void setSurface(double surface) {
        this.surface = surface;
    }

    // toString method
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", profitMargin=" + profitMargin +
                ", totalCost=" + totalCost +
                ", projectStatus=" + projectStatus +
                ", client=" + client +
                ", components=" + components +
                ", quote=" + quote +
                '}';
    }

}
