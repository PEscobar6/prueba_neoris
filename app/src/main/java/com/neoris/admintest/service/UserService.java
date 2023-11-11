package com.neoris.admintest.service;

import com.neoris.admintest.model.User;
import com.neoris.admintest.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository iUserRepository;

    public User saveUser(User user){
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }
        return iUserRepository.save(user);
    }

    public Page<User> getAllPerson(Integer page, Integer size, Boolean enablePagination ){
        return iUserRepository.findAll(enablePagination ? PageRequest.of(page, size) : Pageable.unpaged());
    }

    public Optional<User> findById(Long id ){
        return iUserRepository.findById(id);
    }

    public void deletePerson( Long id ){
        iUserRepository.deleteById(id);
    }

    public User editUser(User user){
        if (user.getUser_id() != null && iUserRepository.existsById(user.getUser_id())){
            return iUserRepository.save(user);
        }
        return user;
    }

    public Object existById( Long id ) {
        return iUserRepository.existsById(id);
    }
}
