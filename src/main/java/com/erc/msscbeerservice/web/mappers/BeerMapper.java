package com.erc.msscbeerservice.web.mappers;

import com.erc.msscbeerservice.domain.Beer;
import com.erc.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = { DateMapper.class })
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);

}