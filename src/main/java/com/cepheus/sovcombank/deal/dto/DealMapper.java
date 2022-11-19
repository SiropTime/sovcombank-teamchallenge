package com.cepheus.sovcombank.deal.dto;

import com.cepheus.sovcombank.deal.model.Deal;

public class DealMapper {
    public static DealOutputDto mapDealToOut(Deal deal){
        return DealOutputDto.builder()
                .summary(deal.getSummary())
                .timeStamp(deal.getTimeStamp())
                .operation(deal.getOperation())
                .build();
    }
}
