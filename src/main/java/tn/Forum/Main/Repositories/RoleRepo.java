package tn.Forum.Main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Forum.Main.Models.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{

	Role findFirstByUserRole(String userRole);
}
