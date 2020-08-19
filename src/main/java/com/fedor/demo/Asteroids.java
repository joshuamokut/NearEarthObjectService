package com.fedor.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Asteroids {
    Map<String, Integer> asteroidsPerDay;
    @JsonProperty("element_count")
    private Integer numberOfAsteroids;

    @JsonProperty("near_earth_objects")
    private void unpackAsteroids(Map<String, List<Object>> dailyReports) {
        asteroidsPerDay = new TreeMap<>();
        dailyReports.forEach((date, dailyReport) ->
                asteroidsPerDay.put(date, dailyReport.size()));
    }
}
