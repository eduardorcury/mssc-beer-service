package com.erc.msscbeerservice.services.order;

import com.erc.msscbeerservice.repositories.BeerRepository;
import com.erc.msscbeerservice.web.model.BeerOrderDto;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public BeerOrderValidator(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Boolean validateOrder(BeerOrderDto beerOrderDto) {

        AtomicInteger beerNotFound = new AtomicInteger();
        beerOrderDto.getBeerOrderLines().forEach(orderLine -> {
            if (beerRepository.findByUpc(orderLine.getUpc()) == null) {
                beerNotFound.incrementAndGet();
            }
        });

        return beerNotFound.get() == 0;

    }

}
