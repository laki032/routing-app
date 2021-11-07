package com.country.routes.api;

import com.country.routes.dto.RoutingResponse;
import com.country.routes.service.RoutingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/routing")
public class RoutingController {

    private final RoutingService routingService;

    @GetMapping(path = "/{origin}/{destination}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoutingResponse> getRoute(
            @PathVariable String origin,
            @PathVariable String destination) {
        return ResponseEntity.ok(routingService.getRoute(origin, destination));
    }
}
