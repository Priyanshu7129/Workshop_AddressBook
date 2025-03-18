package com.worskhop.WorkshopAddressBook.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "address_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookEntry implements Serializable {  // ✅ Implement Serializable

    private static final long serialVersionUID = 1L; // ✅ Add Serial Version UID

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}
