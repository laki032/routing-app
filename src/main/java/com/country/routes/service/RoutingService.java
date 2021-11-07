package com.country.routes.service;

import com.country.routes.dto.RoutingResponse;
import com.country.routes.exception.BadRequestException;
import com.country.routes.gateway.CountryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoutingService {

    private Graph countriesGraph;

    private final CountryClient countryClient;

    @PostConstruct
    private void loadCountriesGraph() {
        log.info("Creating countries graph in PostConstruct");
        var countries = countryClient.getCountries();

        countriesGraph = new DefaultUndirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        countries.forEach(country -> countriesGraph.addVertex(country.getCca3()));
        countries.forEach(country -> country.getBorders().forEach(border ->
                countriesGraph.addEdge(country.getCca3(), border)));
        log.info("Creating countries graph finished");
    }

    public RoutingResponse getRoute(String origin, String destination) {
        var pathFinder = new DijkstraShortestPath<String, DefaultEdge>(countriesGraph);

        try {
            var path = pathFinder.getPath(origin, destination);
            return new RoutingResponse(path.getVertexList());
        } catch (IllegalArgumentException | NullPointerException e) {
            log.warn("Exception in finding path ", e);
            throw new BadRequestException();
        }
    }
}
