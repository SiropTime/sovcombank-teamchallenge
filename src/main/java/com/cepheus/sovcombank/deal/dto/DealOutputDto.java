package com.cepheus.sovcombank.deal.dto;


import com.cepheus.sovcombank.deal.model.Operation;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public class DealOutputDto {

    private LocalDateTime timeStamp;

    private Float summary;
    private Operation operation;
}
