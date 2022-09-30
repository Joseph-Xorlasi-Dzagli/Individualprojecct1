package com.company;

public class Airport {
    int airportID;
    String airportName;
    String City;
    String Country;
    String IATA;
    String ICAO;
    float Latitude;
    float Longitude;
    int Altitude;
    float Timezone;
    char DST;
    String DatabaseTimezone;
    String Type;
    String Source;

    Airport(String airportEntry){
        String[] attributes = airportEntry.split(",");
        try {
            airportID = Integer.parseInt(attributes[0]);
            airportName = attributes[1];
            City = attributes[2];
            Country = attributes[3];
            IATA = attributes[4];
            ICAO = attributes[5];
            Latitude = Float.parseFloat(attributes[6]);
            Longitude = Float.parseFloat(attributes[7]);
            Altitude = Integer.parseInt(attributes[8]);
            DST = attributes[10].charAt(0);
//            Timezone = Float.parseFloat(attributes[9]);
            DatabaseTimezone = attributes[11];
            Type = attributes[12];
            Source = attributes[13];
        }catch (NumberFormatException ex){
            ex.printStackTrace();
        }

    }

}











//(int airportID, String airportName, String City, String Country, String IATA, String ICAO, int Latitude, int Longitude, int Altitude, char DST, int Timezone, String DatabaseTimezone, String Type, String Source)