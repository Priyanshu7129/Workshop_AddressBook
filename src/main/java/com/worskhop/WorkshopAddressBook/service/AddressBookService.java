package com.worskhop.WorkshopAddressBook.service;

import com.worskhop.WorkshopAddressBook.dto.AddressBookDTO;
import com.worskhop.WorkshopAddressBook.model.AddressBookEntry;
import com.worskhop.WorkshopAddressBook.repository.AddressBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class AddressBookService implements IAddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AddressBookEntry> getAllContacts() {
        return addressBookRepository.findAll();
    }

    @Override
    public AddressBookEntry getContactById(Long id) {
        return addressBookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found with id: " + id));
    }

    @Override
    public AddressBookEntry addContact(AddressBookDTO dto) {
        AddressBookEntry entry = modelMapper.map(dto, AddressBookEntry.class);
        return addressBookRepository.save(entry);
    }

    @Override
    public AddressBookEntry updateContact(Long id, AddressBookDTO dto) {
        AddressBookEntry existingEntry = getContactById(id);
        modelMapper.map(dto, existingEntry);
        return addressBookRepository.save(existingEntry);
    }

    @Override
    public void deleteContact(Long id) {
        AddressBookEntry entry = getContactById(id);
        addressBookRepository.delete(entry);
    }
}
