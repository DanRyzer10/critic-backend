package com.metacritic.repositories;

import com.metacritic.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findUserById(Long id);
    User findUserByEmail(String email);
    User findUserByUsername(String  username);
    User findUserByEmailAndPassword(String email,String password);

}
