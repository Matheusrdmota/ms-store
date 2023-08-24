package br.com.easypdv.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String corporateName;
    private String cnpj;
    private String email;
    private String state;
    private String city;
    private String address;
    private String zipCode;
    private String phoneNumber;
    private LocalDateTime billingDate;
    private BigDecimal feeValue;
    private LocalDateTime creationDate;
    private Boolean active;
}
