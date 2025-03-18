package com.worskhop.WorkshopAddressBook.controller;

import com.worskhop.WorkshopAddressBook.model.AddressBookEntry;
import com.worskhop.WorkshopAddressBook.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;  // ✅ Use interface instead of class

    @GetMapping
    public List<AddressBookEntry> getAllContacts() {
        return addressBookService.getAllContacts();
    }

    @GetMapping("/{id}")
    public Optional<AddressBookEntry> getContactById(@PathVariable Long id) {
        return addressBookService.getContactById(id);
    }

    @PostMapping
    public AddressBookEntry addContact(@RequestBody AddressBookEntry entry) {
        return addressBookService.addContact(entry);
    }

    @PutMapping("/{id}")
    public AddressBookEntry updateContact(@PathVariable Long id, @RequestBody AddressBookEntry entry) {
        return addressBookService.updateContact(id, entry);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        addressBookService.deleteContact(id);
    }
}
