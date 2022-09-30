package com.company;

public class Airline {
    int AirlineID;
    String Name;
    String Alias;
    String IATA;
    String ICAO;
    String Callsign;
    String Country;
    char Active;

    Airline(String AirlineEntry){
        String[] attributes = AirlineEntry.split(",");
        AirlineID = Integer.parseInt(attributes[0]);
        Name = attributes[1];
        Alias = attributes[2];
        IATA = attributes[3];
        ICAO = attributes[4];
        Callsign = attributes[5];
        Country = attributes[6];
        Active = attributes[7].charAt(0);

    }
}
