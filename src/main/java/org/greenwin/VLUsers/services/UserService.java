package org.greenwin.VLUsers.services;

import org.greenwin.VLUsers.entities.AppUser;
import org.greenwin.VLUsers.exceptions.UserAlreadyExistsException;
import org.greenwin.VLUsers.repositories.AppUserRepository;
import org.greenwin.VLUsers.utils.BPwdEncoderUtils;
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
        AppUser user = appUserRepository.getByEmail(email);

        try {
            if (BPwdEncoderUtils.matches(password, user.getPassword()))
                return user;
        }catch (NullPointerException e){
            logger.info("Credentials do not match.");
        }
            return null;
    }

    public AppUser registerUser(AppUser user){
        if (findUserByEmail(user.getEmail()) != null)
            throw new UserAlreadyExistsException();
        user.setPassword(BPwdEncoderUtils.BCryptPassword(user.getPassword()));
        return appUserRepository.save(user);
    }

    public AppUser findUserByEmail(String email){

        return appUserRepository.getByEmail(email);
    }

    public void deleteUser(int id){
        appUserRepository.delete(appUserRepository.getAppUserById(id));
    }

}
