package com.worskhop.WorkshopAddressBook.service;

import com.worskhop.WorkshopAddressBook.model.AddressBookEntry;
import java.util.List;
import java.util.Optional;

public interface IAddressBookService {
    List<AddressBookEntry> getAllContacts();
    Optional<AddressBookEntry> getContactById(Long id);
    AddressBookEntry addContact(AddressBookEntry entry);
    AddressBookEntry updateContact(Long id, AddressBookEntry entry);
    void deleteContact(Long id);
}
