package com.model;


import com.enums.CompnentEnum;

public class Labor extends Component {
    private double hourlyRate;
    private double workHours;
    private double workerProductivity;
    private String type;

    // Default constructor
//    public Labor() {
//    }

    // Parameterized constructor
    public Labor(String name ,String type , double hourlyRate, double workHours, double workerProductivity , Project project) {
        super(name, project , CompnentEnum.LABOR);
        this.hourlyRate = hourlyRate;
        this.workHours = workHours;
        this.workerProductivity = workerProductivity;
        this.type = type;
    }
    public Labor(Long id ,String name ,String type , double hourlyRate, double workHours, double workerProductivity ,Double VATRate , Project project) {
        super(id ,name, VATRate , project , CompnentEnum.LABOR);
        this.hourlyRate = hourlyRate;
        this.workHours = workHours;
        this.workerProductivity = workerProductivity;
        this.type = type;
    }

    // Getters and setters
    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }

    public double getWorkerProductivity() {
        return workerProductivity;
    }

    public void setWorkerProductivity(double workerProductivity) {
        this.workerProductivity = workerProductivity;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    // toString method
    @Override
    public String toString() {
        return "Labor{" +
                "hourlyRate=" + hourlyRate +
                ", workHours=" + workHours +
                ", workerProductivity=" + workerProductivity +
                '}';
    }

}
