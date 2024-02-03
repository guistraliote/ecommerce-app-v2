package com.guistraliote.customerAddress;

import com.guistraliote.customer.Customer;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "CUSTOMER_ADDRESS")
public class CustomerAddress extends PanacheEntity {

    @Column(name = "ADDRESS_NAME")
    private String AddressName;

    @Column(name = "ADDRESS")
    private String Address;

    @Column(name = "ADDRESS_NUMBER")
    private Integer AddressNumber;

    @Column(name = "ADDRESS_POSTAL_CODE")
    private String PostalCode;

    @Column(name = "ADDRESS_CITY")
    private String City;

    @Column(name = "ADDRESS_STATE")
    private String State;

    @Column(name = "ADDRESS_COUNTRY")
    private String Country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
}
