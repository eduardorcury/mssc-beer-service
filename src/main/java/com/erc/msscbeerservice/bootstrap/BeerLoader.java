package com.erc.msscbeerservice.bootstrap;

import com.erc.msscbeerservice.domain.Beer;
import com.erc.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Skol")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(10)
                    .upc(1230L)
                    .price(new BigDecimal("13.50"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Brahma")
                    .beerStyle("Pale Ale")
                    .quantityToBrew(150)
                    .minOnHand(15)
                    .upc(4657L)
                    .price(new BigDecimal("8.49"))
                    .build());

        }

        System.out.println("Loaded Beers: " + beerRepository.count());

    }
}
