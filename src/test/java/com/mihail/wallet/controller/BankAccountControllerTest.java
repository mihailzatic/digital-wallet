package com.mihail.wallet.controller;

import com.mihail.wallet.entity.BankAccount;
import com.mihail.wallet.service.BankAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

// The updated Spring Boot 4.0 Import for WebMvcTest!
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BankAccountController.class)
public class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BankAccountService service;

    @Test
    void testGetAccount() throws Exception {
        BankAccount mockAccount = new BankAccount("Mihail", new BigDecimal("250.00"));
        when(service.getAccount(1L)).thenReturn(mockAccount);

        mockMvc.perform(get("/api/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountHolderName").value("Mihail"))
                .andExpect(jsonPath("$.balance").value(250.00));
    }
}