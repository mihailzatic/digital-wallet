package com.mihail.wallet.service;

import com.mihail.wallet.entity.BankAccount;
import com.mihail.wallet.repository.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {

    @Mock
    private BankAccountRepository repository;

    @InjectMocks
    private BankAccountService service;

    private BankAccount account1;
    private BankAccount account2;

    @BeforeEach
    void setUp() {
        account1 = new BankAccount("Mihail", new BigDecimal("100.00"));
        account2 = new BankAccount("John Doe", new BigDecimal("50.00"));
    }

    @Test
    void testTransferFunds_Successful() {
        when(repository.findById(1L)).thenReturn(Optional.of(account1));
        when(repository.findById(2L)).thenReturn(Optional.of(account2));

        service.transferFunds(1L, 2L, new BigDecimal("40.00"));

        assertEquals(new BigDecimal("60.00"), account1.getBalance());
        assertEquals(new BigDecimal("90.00"), account2.getBalance());
        verify(repository, times(2)).save(any(BankAccount.class));
    }

    @Test
    void testTransferFunds_InsufficientFunds_ThrowsException() {
        when(repository.findById(1L)).thenReturn(Optional.of(account1));
        when(repository.findById(2L)).thenReturn(Optional.of(account2));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.transferFunds(1L, 2L, new BigDecimal("150.00"));
        });

        assertEquals("Insufficient funds", exception.getMessage());
        verify(repository, never()).save(any(BankAccount.class));
    }
}