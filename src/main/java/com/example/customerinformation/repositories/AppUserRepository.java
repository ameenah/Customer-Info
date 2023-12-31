package com.example.customerinformation.repositories;

import com.example.customerinformation.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);

//    AppUser saveUser (AppUser user);
////    Role saveRole(Role role);
//    void addRoleToUser(String username, String roleName);
//    AppUser getUser(String username );
////    List<AppUser> getUsers();
}
