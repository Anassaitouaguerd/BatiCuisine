package com.service;
import com.interfaces.ClientInterface;
import com.model.Client;
import com.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientService implements ClientInterface {

    public String createClient(String name , String phone , String address, Boolean isProfessional) {
        String creatClient = new ClientRepository().createClient(new Client(name,address , phone,isProfessional));
        System.out.println("Client "+creatClient+" is created");
        System.out.println("Would you like to continue with this client? (y/n):");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if(choice.equals("y")){
            return creatClient;
        }
        return null;
    }

    public void updateClient() {
    }

    public void deleteClient() {
    }

    public Client getClient(Long clientId) {
        return new ClientRepository().getClientById(clientId);

    }

    public void getAllClients() {
    }

    public static Optional<Client> serchClient(String ClientName) {
        Optional<Client> client = Optional.ofNullable(ClientRepository.getClient(ClientName));
        client.ifPresent(c -> System.out.println("Client " + ClientName + " is found"));
        return client;
    }

    public static int getClientId(String ClientName) {
        return ClientRepository.getClientId(ClientName);
    }
}
