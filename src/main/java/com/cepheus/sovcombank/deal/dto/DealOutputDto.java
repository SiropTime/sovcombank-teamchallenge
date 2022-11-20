package com.cepheus.sovcombank.deal.dto;


import com.cepheus.sovcombank.deal.model.Operation;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DealOutputDto {

    private LocalDateTime timeStamp;

    private Float summary;
    private Operation operation;
}
