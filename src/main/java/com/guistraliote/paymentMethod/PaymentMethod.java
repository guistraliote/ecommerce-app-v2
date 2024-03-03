package com.guistraliote.paymentMethod;

import com.guistraliote.customer.Customer;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "PAYMENT_METHOD")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_TYPE")
    @NotBlank(message = "O método não pode ser nulo")
    private PaymentTypes type;

    @Column(name = "CREDIT_CARD_BRAND")
    private String brand;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @Column(name = "CARD_HOLDER")
    private String cardHolder;

    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    @Column(name = "SECURITY_CODE")
    private Integer securityCode;

    @NotNull
    @Column(name = "IS_ACTIVE")
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;
}
