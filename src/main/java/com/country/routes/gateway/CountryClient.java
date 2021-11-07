package com.country.routes.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "countryData", url = "https://raw.githubusercontent.com/mledoze/countries/master")
public interface CountryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/countries.json")
    List<Country> getCountries();

}
