package com.cepheus.sovcombank.account.dto;

import com.cepheus.sovcombank.account.model.Currency;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private Currency currency;
    private Float balance;
}
