package com.UI;

import com.model.Client;
import com.model.Component;
import com.model.Project;
import com.service.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuClientSearch {
    // Constants for the menu
    private static final int SEARCH_CLIENT = 1;
    private static final int ADD_CLIENT = 2;
    private static final int EXIT = 3;

    // Search for a client or add a new one
    public static void searchClient() {
        System.out.println("Would you like to search for an existing customer or add a new one?");
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            if (choice == EXIT) {
                System.out.println("Exiting");
                break;
            }
            handleChoice(choice);
        }
    }

    // Display the menu
    private static void displayMenu() {
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│           Client Menu               │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│ 1. Search for an existing customer  │");
        System.out.println("│ 2. Add a new customer               │");
        System.out.println("│ 3. Exit                             │");
        System.out.println("└─────────────────────────────────────┘");
    }

    // Get user input for searching to display the client
    private static String displaySearchInput() {
    Scanner scanner = new Scanner(System.in);
    String name;
    while (true) {
        System.out.println("Enter the customer name: ");
        name = scanner.nextLine().trim();
        if (name.isEmpty() || name.matches(".*\\d.*")) {
            System.out.println("Invalid input. Name cannot be empty or contain numbers. Please try again.");

        } else {
            break;
        }
    }
    return name;
}

    // Get user choice
    private static int getUserChoice() {
        int choice = 0;
        try {
            choice = Integer.parseInt(System.console().readLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
        }
        return choice;
    }

    // Menu to entre info for  new client
    private static List<Object> displayCreateClientMenu() {
    Scanner scanner = new Scanner(System.in);
    String name;
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│        New Client Information       │");
        System.out.println("└─────────────────────────────────────┘");
    while (true) {
        System.out.println("Enter the client name: ");
        name = scanner.nextLine().trim();
        if (name.isEmpty() || name.matches(".*\\d.*")) {
            System.out.println("Invalid input. Name cannot be empty or contain numbers. Please try again.");
        } else {
            break;
        }
    }

    String phoneNumber;
    while (true) {
        System.out.println("Enter the client phone number: ");
        phoneNumber = scanner.nextLine().trim();
        if (!phoneNumber.matches("\\d{10}")) {
            System.out.println("Invalid input. Phone number must be 10 digits. Please try again.");
        } else {
            break;
        }
    }

    String address;
    while (true) {
        System.out.println("Enter the client address: ");
        address = scanner.nextLine().trim();
        if (address.isEmpty()) {
            System.out.println("Invalid input. Address cannot be empty. Please try again.");
        } else {
            break;
        }
    }

    boolean isProfessional;
    while (true) {
        System.out.println("Is this client professional? (yes/no)");
        String isProfessionalInput = scanner.nextLine().trim().toLowerCase();
        if (isProfessionalInput.equals("yes")) {
            isProfessional = true;
            break;
        } else if (isProfessionalInput.equals("no")) {
            isProfessional = false;
            break;
        } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
        }
    }

    return List.of(name, phoneNumber, address, isProfessional);
}

    // Display the inputs of creation new  project
    private List<Object> displayInputsOfProject() {
    Scanner scanner = new Scanner(System.in);
    String name;
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│        New Project Information      │");
        System.out.println("└─────────────────────────────────────┘");
    while (true) {
        System.out.println("Enter the project name: ");
        name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Invalid input. Project name cannot be empty. Please try again.");
        } else {
            break;
        }
    }

    double kitchenArea;
    while (true) {
        System.out.println("Enter the kitchen area (in m²): ");
        if (scanner.hasNextDouble()) {
            kitchenArea = scanner.nextDouble();
            if (kitchenArea <= 0) {
                System.out.println("Invalid input. Kitchen area must be a positive number. Please try again.");
            } else {
                break;
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number for the kitchen area.");
            scanner.next(); // clear the invalid input
        }
    }

    return List.of(name, kitchenArea);
}

    // Display the inputs of creation new material
    private List<Object> displayInputsOfMaterial() {
    Scanner scanner = new Scanner(System.in);

        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│       New Material Information      │");
        System.out.println("└─────────────────────────────────────┘");

    String name;
    while (true) {
        System.out.println("Enter the material name: ");
        name = scanner.nextLine().trim();
        if (name.isEmpty() || name.matches(".*\\d.*")) {
            System.out.println("Invalid input. Name cannot be empty or contain numbers. Please try again.");
        } else {
            break;
        }
    }

    double quantity;
    while (true) {
        System.out.println("Enter the quantity of this material (in m²): ");
        if (scanner.hasNextDouble()) {
            quantity = scanner.nextDouble();
            if (quantity <= 0) {
                System.out.println("Invalid input. Quantity must be a positive number. Please try again.");
            } else {
                break;
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number for the quantity.");
            scanner.next(); // clear the invalid input
        }
    }

    double unitCost;
    while (true) {
        System.out.println("Enter the unit cost of this material (€/m²): ");
        if (scanner.hasNextDouble()) {
            unitCost = scanner.nextDouble();
            if (unitCost <= 0) {
                System.out.println("Invalid input. Unit cost must be a positive number. Please try again.");
            } else {
                break;
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number for the unit cost.");
            scanner.next(); // clear the invalid input
        }
    }

    double transportCost;
    while (true) {
        System.out.println("Enter the cost of transporting this material (€): ");
        if (scanner.hasNextDouble()) {
            transportCost = scanner.nextDouble();
            if (transportCost < 0) {
                System.out.println("Invalid input. Transport cost cannot be negative. Please try again.");
            } else {
                break;
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number for the transport cost.");
            scanner.next(); // clear the invalid input
        }
    }

    double qualityCoefficient;
    while (true) {
        System.out.println("Enter the material quality coefficient (1.0 = standard, > 1.0 = high quality): ");
        if (scanner.hasNextDouble()) {
            qualityCoefficient = scanner.nextDouble();
            if (qualityCoefficient < 1.0) {
                System.out.println("Invalid input. Quality coefficient must be 1.0 or higher. Please try again.");
            } else {
                break;
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number for the quality coefficient.");
            scanner.next(); // clear the invalid input
        }
    }

    return List.of(name, quantity, unitCost, transportCost, qualityCoefficient);
}

    // set inputs to add new labor to the project
    private List<Object> displayInputsOfLabor() {
    Scanner scanner = new Scanner(System.in);
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│         New Labor Information       │");
        System.out.println("└─────────────────────────────────────┘");
    String name;
    while (true) {
        System.out.println("Enter the labor name: ");
        name = scanner.nextLine().trim();
        if (name.isEmpty() || name.matches(".*\\d.*")) {
            System.out.println("Invalid input. Name cannot be empty or contain numbers. Please try again.");
        } else {
            break;
        }
    }

    String type;
    while (true) {
        System.out.println("Enter the labor type (Basic Worker, Specialist): ");
        type = scanner.nextLine().trim();
        if (type.isEmpty() || (!type.equalsIgnoreCase("Basic Worker") && !type.equalsIgnoreCase("Specialist"))) {
            System.out.println("Invalid input. Type must be 'Basic Worker' or 'Specialist'. Please try again.");
        } else {
            break;
        }
    }

    double hourlyRate;
    while (true) {
        System.out.println("Enter the hourly rate for this labor (€/h): ");
        if (scanner.hasNextDouble()) {
            hourlyRate = scanner.nextDouble();
            if (hourlyRate <= 0) {
                System.out.println("Invalid input. Hourly rate must be a positive number. Please try again.");
            } else {
                break;
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number for the hourly rate.");
            scanner.next(); // clear the invalid input
        }
    }

    double workHours;
    while (true) {
        System.out.println("Enter the number of hours worked: ");
        if (scanner.hasNextDouble()) {
            workHours = scanner.nextDouble();
            if (workHours <= 0) {
                System.out.println("Invalid input. Work hours must be a positive number. Please try again.");
            } else {
                break;
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number for the work hours.");
            scanner.next(); // clear the invalid input
        }
    }

    double workerProductivity;
    while (true) {
        System.out.println("Enter the productivity factor (1.0 = standard, > 1.0 = high productivity): ");
        if (scanner.hasNextDouble()) {
            workerProductivity = scanner.nextDouble();
            if (workerProductivity < 1.0) {
                System.out.println("Invalid input. Productivity factor must be 1.0 or higher. Please try again.");
            } else {
                break;
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number for the productivity factor.");
            scanner.next(); // clear the invalid input
        }
    }

    return List.of(name, type, hourlyRate, workHours, workerProductivity);
}

    // set inputs to calculate the total cost of the project
private List<Object> displayInputsTotalCost() {
    Scanner scanner = new Scanner(System.in);
    Double vat = null;
    Double profitMargin = null;
    System.out.println("┌─────────────────────────────────────┐");
    System.out.println("│       Project Cost Information      │");
    System.out.println("└─────────────────────────────────────┘");

    System.out.println("Would you like to apply VAT to the project? (y/n): ");
    String vatChoice = scanner.nextLine().trim().toLowerCase();
    if (vatChoice.equals("y")) {
        System.out.println("Enter the VAT percentage (%): ");
        try {
            vat = scanner.nextDouble();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input for VAT percentage. Please enter a valid number.");
            scanner.nextLine(); // clear the invalid input
        }
    }

    System.out.println("Would you like to apply a profit margin to the project? (y/n): ");
    String profitMarginChoice = scanner.nextLine().trim().toLowerCase();
    if (profitMarginChoice.equals("y")) {
        System.out.println("Enter the profit margin percentage (%): ");
        try {
            profitMargin = scanner.nextDouble();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input for profit margin percentage. Please enter a valid number.");
            scanner.nextLine(); // clear the invalid input
        }
    }

    if (vat == null || profitMargin == null) {
        return null;
    }

    return List.of(vat, profitMargin);
}

    // set inputs for saving quote
    private List<Object> displayInputsSavingQuote() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│         Quote Registration          │");
        System.out.println("└─────────────────────────────────────┘");
        String issueDate;
        while (true) {
            System.out.println("Enter the date the quote was issued (format: dd/mm/yyyy) : ");
            issueDate = scanner.nextLine().trim();
            if (issueDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter the date in the format dd/mm/yyyy.");
            }
        }

        String validityDate;
        while (true) {
            System.out.println("Enter the validity date of the quote (format: dd/mm/yyyy) : ");
            validityDate = scanner.nextLine().trim();
            if (validityDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter the date in the format dd/mm/yyyy.");
            }
        }
        return List.of(issueDate, validityDate);
    }

    // Handle the new project creation
    private void handleProjectCreation(String clientName) {
    List<Object> projectInfo = displayInputsOfProject();
    long clientId = ClientService.getClientId(clientName);

    // create new project and also get the last id is inserted in the database
    long createProject = new ProjectService().createProject(
            (String) projectInfo.get(0),
            (double) projectInfo.get(1), clientId);
    long lastProjectId = createProject;

    // Create new material for the project
    handleMaterialCreation(lastProjectId);

    // Ask the user if he wants to add another material
    System.out.println("Do you want to add another material? (y/n)");
    Scanner scanner = new Scanner(System.in);
    String choice = scanner.nextLine().trim().toLowerCase();
    while (choice.equals("y")) {
        handleMaterialCreation(lastProjectId);
        System.out.println("Do you want to add another material? (y/n)");
        choice = scanner.nextLine().trim().toLowerCase();
    }

    // Create new labor for the project
    handleLaborCreation(lastProjectId);
    System.out.println("Do you want to add another type of labor? (y/n)");
    String laborChoice = scanner.nextLine().trim().toLowerCase();
    while (laborChoice.equals("y")) {
        handleLaborCreation(lastProjectId);
        System.out.println("Do you want to add another type of labor? (y/n)");
        laborChoice = scanner.nextLine().trim().toLowerCase();
    }
        System.out.println("Calculating the total cost of the project...");
        handleTotalCost(lastProjectId);

    // Saving new quote
        handleSavingQuote(lastProjectId);

}

    // Handle creation of material
    private void handleMaterialCreation(long lastProjectId) {
        List<Object> materialInfo = displayInputsOfMaterial();
        new MaterialService().createMaterial(
                (String) materialInfo.get(0),
                (double) materialInfo.get(1),
                (double) materialInfo.get(2),
                (double) materialInfo.get(3),
                (double) materialInfo.get(4), lastProjectId
        );
    }

    // Handle creation of labor
    private void handleLaborCreation(long lastProjectId) {
        List<Object> laborInfo = displayInputsOfLabor();
        new LaborService().createLabor(
                (String) laborInfo.get(0),
                (String) laborInfo.get(1),
                (double) laborInfo.get(2),
                (double) laborInfo.get(3),
                (double) laborInfo.get(4), lastProjectId
        );
    }

    private void handleTotalCost(long lastProjectId) {
        List<Object> totalCost = displayInputsTotalCost();
        if (totalCost != null) {
            new ComponentService().updateComponent(
                    (double) totalCost.get(0), lastProjectId
            );
        }
        if (totalCost != null) {
            new ProjectService().profitMargin(
                    (double) totalCost.get(1), lastProjectId
            );
        }
        Project project = new ProjectService().getProject(lastProjectId);
        if (project != null) {
            Client client = new ClientService().getClient(project.getClient().getId());
            if (client != null) {
                CalculateCostsUI.calculateCosts(lastProjectId, client , project);
            }

        }

    }
   public void handleSavingQuote(long lastProjectId) {
       System.out.println("Would you like to save the quote? (y/n) : ");
         Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("y")) {
                List <Object> quoteInfo = displayInputsSavingQuote();
                new QuoteService().createQuote(
                        LocalDate.parse((String) quoteInfo.get(0), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        LocalDate.parse((String) quoteInfo.get(1), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        lastProjectId
                );
            }
}

    // Handle the user choice
    private static void handleChoice(int choice) {
        switch (choice) {
            case SEARCH_CLIENT:
                String name = displaySearchInput();
                Optional<Client> client = ClientService.serchClient(name);
                if (client.isPresent()) {
                    displayClientInfo(client.get());
                    System.out.println("Would you like to continue with this client? (y/n): ");
                    Scanner scanner = new Scanner(System.in);
                    String choiceContinue = scanner.nextLine();
                    if (choiceContinue.equals("y")) {
                        new MenuClientSearch().handleProjectCreation(name);
                    }
                }else {
                    System.out.println("Client not found.");
                }
                break;
            case ADD_CLIENT:
                List<Object> clientInfo = displayCreateClientMenu();
                String createClient = new ClientService().createClient(
                        (String) clientInfo.get(0),
                        (String) clientInfo.get(1),
                        (String) clientInfo.get(2),
                        (Boolean) clientInfo.get(3)
                );
                if (createClient != null) {
                    System.out.println("Continuing with client " + createClient);
                    new MenuClientSearch().handleProjectCreation(createClient);
                }
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
    private static void displayClientInfo(Client client) {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│                     Client Information                      │");
        System.out.println("├───────────────────┬─────────────────────────────────────────┤");
        System.out.printf("│ Name              │ %-37s │%n", client.getName());
        System.out.printf("│ Phone             │ %-37s │%n", client.getPhone());
        System.out.printf("│ Address           │ %-37s │%n", client.getAddress());
        System.out.printf("│ Is Professional   │ %-37s │%n", client.isProfessional() ? "Yes" : "No");
        System.out.println("└───────────────────┴─────────────────────────────────────────┘");
    }
}