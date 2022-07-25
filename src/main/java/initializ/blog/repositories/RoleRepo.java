package initializ.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import initializ.blog.entities.Role;

public interface RoleRepo extends  JpaRepository<Role, Integer>{

}
