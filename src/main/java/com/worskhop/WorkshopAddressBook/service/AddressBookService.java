package com.worskhop.WorkshopAddressBook.service;

import com.worskhop.WorkshopAddressBook.model.AddressBookEntry;
import com.worskhop.WorkshopAddressBook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    public List<AddressBookEntry> getAllContacts() {
        return addressBookRepository.findAll();
    }

    public AddressBookEntry getContactById(Long id) {
        return addressBookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found with id: " + id));
    }

    public AddressBookEntry addContact(AddressBookEntry entry) {
        return addressBookRepository.save(entry);
    }

    public AddressBookEntry updateContact(Long id, AddressBookEntry updatedEntry) {
        AddressBookEntry existingEntry = getContactById(id);
        existingEntry.setName(updatedEntry.getName());
        existingEntry.setEmail(updatedEntry.getEmail());
        existingEntry.setPhoneNumber(updatedEntry.getPhoneNumber());
        existingEntry.setAddress(updatedEntry.getAddress());
        return addressBookRepository.save(existingEntry);
    }

    public void deleteContact(Long id) {
        AddressBookEntry entry = addressBookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found with id: " + id));
        addressBookRepository.delete(entry);
    }
}
