package com.neoris.admintest.service;

import com.neoris.admintest.model.Client;
import com.neoris.admintest.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private IClientRepository iClientRepository;

    public Client saveClient( Long userId, Client client ){
        if (client != null || userId != null){
            client.setUsuario_id(userId);

            String rawPassword = client.getContrasena();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(rawPassword);

            client.setContrasena(hashedPassword);

            return iClientRepository.save(client);
        }
        return null;
    }

    public Page<Client> getAllClients( Integer page, Integer size, Boolean enablePagination ){
        return iClientRepository.findAll(enablePagination ? PageRequest.of(page, size) : Pageable.unpaged());
    }

    public Optional<Client> findById( Long id ){
        return iClientRepository.findById(id);
    }

    public void deleteStudent( Long id ){
        iClientRepository.deleteById(id);
    }

    public Client editClient( Client client ){
        if (client.getCliente_id() != null && iClientRepository.existsById(client.getCliente_id())){
            return iClientRepository.save(client);
        }
        return client;
    }

    public Object existById( Long id ) {
        return iClientRepository.existsById(id);
    }
}
