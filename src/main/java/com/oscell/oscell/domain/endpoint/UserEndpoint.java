package com.oscell.oscell.domain.endpoint;

import java.util.List;

import com.oscell.oscell.domain.repository.UserRepository;
import com.oscell.oscell.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oscell.oscell.commons.response.ServiceOrderResponse;
import com.oscell.oscell.domain.User;
import com.oscell.oscell.domain.creation.UserCreation;
import com.oscell.oscell.domain.update.UserUpdate;

@Service
public class UserEndpoint {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUser() {
        return repository.findAll();
    }

    public User getUser(Long sequence) throws Exception {
        try {
            return repository.findById(sequence).get();
        } catch (Exception e) {
            throw new Exception("No user found with the sequence: " + sequence);
        }
    }

    public ServiceOrderResponse<User> createUser(UserCreation userCreation) {
        // Verificação de campos obrigatórios
        if (userCreation.getUserName() == null || userCreation.getUserName().isEmpty()) {
            return ServiceOrderResponse.error("O nome de usuário é obrigatório.");
        }
        if (userCreation.getUserPassword() == null || userCreation.getUserPassword().isEmpty()) {
            return ServiceOrderResponse.error("A senha é obrigatória.");
        }

        if (userRepository.existsByUserName(userCreation.getUserName())) {
            return ServiceOrderResponse.error("Nome de usuário já está em uso.");
        }

        User entity = mapper.map(userCreation);
        // Codificar a senha antes de salvar o usuário
        entity.setUserPassword(passwordEncoder.encode(entity.getUserPassword()));

        try {
            repository.save(entity);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.errorWithContent(entity, e.getMessage());
        }
    }

    public ServiceOrderResponse<User> updateUser(Long sequence, UserUpdate userUpdate) {
        try {
            User entity = getUser(sequence);

            if (!entity.getUserName().equals(userUpdate.getUserName()) && userRepository.existsByUserName(userUpdate.getUserName())) {
                throw new RuntimeException("Nome de usuário já está em uso.");
            }
            if (userUpdate.getUserName() != null) {
                entity.setUserName(userUpdate.getUserName());
            }
            if (userUpdate.getUserPassword() != null) {
                // Codificar a nova senha antes de atualizar o usuário
                entity.setUserPassword(passwordEncoder.encode(userUpdate.getUserPassword()));
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
            repository.delete(entity);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.error(e.getMessage());
        }
    }
}
