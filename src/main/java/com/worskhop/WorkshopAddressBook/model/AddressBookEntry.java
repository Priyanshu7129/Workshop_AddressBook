package com.worskhop.WorkshopAddressBook.model;

import com.worskhop.WorkshopAddressBook.dto.AddressBookDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    // Constructor to convert DTO to Entity
    public AddressBookEntry(AddressBookDTO dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
        this.address = dto.getAddress();
    }
}
