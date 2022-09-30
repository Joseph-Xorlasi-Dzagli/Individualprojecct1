package  com.company;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;



public class Main {

    static String Directory = "C:\\Users\\joseph.dzagli\\OneDrive - Ashesi University\\Canvas\\Intermediate Computer Programming\\";

    static String Request() {
        Scanner SourceCity = new Scanner(System.in);
        System.out.println("Enter Source City: ");
        String S_City  = SourceCity.nextLine();
        Scanner SourceCountry = new Scanner(System.in);
        System.out.println("Enter Source Country: ");
        String S_Country = SourceCountry.nextLine();

        Scanner DestinationCity = new Scanner(System.in);
        System.out.println("Enter Destination City: ");
        String D_City = DestinationCity.nextLine();
        Scanner DestinationCountry = new Scanner(System.in);
        System.out.println("Enter Destination Country: ");
        String D_Country = DestinationCountry.nextLine();


        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Directory + "Requestinput.txt"));
            bw.write(S_City + "," + S_Country);
            bw.write("\n" + D_City + "," + D_Country);
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return Directory + "Requestinput.txt";

    }

    static String[] ProcessRequest(String filepath) {
        String[] Data = new String[4];
        String[] temp = null;
        int i = -1;

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(filepath));
            String s;
            while ((s = br.readLine()) != null) {
                i = i + 1;
                temp = s.split(",");
                Data[i] = temp[0];
                i = i + 1;
                Data[i] = temp[1];
                System.out.println(s);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Data; //     Data = [Source City, Source Country, Destination City, Destination Country]
    }




    static double Heuristic(Airport SourceAirport, Airport DestinationAirport) {
        float a = (Math.abs(SourceAirport.Latitude - DestinationAirport.Latitude) + Math.abs(SourceAirport.Longitude - DestinationAirport.Longitude));

        return Math.sqrt((a < 0) ? -1 * a : a);
    }




    static Airport findAirportClass(String City, String Country) {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(Directory + "Airports.csv"));
            BufferedReader rbr = new BufferedReader(
                    new FileReader(Directory + "routes.csv"));

            String s;
            while ((s = br.readLine()) != null) {
                String[] attributes = s.split(",");

//                System.out.println(s);
                if (attributes[2].contains(City) && attributes[3].contains(Country)) {

                    return new Airport(s);

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }


//
    static Airport findDAirportClass(String City, String Country) {
         Airport Destination = null;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(Directory + "Airports.csv"));

            String s;
            String t;
            String[] rattributes;
            String[] attributes;
            String[] Airport;
            while ((s = br.readLine()) != null) {
                attributes = s.split(",");

                BufferedReader rbr = new BufferedReader(
                        new FileReader(Directory + "routes.csv"));

                if (attributes[2].contains(City) && attributes[3].contains(Country)) {

                    Destination = new Airport(s);

                    while ((t = rbr.readLine()) != null) {
                        rattributes = t.split(",");

                        if ( rattributes[4].equals(Destination.IATA) || rattributes[4].equals(Destination.ICAO)) {
                            return new Airport(s);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }



    static Airport findAirportClass(int AirportID) {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(Directory + "airports.csv"));

            String s;
            while ((s = br.readLine()) != null) {
                String[] attributes = s.split(",");
                if (Integer.parseInt(attributes[0]) == AirportID) {

                    return new Airport(s);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }

        return null;
    }


    static Airport findAirportClass(String AirportName) {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(Directory + "airports.csv"));

            String s;
            while ((s = br.readLine()) != null) {
                if (s.contains(AirportName)) {
                    return new Airport(s);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }


    static Airline findAirlineClass(String AirlineID) {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(Directory + "airlines.csv"));

            String s;
            while ((s = br.readLine()) != null) {
                if (s.contains(AirlineID)) {
                    return new Airline(s);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }



    static Airline findAirlineClass(String SourceAirport, String DestinationAirport) {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(Directory + "routes.csv"));

            String s;
            while ((s = br.readLine()) != null) {
                if (s.contains(SourceAirport) && s.contains(DestinationAirport)) {
                    return findAirlineClass((new Route(s)).Airline);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }






    static List<Airport> findAllDestinationAirports(Airport SourceAirportID) {
        int counter = 0;
        List<Airport> Destination = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(Directory + "routes.csv"));
            String s;
            String[] attributes;
            while ((s = br.readLine()) != null) {
                attributes = s.split(",");
                try {
                    if ( !attributes[5].contains("N") && attributes[2].equals(SourceAirportID.IATA) || attributes[2].equals(SourceAirportID.ICAO)) {
                        Destination.add(findAirportClass(Integer.parseInt(attributes[5])));
                    }
                }catch (NumberFormatException nFe){
                    nFe.printStackTrace();
                }
            }
            br.close();
            return Destination;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Destination;
    }



    static String printResults(String[] route){
        List<Airport> Airports = new ArrayList<>();
        List<Airline> Airline = new ArrayList<>();
        List<Route> Routes = new ArrayList<>();
        for(int i = 0; route[i]!= null; i++) {
            Airports.add(findAirportClass(route[i]));
            System.out.println(route[i]);


            if (i > 0 && route[i] != null) {
                System.out.println(Airports.get(i).airportID);
                try {
                    BufferedReader br = new BufferedReader(
                            new FileReader(Directory + "routes.csv"));
                    String s;
                    String[] attributes;

                    while ((s = br.readLine()) != null) {
                        attributes = s.split(",");
                        if ((attributes[2].contains(Airports.get(i-1).IATA) || attributes[2].contains(Airports.get(i-1).ICAO)) && (attributes[4].contains(Airports.get(i).IATA) || attributes[4].contains(Airports.get(i).ICAO))) {
                            System.out.println(s);
                            System.out.println("added");
                            Routes.add(new Route(s));
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(Routes);
//                if (i > 0) {
//                    Airline.add(findAirlineClass(Integer.toString(Airports.get(i - 1).airportID), Integer.toString(Airports.get(i).airportID)));
//                    System.out.println(Airline);
//                }
            }

        }
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(Directory + "_output.txt"));

        int totalflights = 0;
        int totalstops = 0;

            for(int i = 1; route[i]!= null; i++) {
                bw.write(Integer.toString(i) + ". " + Routes.get(i).Airline + " from " + Airports.get(i-1).IATA + " to " + Airports.get(i).IATA +" "+ Routes.get(i).stops + " stops\n");
                totalflights = i;
                totalstops = totalstops + Routes.get(i).stops;

            }
            bw.write("Total flights: " + Integer.toString(totalflights)+ "\n");
            bw.write("Total Additional Stops: " + Integer.toString(totalstops));

            bw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Directory + "routes.csv";
    }


    static String[] findroute() {
        String[] Data = ProcessRequest(Directory + "Requestinput.txt");

        String[] route = new String[100];

        Set<Airport> Explored = new HashSet<>();


        int j = 0;
        Airport SourceAirport = findAirportClass(Data[0], Data[1]); // @param SourceCity, SourceCountry

        Airport DestinationAirport = findDAirportClass(Data[2], Data[3]); // @param DestinationCity, DestinationCountry
        assert DestinationAirport != null;

        Airport min = SourceAirport;
        assert min != null;
        route[0] = min.airportName;
        Explored.add(min);

        while (min != DestinationAirport) {
            List<Airport> Destinations = findAllDestinationAirports(min);

            min = Destinations.get(0);

            for (int i = 0; i < Destinations.size() && Destinations.get(i)!= null; i++) {


                if (Destinations.get(i) != null) {

                    if (Destinations.get(i).airportName.equals(DestinationAirport.airportName)) {
                        System.out.println("Found Solution!");
                        j = j + 1;
                        route[j] = min.airportName;
                        route[j+1] = Destinations.get(i).airportName;
                        System.out.println(Arrays.toString(route));
                        printResults(route);
                        return route;
                    } else if (Heuristic(Destinations.get(i), DestinationAirport) < Heuristic(min, DestinationAirport) ) {
                        min = Destinations.get(i);
                        Explored.add(min);


                    }
                }
            }
            j = j + 1;
            route[j] = min.airportName;
        }

        return route;


    }



    public static void main(String[] args) {
        String Directory = "C:\\Users\\joseph.dzagli\\OneDrive - Ashesi University\\Canvas\\Intermediate Computer Programming\\";
        findroute();


    }
}

//&& findAirlineClass(Integer.toString(min.airportID), Integer.toString(Destinations.get(i).airportID)) != null

