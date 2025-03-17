package com.worskhop.WorkshopAddressBook.controller;

import com.worskhop.WorkshopAddressBook.dto.AddressBookDTO;
import com.worskhop.WorkshopAddressBook.dto.ResponseDTO;
import com.worskhop.WorkshopAddressBook.model.AddressBookEntry;
import com.worskhop.WorkshopAddressBook.service.IAddressBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;

    // Get all contacts
    @GetMapping
    public ResponseEntity<ResponseDTO<List<AddressBookEntry>>> getAllContacts() {
        List<AddressBookEntry> contacts = addressBookService.getAllContacts();
        return ResponseEntity.ok(new ResponseDTO<>("Contacts retrieved successfully", contacts));
    }

    // Get contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<AddressBookEntry>> getContactById(@PathVariable Long id) {
        AddressBookEntry contact = addressBookService.getContactById(id);
        return ResponseEntity.ok(new ResponseDTO<>("Contact retrieved successfully", contact));
    }

    // Add a new contact
    @PostMapping
    public ResponseEntity<ResponseDTO<AddressBookEntry>> addContact(@Valid @RequestBody AddressBookDTO dto) {
        AddressBookEntry savedEntry = addressBookService.addContact(dto);
        return ResponseEntity.ok(new ResponseDTO<>("Contact added successfully", savedEntry));
    }

    // Update contact
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<AddressBookEntry>> updateContact(@PathVariable Long id, @Valid @RequestBody AddressBookDTO dto) {
        AddressBookEntry updatedEntry = addressBookService.updateContact(id, dto);
        return ResponseEntity.ok(new ResponseDTO<>("Contact updated successfully", updatedEntry));
    }

    // Delete contact
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteContact(@PathVariable Long id) {
        addressBookService.deleteContact(id);
        return ResponseEntity.ok(new ResponseDTO<>("Contact deleted successfully", null));
    }
}
