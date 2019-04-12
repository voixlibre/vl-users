package org.greenwin.VLUsers.randomTests;

import org.greenwin.VLUsers.entities.AppRole;
import org.greenwin.VLUsers.repositories.AppRoleRepository;
import org.greenwin.VLUsers.repositories.AppUserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


//@RunWith(SpringRunner.class)
//@DataJpaTest
public class RandomTests {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Before
    public void initData(){
    }


  //  @Test
  //  public void ConnectionTest(){
  //      List<AppRole> roles = new ArrayList<>();
  //      appRoleRepository.findAll().forEach(roles :: add);
  //      for (AppRole r :roles
  //           ) {
  //          System.out.println(r.getRoleName());
  //      }
  //      Assert.assertEquals(4, roles.size());
  //  }

    @Test
    public void SimpleTest(){
        Assert.assertEquals(1, 999-998);
    }
}
