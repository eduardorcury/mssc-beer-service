package com.erc.msscbeerservice.bootstrap;

import com.erc.msscbeerservice.domain.Beer;
import com.erc.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

//@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Skol")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(10)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("13.50"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Brahma")
                    .beerStyle("Pale Ale")
                    .quantityToBrew(150)
                    .minOnHand(15)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("8.49"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Pinball Porter")
                    .beerStyle("PALE_ALE")
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .price(new BigDecimal("12.95"))
                    .upc(BEER_3_UPC)
                    .build());
        }

        System.out.println("Loaded Beers: " + beerRepository.count());

    }
}
