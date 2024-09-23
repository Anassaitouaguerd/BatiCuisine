package com.interfaces;

import com.model.Client;

public interface ClientInterface {

    public String createClient(String name , String phone , String address, Boolean isProfessional);

    public void updateClient();

    public void deleteClient();

    public Client getClient(Long clientId);

    public void getAllClients();

}
