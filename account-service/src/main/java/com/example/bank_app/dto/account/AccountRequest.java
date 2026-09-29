package com.example.bank_app.dto.account;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequest {

    @NotBlank
    private String accountNumber;

    @NotBlank
    private String ownerName;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal balance;
}