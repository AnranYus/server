package com.inxtes.nowsyncserver.service;

import com.inxtes.nowsyncserver.model.Contacts;

import java.util.List;
import java.util.Map;

public interface ContactsService {

    Map<String, Contacts> getAllContacts();

    List<Contacts> setContacts(List<Contacts> list);

}
