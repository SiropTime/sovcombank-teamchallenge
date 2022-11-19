package com.cepheus.sovcombank.deal.dto;


import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public class DealOutputDto {

    private LocalDateTime timeStamp;

    private Float summary;
}
