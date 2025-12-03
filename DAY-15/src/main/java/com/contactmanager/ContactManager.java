package com.contactmanager;

import java.util.*;

public class ContactManager {
    private static ContactManager instance;
    private Map<String, contact> contacts;
    
    private ContactManager() {
        contacts = new HashMap<>();
    }
    
    public static synchronized ContactManager getInstance() {
        if (instance == null) {
            instance = new ContactManager();
        }
        return instance;
    }
    
    public String addContact(contact contact) {
        String id = UUID.randomUUID().toString();
        contact.setId(id);
        contacts.put(id, contact);
        return id;
    }
    
    public contact getContact(String id) {
        return contacts.get(id);
    }
    
    public List<contact> getAllContacts() {
        return new ArrayList<>(contacts.values());
    }
    
    public boolean updateContact(String id, contact contact) {
        if (contacts.containsKey(id)) {
            contact.setId(id);
            contacts.put(id, contact);
            return true;
        }
        return false;
    }
    
    public boolean deleteContact(String id) {
        return contacts.remove(id) != null;
    }
}
