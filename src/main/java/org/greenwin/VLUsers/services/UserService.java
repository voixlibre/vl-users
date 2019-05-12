package org.greenwin.VLUsers.services;

import org.greenwin.VLUsers.entities.AppUser;
import org.greenwin.VLUsers.repositories.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    AppUserRepository appUserRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public AppUser getUserIfCorrectCredits(String email, String password){
        AppUser user = appUserRepository.findByEmail(email);

        try {
            if(user.getPassword().equals(password))
                return user;
        }catch (NullPointerException e){
            logger.info("Credentials do not match.");
        }
        return null;
    }

}
