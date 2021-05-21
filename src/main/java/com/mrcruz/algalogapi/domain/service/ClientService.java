package com.mrcruz.algalogapi.domain.service;

import com.mrcruz.algalogapi.api.exception.BusinessException;
import com.mrcruz.algalogapi.domain.model.Client;
import com.mrcruz.algalogapi.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClientService {

    private ClientRepository clientRepository;

    public Client findById(Long id){
        return clientRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Cliente não encontrado com id: " + id));
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    @Transactional
    public Client save(Client client){
        if(clientRepository.findByEmail(client.getEmail()).isPresent()){
            throw new BusinessException("E-mail já cadastrado!");
        }
        return clientRepository.save(client);
    }

    @Transactional
    public Client update(Long id, Client client){
        findById(id);
        client.setId(id);
        Optional<Client> clientExists = clientRepository.findByEmail(client.getEmail());

        if(clientExists.isPresent() && (!client.equals(clientExists.get()))){
            throw new BusinessException("E-mail já cadastrado!");
        }
        return clientRepository.save(client);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        clientRepository.deleteById(id);
    }
}
