package com.metacritic.services;

import com.metacritic.domain.Role;
import com.metacritic.domain.User;
import com.metacritic.domain.dtos.CreateUserDTO;
import com.metacritic.domain.dtos.SIgnInDTO;
import com.metacritic.domain.dtos.UpdateUserDTO;
import com.metacritic.repositories.RoleRepository;
import com.metacritic.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User findByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
    public User findByEmailAndPassword(String email,String password){
        User user = userRepository.findUserByEmail(email);
        if(user!=null){
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    @Transactional
    public User save(CreateUserDTO user){
        Role role = roleRepository.findById(2L).orElseThrow(()-> new IllegalArgumentException("rol no encontrado"));
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setRole(role);
        newUser.setCreatedAt(java.time.LocalDateTime.now());
        newUser.setUsername(user.getEmail());
        return userRepository.save(newUser);
    }
    //actualizar no disponible
    public User update(UpdateUserDTO user, Long id){
        User userToUpdate = userRepository.findById(id).orElse(null);
        if(userToUpdate!=null){
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPassword(user.getPassword());
            return userRepository.save(userToUpdate);
        }
        return null;
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }

   public User findByEmailAndPassword(SIgnInDTO user){
       return userRepository.findUserByEmailAndPassword(user.getEmail(),user.getPassword());
   }

}
