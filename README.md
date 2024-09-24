# Bati-Cuisine

## Project Overview

This Java application is designed to calculate and manage costs for kitchen renovation projects. It provides functionality for managing clients, projects, materials, labor, and quotes. The application uses a console-based user interface for interaction.

## Features

- Client management (add, search, update)
- Project creation and management
- Material and labor cost calculation
- Quote generation and management
- Cost breakdown and total project cost calculation

## Project Structure

The project is structured into several packages:

- `com.baticuisine.UI`: Contains the user interface classes
- `com.baticuisine.service`: Contains service classes that implement business logic
- `com.baticuisine.repository`: Contains repository classes for database operations
- `com.baticuisine.model`: Contains model classes representing the data entities
- `com.baticuisine.interfaces`: Contains interface definitions
- `com.baticuisine.config`: Contains configuration classes (e.g., database connection)

### Key Components

- **UI Classes**: `CalculateCostsUI`, `CalculeCostProjectUI`, `Menu`, `MenuClientSearch`
- **Service Classes**: `ClientService`, `ComponentService`, `LaborService`, `MaterialService`, `ProjectService`, `QuoteService`
- **Repository Classes**: `ClientRepository`, `ComponentRepository`, `LaborRepository`, `MaterialRepository`, `ProjectRepository`, `QuoteRepository`
- **Model Classes**: `Client`, `Component`, `Labor`, `Material`, `Project`, `Quote`
- **Interfaces**: `ClientInterface`, `DevisInterface`, `LaborInterface`, `MaterialInterface`, `ProjectInterface`

## Setup Instructions

1. Ensure you have Java Development Kit (JDK) installed on your system.
2. Clone the repository to your local machine.
3. Set up your