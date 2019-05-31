package org.greenwin.VLUsers;

import org.greenwin.VLUsers.configuration.ApplicationPropertiesConfiguration;
import org.greenwin.VLUsers.entities.AppRole;
import org.greenwin.VLUsers.entities.AppUser;
import org.greenwin.VLUsers.repositories.AppRoleRepository;
import org.greenwin.VLUsers.repositories.AppUserRepository;
import org.greenwin.VLUsers.utils.BPwdEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableFeignClients("org.greenwin")
@EnableDiscoveryClient
public class VlUsersApplication implements CommandLineRunner {

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ApplicationPropertiesConfiguration configuration;

	public static void main(String[] args) {
		SpringApplication.run(VlUsersApplication.class, args);
	}


    @Override
    public void run(String... args) throws Exception {


	    AppRole admin = new AppRole( "ADMIN");
        AppRole autor = new AppRole( "AUTOR");
        AppRole journalist = new AppRole( "JOURNALIST");
        AppRole deliverer = new AppRole( "STATISTICIAN");

        AppUser julien = new AppUser("juliencauwet@yahoo.fr", BPwdEncoderUtils.BCryptPassword("12345"), "Julien", "Cauwet", "+33652520263", null, null);
        AppUser eric = new AppUser("dhalluine@hotmail.fr", BPwdEncoderUtils.BCryptPassword("12345"), "Eric", "D'Halluin", "+33670572625", null, null);
        AppUser bruno = new AppUser("b.gerussi@gmail.com", BPwdEncoderUtils.BCryptPassword("12345"), "Bruno", "Gerussi", null, null, null);
        AppUser laetitia = new AppUser("laetis0609@yahoo.fr", BPwdEncoderUtils.BCryptPassword("12345"), "Laëtitia", "Cauwet", "+33617131790", null, null);
        AppUser maman = new AppUser("gerussi.martine@orange.fr", BPwdEncoderUtils.BCryptPassword("12345"), "Martine", "Gerussi", "+33670593820", null, null);
        AppUser manu = new AppUser("emfavvic@gmail.com", BPwdEncoderUtils.BCryptPassword("12345"), "Emmanuel", "Favre-Victoire", null, null, null);
        AppUser juan= new AppUser("jjolivero@gmail.com", BPwdEncoderUtils.BCryptPassword("12345"), "Juan-Jose", "Olivero", "+41794892845", null, null );

        List<AppUser> appUsers = Arrays.asList(julien, eric, bruno, laetitia, maman, manu, juan);

        appRoleRepository.save(admin);
        appRoleRepository.save(autor);
        appRoleRepository.save(journalist);
        appRoleRepository.save(deliverer);

        List<AppUser> admins = Arrays.asList(julien);
        List<AppUser> journalistsAndProducers = Arrays.asList(maman, eric, manu);
        List<AppUser> journalists = Arrays.asList(juan, laetitia, bruno);
        List<AppRole> onlyAdmin = Arrays.asList(admin);
        List<AppRole> journalistAndProducer = Arrays.asList(journalist, autor);
        List<AppRole> onlyConsumer = Arrays.asList(journalist);


        assignRoles(admins, onlyAdmin);
        assignRoles(journalistsAndProducers, journalistAndProducer);
        assignRoles(journalists, onlyConsumer);

        saveUsers(appUsers);

    }

    private void saveUsers(List<AppUser> appUsers){
	    for (AppUser user : appUsers)
	        appUserRepository.save(user);
    }

    private void  assignRoles(List<AppUser> appUsers, List<AppRole> roles){
	    for (AppUser user: appUsers)
	        user.setAppRole(roles);
    }
}

