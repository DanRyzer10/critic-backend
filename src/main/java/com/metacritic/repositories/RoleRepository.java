package com.metacritic.repositories;

import com.metacritic.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findRoleById(Long id);
}
