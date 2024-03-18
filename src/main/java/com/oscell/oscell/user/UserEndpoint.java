package com.oscell.oscell.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oscell.oscell.commons.response.ServiceOrderResponse;
import com.oscell.oscell.user.domain.User;
import com.oscell.oscell.user.domain.UserCreation;
import com.oscell.oscell.user.domain.UserUpdate;

@Service
public class UserEndpoint {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    private final UserRepository userRepository;

    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUser() {
        return repository.findAll();
    }

    public User getUser(Long sequence) throws Exception{
        try{
            return repository.findById(sequence).get();
        }catch(Exception e){
            throw new Exception("No user found with the sequence: " + sequence);
        }
    }

    public ServiceOrderResponse<User> createUser(UserCreation userCreation) {
        if (userRepository.existsByUserName(userCreation.getUserName())) {
            return ServiceOrderResponse.error("Nome de usuário já está em uso.");
        }
    
        User entity = mapper.map(userCreation);
        try {
            repository.save(entity);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.errorWithContent(entity, e.getMessage());
        }
    }    

    public ServiceOrderResponse<User> updateUser(String searchKey, String searchValue, UserUpdate userUpdate) {
        try {  
            User entity;

            if ("sequence".equalsIgnoreCase(searchKey)) {
                Long sequence = Long.parseLong(searchValue);
                entity = getUser(sequence);
            } else if ("userName".equalsIgnoreCase(searchKey)) {
                entity = userRepository.findByUserName(searchValue);
                if (entity == null) {
                    throw new RuntimeException("Usuário não encontrado.");
                }
            } else {
                throw new IllegalArgumentException("Chave de pesquisa inválida. Use 'Número de Sequência' ou 'Nome do Usuário'.");
            }
    
            if (!entity.getUserName().equals(userUpdate.getUserName()) && userRepository.existsByUserName(userUpdate.getUserName())) {
                throw new RuntimeException("Nome de usuário já está em uso.");
            }
            if (userUpdate.getUserName() != null) {
                entity.setUserName(userUpdate.getUserName());
            }
            if (userUpdate.getUserPassword() != null) {
                entity.setUserPassword(userUpdate.getUserPassword());
            }
            repository.save(entity);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.errorWithContent(mapper.map(userUpdate), e.getMessage());
        }
    }
    
    public ServiceOrderResponse<User> deleteUser(Long sequence) {
        try {
            User entity = getUser(sequence);
            repository.deleteById(sequence);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.error(e.getMessage());
        }
    }
}
