package com.mihail.wallet.service;

import com.mihail.wallet.entity.BankAccount;
import com.mihail.wallet.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    public BankAccount createAccount(String name, BigDecimal initialDeposit) {
        BankAccount account = new BankAccount(name, initialDeposit);
        return repository.save(account);
    }

    public BankAccount getAccount(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Transactional
    public void transferFunds(Long fromId, Long toId, BigDecimal amount) {
        BankAccount fromAccount = getAccount(fromId);
        BankAccount toAccount = getAccount(toId);

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        repository.save(fromAccount);
        repository.save(toAccount);
    }
}