package com.erc.msscbeerservice.events;

import com.erc.msscbeerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }

}
