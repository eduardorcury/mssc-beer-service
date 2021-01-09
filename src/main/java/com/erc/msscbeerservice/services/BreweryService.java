package com.erc.msscbeerservice.services;

import com.erc.msscbeerservice.config.JmsConfig;
import com.erc.msscbeerservice.domain.Beer;
import com.erc.msscbeerservice.events.BrewBeerEvent;
import com.erc.msscbeerservice.repositories.BeerRepository;
import com.erc.msscbeerservice.services.inventory.BeerInventoryService;
import com.erc.msscbeerservice.web.mappers.BeerMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreweryService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    public BreweryService(BeerRepository beerRepository, BeerInventoryService beerInventoryService, JmsTemplate jmsTemplate, BeerMapper beerMapper) {
        this.beerRepository = beerRepository;
        this.beerInventoryService = beerInventoryService;
        this.jmsTemplate = jmsTemplate;
        this.beerMapper = beerMapper;
    }

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {

        List<Beer> beers = beerRepository.findAll();
        beers.forEach(beer -> {
            Integer inventoryQuantityOnHand = beerInventoryService.getOnhandInventory(beer.getId());
            if (beer.getMinOnHand() >= inventoryQuantityOnHand) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE,
                        new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }

}
