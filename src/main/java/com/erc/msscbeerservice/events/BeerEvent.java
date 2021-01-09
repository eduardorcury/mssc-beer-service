package com.erc.msscbeerservice.events;

import com.erc.msscbeerservice.web.model.BeerDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -5815566940065181210L;

    private BeerDto beerDto;

    public BeerEvent() {

    }

    public BeerEvent(BeerDto beerDto) {
        this.beerDto = beerDto;
    }

}
