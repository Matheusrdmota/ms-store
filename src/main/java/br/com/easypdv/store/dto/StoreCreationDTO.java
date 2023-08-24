package br.com.easypdv.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreCreationDTO {
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
}
