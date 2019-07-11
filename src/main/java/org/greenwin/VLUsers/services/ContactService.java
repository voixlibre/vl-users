package org.greenwin.VLUsers.services;

import org.greenwin.VLUsers.entities.Contact;
import org.greenwin.VLUsers.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact saveContact(Contact contact){
        return contactRepository.save(contact);
    }
}
