package org.greenwin.VLUsers.repositories;

import org.greenwin.VLUsers.entities.AppRole;
import org.greenwin.VLUsers.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {

    AppRole getAppRoleById(int id);

    List<AppRole> getAppRolesByUsersEquals(AppUser user);
}
