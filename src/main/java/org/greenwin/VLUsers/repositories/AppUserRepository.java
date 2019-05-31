package org.greenwin.VLUsers.repositories;

import org.greenwin.VLUsers.entities.AppRole;
import org.greenwin.VLUsers.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByName(String username);

    AppUser getAppUserById(int id);

    AppUser findById(int id);

    AppUser getByEmail(String email);

    List<AppUser> findAllByNameContainingIgnoreCase(String name);

    List<AppUser> getAppUsersByAppRoleEquals(AppRole role);

}
