package org.greenwin.VLUsers.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.greenwin.VLUsers.entities.AppUser;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote {

    private int id;

    private Topic topic;

    private int userId;

    @Transient
    private AppUser user;



}
