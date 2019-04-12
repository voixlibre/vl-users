package org.greenwin.VLUsers.controllers;



import org.greenwin.VLUsers.configuration.ApplicationPropertiesConfiguration;
import org.greenwin.VLUsers.entities.AppUser;
import org.greenwin.VLUsers.repositories.AppRoleRepository;
import org.greenwin.VLUsers.repositories.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    ApplicationPropertiesConfiguration configuration;

    @GetMapping("/users")
    public List<AppUser> getAllUsers(){
        logger.info("entering getAllUsers()");
        logger.info("configuration test: " + configuration.getTest());
        return appUserRepository.findAll();

    }

    @GetMapping("/users/{id}")
    public AppUser getUserById(@PathVariable("id") int id){
        AppUser user = appUserRepository.getAppUserById(id);
        List<AppUser> users = new ArrayList<>();
        users.add(user);

        return user;
    }
}
