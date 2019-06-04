package org.greenwin.VLUsers.controllers;

import org.greenwin.VLUsers.configuration.ApplicationPropertiesConfiguration;
import org.greenwin.VLUsers.entities.AppUser;
import org.greenwin.VLUsers.exceptions.UserAlreadyExistsException;
import org.greenwin.VLUsers.exceptions.UserNotFoundException;
import org.greenwin.VLUsers.repositories.AppRoleRepository;
import org.greenwin.VLUsers.repositories.AppUserRepository;
import org.greenwin.VLUsers.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    UserService userService;

    @Autowired
    ApplicationPropertiesConfiguration configuration;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser login(@RequestBody AppUser user){
        return  userService.getUserIfCorrectCredits(user.getEmail(), user.getPassword());
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<AppUser> getAllUsers(){
        return appUserRepository.findAll();
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser getUserById(@PathVariable("id") int id){
        AppUser user = appUserRepository.getAppUserById(id);
        List<AppUser> users = new ArrayList<>();
        users.add(user);
        return user;
    }

    @GetMapping("/email/{email}")
    public AppUser getUserByEmail(@PathVariable("email") String email){
        AppUser user = appUserRepository.getByEmail(email);
        return user;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser saveUser(@RequestBody AppUser appUser){
        try {
            return userService.registerUser(appUser);
        }catch (UserAlreadyExistsException e){
            throw new UserAlreadyExistsException();
        }

    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser updateUser(@RequestBody AppUser user){
        AppUser appUser = appUserRepository.getAppUserById(user.getId());
        user.setPassword(appUser.getPassword());
        return appUserRepository.save(user);
    }

    @GetMapping("/find/{email}")
    public AppUser findUser(@PathVariable ("email") String email) throws UserNotFoundException {
        AppUser user = userService.findUserByEmail(email);
        if (user == null)
            throw new UserNotFoundException();
        return user;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        userService.deleteUser(id);
    }



}
