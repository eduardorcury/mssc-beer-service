package com.erc.msscbeerservice.events;

import com.erc.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent() {

    }

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }

}
