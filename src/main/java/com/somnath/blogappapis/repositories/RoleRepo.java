package com.somnath.blogappapis.repositories;

import com.somnath.blogappapis.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
