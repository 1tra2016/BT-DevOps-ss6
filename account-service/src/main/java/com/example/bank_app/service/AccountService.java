package com.example.bank_app.service;

import com.example.bank_app.dto.account.AccountRequest;
import com.example.bank_app.dto.account.AccountResponse;
import com.example.bank_app.entity.Account;
import com.example.bank_app.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountResponse createAccount(AccountRequest request) {

        if (accountRepository.existsByAccountNumber(
                request.getAccountNumber())) {

            throw new RuntimeException("Account number already exists");
        }

        Account account = Account.builder()
                .accountNumber(request.getAccountNumber())
                .ownerName(request.getOwnerName())
                .balance(request.getBalance())
                .build();

        Account savedAccount = accountRepository.save(account);

        return toResponse(savedAccount);
    }

    public AccountResponse getAccountById(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Account not found")
                );

        return toResponse(account);
    }

    public List<AccountResponse> getAllAccounts() {

        return accountRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private AccountResponse toResponse(Account account) {

        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .ownerName(account.getOwnerName())
                .balance(account.getBalance())
                .build();
    }
}