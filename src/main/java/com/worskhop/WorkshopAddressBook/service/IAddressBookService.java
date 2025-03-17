package com.worskhop.WorkshopAddressBook.service;

import com.worskhop.WorkshopAddressBook.dto.AddressBookDTO;
import com.worskhop.WorkshopAddressBook.model.AddressBookEntry;
import java.util.List;

public interface IAddressBookService {
    List<AddressBookEntry> getAllContacts();
    AddressBookEntry getContactById(Long id);
    AddressBookEntry addContact(AddressBookDTO dto);
    AddressBookEntry updateContact(Long id, AddressBookDTO dto);
    void deleteContact(Long id);
}
