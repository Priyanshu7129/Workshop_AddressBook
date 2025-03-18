package com.worskhop.WorkshopAddressBook.service;

import com.worskhop.WorkshopAddressBook.model.AddressBookEntry;
import com.worskhop.WorkshopAddressBook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService implements IAddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    // ✅ Cache all contacts
    @Cacheable(value = "contacts")
    public List<AddressBookEntry> getAllContacts() {
        System.out.println("Fetching from DB...");
        return addressBookRepository.findAll();
    }

    // ✅ Cache individual contact retrieval
    @Cacheable(value = "contact", key = "#id")
    public Optional<AddressBookEntry> getContactById(Long id) {
        System.out.println("Fetching contact from DB...");
        return addressBookRepository.findById(id);
    }

    // ✅ Evict cache when a new contact is added
    @CacheEvict(value = "contacts", allEntries = true)
    public AddressBookEntry addContact(AddressBookEntry entry) {
        return addressBookRepository.save(entry);
    }

    // ✅ Evict cache when a contact is updated
    @CacheEvict(value = {"contacts", "contact"}, allEntries = true)
    public AddressBookEntry updateContact(Long id, AddressBookEntry entry) {
        return addressBookRepository.save(entry);
    }

    // ✅ Evict cache when a contact is deleted
    @CacheEvict(value = {"contacts", "contact"}, allEntries = true)
    public void deleteContact(Long id) {
        addressBookRepository.deleteById(id);
    }
}
