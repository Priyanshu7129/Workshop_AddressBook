package com.worskhop.WorkshopAddressBook.service;

import com.worskhop.WorkshopAddressBook.model.AddressBookEntry;
import com.worskhop.WorkshopAddressBook.repository.AddressBookRepository;
import com.worskhop.WorkshopAddressBook.config.RabbitMQConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService implements IAddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    // ✅ Fetch all contacts (with Redis caching)
    @Override
    @Cacheable(value = "contacts")
    public List<AddressBookEntry> getAllContacts() {
        System.out.println("Fetching from DB...");
        return addressBookRepository.findAll();
    }

    // ✅ Fetch contact by ID (with Redis caching)
    @Override
    @Cacheable(value = "contact", key = "#id")
    public Optional<AddressBookEntry> getContactById(Long id) {
        System.out.println("Fetching contact from DB...");
        return addressBookRepository.findById(id);
    }

    // ✅ Add a new contact (clear cache + publish event to RabbitMQ)
    @Override
    @CacheEvict(value = "contacts", allEntries = true)
    public AddressBookEntry addContact(AddressBookEntry entry) {
        AddressBookEntry savedEntry = addressBookRepository.save(entry);

        // Publish event to RabbitMQ
        rabbitMQProducer.sendContactAddedMessage("New contact added: " + entry.getName());

        return savedEntry;
    }

    // ✅ Update an existing contact (clear cache)
    @Override
    @CacheEvict(value = {"contacts", "contact"}, allEntries = true)
    public AddressBookEntry updateContact(Long id, AddressBookEntry entry) {
        Optional<AddressBookEntry> existingEntry = addressBookRepository.findById(id);
        if (existingEntry.isPresent()) {
            AddressBookEntry updatedEntry = existingEntry.get();
            updatedEntry.setName(entry.getName());
            updatedEntry.setEmail(entry.getEmail());
            updatedEntry.setPhoneNumber(entry.getPhoneNumber());
            updatedEntry.setAddress(entry.getAddress());

            return addressBookRepository.save(updatedEntry);
        } else {
            throw new RuntimeException("Contact not found with id: " + id);
        }
    }

    // ✅ Delete a contact (clear cache)
    @Override
    @CacheEvict(value = {"contacts", "contact"}, allEntries = true)
    public void deleteContact(Long id) {
        if (!addressBookRepository.existsById(id)) {
            throw new RuntimeException("Contact not found with id: " + id);
        }

        addressBookRepository.deleteById(id);
    }
}
