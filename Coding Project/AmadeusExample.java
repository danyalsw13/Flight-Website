package com.example.projectdreamline;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Airline;

import java.util.HashMap;
import java.util.Map;

public class AmadeusExample {

    public static Map<String, String> getAirlineNamesAndCodes() {
        Map<String, String> airlineNamesAndCodes = new HashMap<>();
        // Initialize the Amadeus client with your API key and secret
        Amadeus amadeus = Amadeus.builder("ZPeflw5GDnxraU9hFpdk6UAYPQm7jsay", "CQYi8IGjusYdu6Xb").build();

        try {
            // Make a request to Airlines API to fetch all airlines
            Airline[] airlines = amadeus.referenceData.airlines.get();

            // Process the response
            for (Airline airline : airlines) {
                String airlineCode = airline.getIataCode();
                String airlineName = airline.getBusinessName();
                airlineNamesAndCodes.put(airlineCode, airlineName);
            }
        } catch (ResponseException e) {
            // Handle errors
            e.printStackTrace();
        }
        return airlineNamesAndCodes;
    }
}





