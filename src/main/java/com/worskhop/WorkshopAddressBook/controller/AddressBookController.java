package com.worskhop.WorkshopAddressBook.controller;

import com.worskhop.WorkshopAddressBook.model.AddressBookEntry;
import com.worskhop.WorkshopAddressBook.service.IAddressBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addressbook")
@Tag(name = "Address Book API", description = "Endpoints for managing contacts")
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;

    @GetMapping
    @Operation(summary = "Fetch all contacts", description = "Retrieves a list of all contacts in the Address Book.")
    public List<AddressBookEntry> getAllContacts() {
        return addressBookService.getAllContacts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Fetch a contact by ID", description = "Retrieves a specific contact using the ID.")
    public Optional<AddressBookEntry> getContactById(@PathVariable Long id) {
        return addressBookService.getContactById(id);
    }

    @PostMapping
    @Operation(summary = "Add a new contact", description = "Creates and stores a new contact.")
    public AddressBookEntry addContact(@RequestBody AddressBookEntry entry) {
        return addressBookService.addContact(entry);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a contact", description = "Updates an existing contact by ID.")
    public AddressBookEntry updateContact(@PathVariable Long id, @RequestBody AddressBookEntry entry) {
        return addressBookService.updateContact(id, entry);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a contact", description = "Deletes a contact by ID.")
    public void deleteContact(@PathVariable Long id) {
        addressBookService.deleteContact(id);
    }
}
