package com.erc.msscbeerservice.services.brewing;

import com.erc.msscbeerservice.config.JmsConfig;
import com.erc.msscbeerservice.domain.Beer;
import com.erc.msscbeerservice.events.BrewBeerEvent;
import com.erc.msscbeerservice.events.NewInventoryEvent;
import com.erc.msscbeerservice.repositories.BeerRepository;
import com.erc.msscbeerservice.web.model.BeerDto;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    public BrewBeerListener(BeerRepository beerRepository, JmsTemplate jmsTemplate) {
        this.beerRepository = beerRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {

        BeerDto beerDto = event.getBeerDto();
        Beer beer = beerRepository.getOne(beerDto.getId());
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());
        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);
        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);

    }

}
