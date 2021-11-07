package com.country.routes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class RoutingResponse {
    private List<String> route;
}
