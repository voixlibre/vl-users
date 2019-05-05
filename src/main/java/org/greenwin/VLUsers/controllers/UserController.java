package org.greenwin.VLUsers.controllers;

import org.greenwin.VLUsers.configuration.ApplicationPropertiesConfiguration;
import org.greenwin.VLUsers.entities.AppUser;
import org.greenwin.VLUsers.repositories.AppRoleRepository;
import org.greenwin.VLUsers.repositories.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    ApplicationPropertiesConfiguration configuration;

    @GetMapping("/")
    public List<AppUser> getAllUsers(){
        logger.info("entering getAllUsers()");
        logger.info("configuration test: " + configuration.getTest());
        return appUserRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public AppUser getUserById(@PathVariable("id") int id){
        AppUser user = appUserRepository.getAppUserById(id);
        List<AppUser> users = new ArrayList<>();
        users.add(user);
        return user;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser saveUser(@RequestBody AppUser appUser){
        return appUserRepository.save(appUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable ("id") int id){
        appUserRepository.delete(appUserRepository.getAppUserById(id));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser updateUser(@RequestBody AppUser user){
        AppUser appUser = appUserRepository.getAppUserById(user.getId());
        user.setPassword(appUser.getPassword());
        return appUserRepository.save(user);
    }

}
