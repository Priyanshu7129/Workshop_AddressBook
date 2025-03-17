package com.worskhop.WorkshopAddressBook.service;

import com.worskhop.WorkshopAddressBook.model.AddressBookEntry;
import com.worskhop.WorkshopAddressBook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    public List<AddressBookEntry> getAllContacts() {
        return addressBookRepository.findAll();
    }

    public AddressBookEntry addContact(AddressBookEntry entry) {
        return addressBookRepository.save(entry);
    }
}
