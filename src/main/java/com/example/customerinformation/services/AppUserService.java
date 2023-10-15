package com.example.customerinformation.services;

import com.example.customerinformation.models.Role;
import com.example.customerinformation.repositories.RoleRepository;
import com.example.customerinformation.models.AppUser;
import com.example.customerinformation.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor //Dependncy Injection : add parameters to constructors automatically
@Transactional @Slf4j
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository ;
    private final PasswordEncoder passwordEncoder ;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username);
        if(user == null){
            log.error("User not found in  DB");
            throw  new UsernameNotFoundException("User not found in  DB");
        }else{
            log.info("User {} found in  DB", username);

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities ) ;
    }



    public AppUser saveUser (AppUser user){
        log.info("Saving new user to DB");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  appUserRepository.save(user);
    }
    public Role saveRole(Role role){
        log.info("Saving new Role {} to DB", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName){
        log.info("Adding Role {} to user {} ", roleName, username);
        AppUser user = appUserRepository.findByUsername((username));
        Role role = roleRepository.findByName(roleName);

        user.getRoles().add(role);
    }
    public AppUser getUser(String username ){
        log.info("Fetching user {}", username);
        return  appUserRepository.findByUsername(username);

    }
    public List<AppUser> getUsers(){
        log.info("Fetching All users");
        return  appUserRepository.findAll();
    }


}
