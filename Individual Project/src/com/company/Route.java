package com.company;

public class Route{

    String Airline;
    int AirlineID;
    String SourceAirport;
    int SourceAirportID;
    String DestinationAirport;
    int DestinationAirportID;
    char Codeshare;
    int stops;
    String Equipment;

    Route(String routeEntry){
        String[] attributes = routeEntry.split(",");
        Airline = attributes[0];
        AirlineID =  (attributes[1].contains("N") ) ? 0 : Integer.parseInt(attributes[1]);
        SourceAirport = attributes[2];
        SourceAirportID = (attributes[3].contains("N") ) ? 0 :Integer.parseInt(attributes[3]);
        DestinationAirport = attributes[4];
        DestinationAirportID = (attributes[5].contains("N") ) ? 0 :Integer.parseInt(attributes[5]);
//        Codeshare = (attributes.length > 7 ) ? attributes[6].charAt(0) : '\0';
        try {
            stops = (attributes.length > 8) ? Integer.parseInt(attributes[7]) : Integer.parseInt(attributes[6]);
        }catch (NumberFormatException nfe){
            stops = 0;
        }
        Equipment = (attributes.length > 8 ) ? attributes[8] : attributes[7];
    }
}
