package com.oscell.oscell.user;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oscell.oscell.commons.response.ServiceOrderResponse;
import com.oscell.oscell.user.domain.User;
import com.oscell.oscell.user.domain.UserCreation;
import com.oscell.oscell.user.domain.UserUpdate;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

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
    
    public String authenticateUser(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (user != null && user.getUserPassword().equals(password)) {
            System.out.println(user.getUserName() + user.getUserPassword());
            return generateJwtToken(user); // Gera e retorna o token JWT
        } else {
            throw new RuntimeException("Credenciais inválidas.");
        }
    }

    public String generateJwtToken(User user) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + 3600000; // Token válido por 1 hora
        Date exp = new Date(expMillis);

        // Geração da chave secreta (substitua "JVS29032004" pela sua chave secreta)
        Key key = Keys.hmacShaKeyFor("JVS29032004".getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
            .setSubject(user.getUserName())
            .setIssuedAt(now)
            .setExpiration(exp)
            .signWith(key, SignatureAlgorithm.HS512) // Use a chave Key aqui
            .compact();
    }

    public Long getUserSequence(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user != null) {
            return user.getSequence(); // Supondo que 'getSequence' seja o método para obter o número de sequência do usuário
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado com o nome: " + userName);
        }
    }
}
