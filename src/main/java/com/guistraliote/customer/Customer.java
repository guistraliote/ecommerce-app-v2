package com.guistraliote.customer;

import com.guistraliote.customerAddress.CustomerAddress;
import com.guistraliote.paymentMethod.PaymentMethod;
import com.guistraliote.productReview.ProductReview;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "CUSTOMER")
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "NAME")
    private String name;

    @NotBlank(message = "O sobrenome é obrigatório")
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos numéricos")
    @Column(name = "CPF")
    private String cpf;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    @Column(name = "EMAIL")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    @Column(name = "PHONE")
    private String phone;

    @NotNull
    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean active;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<CustomerAddress> addresses = new ArrayList<>();

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<ProductReview> productReviews = new ArrayList<>();

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<PaymentMethod> paymentMethod = new ArrayList<>();
}
