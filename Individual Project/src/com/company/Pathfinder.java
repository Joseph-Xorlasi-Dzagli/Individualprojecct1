//package com.company;
//import java.io.*;
//import java.util.*;
//import java.lang.Math;
//
//
//
//public class Pathfinder {
//
//    String Directory = "C:\\Users\\joseph.dzagli\\OneDrive - Ashesi University\\Canvas\\Intermediate Computer Programming\\";
//
//    String Request() {
//        Scanner SourceCity = new Scanner(System.in);
//        System.out.println("Enter Source City: ");
//        Scanner SourceCountry = new Scanner(System.in);
//        System.out.println("Enter Source Country: ");
//
//        Scanner DestinationCity = new Scanner(System.in);
//        System.out.println("Enter Destination City: ");
//        Scanner DestinationCountry = new Scanner(System.in);
//        System.out.println("Enter Destination Country: ");
//
//
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(Directory + "Requestinput.txt"));
//            bw.write(SourceCity + "," + SourceCountry);
//            bw.write("\n" + DestinationCity + "," + DestinationCountry);
//            bw.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//
//        }
//        return Directory + "Requestinput.txt";
//
//    }
//
//    String[] ProcessRequest(String filepath) {
//        String[] Data = null;
//        try {
//            BufferedReader br = new BufferedReader(
//                    new FileReader(filepath));
//            String s;
//            while ((s = br.readLine()) != null) {
//                Data = s.split(",");
//                System.out.println(s);
//            }
//            br.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return Data;
//    }
////     Data = [Source City, Source Country, Destination City, Destination Country]
//
//
//    class Node {
//        Airport P_AirportID = null; //Parent Node
//        Airport S_AirportID; //  State
//
//        Node(Airport S_Airport) {
//            S_AirportID = S_Airport;
//        }
//
//        Node(Airport P_Aiport, Airport S_Airport) {
//            P_AirportID = P_Aiport;
//            S_AirportID = S_Airport;
//        }
//    }
//
//    double Heuristic(Airport SourceAirport, Airport DestinationAirport) {
//        float a = (Math.abs(SourceAirport.Latitude - DestinationAirport.Latitude) + Math.abs(SourceAirport.Longitude - DestinationAirport.Longitude));
//        return Math.sqrt((a < 0) ? -1 * a : a);
//    }
//
//
//    Airport findAirportClass(String City, String Country) {
//        try {
//            BufferedReader br = new BufferedReader(
//                    new FileReader(Directory + "Airports.csv"));
//
//            String s;
//            while ((s = br.readLine()) != null) {
//                if (s.contains(City) && s.contains(Country)) {
//                    return new Airport(s);
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return null;
//    }
//
//    Airport findAirportClass(String AirportID) {
//        try {
//            BufferedReader br = new BufferedReader(
//                    new FileReader(Directory + "Airports.csv"));
//
//            String s;
//            while ((s = br.readLine()) != null) {
//                if (s.contains(AirportID)) {
//                    return new Airport(s);
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return null;
//    }
//
//    Set<Airport> findAllDestinationAirports(String SourceAirportID) {
//        try {
//
//            BufferedReader br = new BufferedReader(
//                    new FileReader(Directory + "routes.csv"));
//            String s;
//            String[] attributes;
//            Set<Airport> Destination = new HashSet<Airport>();
//            ;
//            while ((s = br.readLine()) != null) {
//                attributes = s.split(",");
//                if (attributes[3].contains(SourceAirportID)) {
////                Get ID of Destination Airport, go to airport table and create Airport instance of Destination ID
//                    Destination.add(findAirportClass(attributes[5]));
//                }
//            }
//            return Destination;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//
//    Set<Airport> findroute() {
//        String[] Data = ProcessRequest(Request());
//        String SourceCity = Data[0];
//        String SourceCountry = Data[1];
//        String DestinationCity = Data[2];
//        String DestinationCountry = Data[3];
//        Set<Airport> route = new HashSet<>();
//        Airport min = null;
////        Set<Airport> frontier = new HashSet<Airport>();
//        Set<Airport> frontier;
//        Airport[] frontierList;
//        Airport SourceAirport = findAirportClass(SourceCity, SourceCountry);
//        Airport DestinationAirport = findAirportClass(DestinationCity, DestinationCountry);
////        Node node = new Node(findAirportClass(SourceCity, SourceCountry));
////        frontier.add(node.S_AirportID);
//        while (min == DestinationAirport) {
//            frontier = findAllDestinationAirports(Integer.toString(SourceAirport.airportID));
//            frontierList = frontier.toArray(new Airport[frontier.size()]);
//            min = frontierList[0];
//            double minHeuristic = Heuristic(min, DestinationAirport);
//            for (int i = 0; i < frontier.size(); i++) {
//                if (frontierList[i] == DestinationAirport) {
//                    route.add(frontierList[i]);
//                    return route;
//                } else if (Heuristic(frontierList[i], DestinationAirport) < minHeuristic) {
//                    min = frontierList[i];
//                }
//            }
//            route.add(min);
//        }
//        return route;
//
//
//    }
////    Node node = new Node(Requesjt)
////    processed_node_counter = 0
////
////            if problem.goal_test(node.state):// if airport state is destination return goal
////            return node.solution_path()
////
////    ArrayList<Airport> frontier = new ArrayList<Airport>(node.S_AirportID); // A frontier to keep all airport reachable from state airport
////    int frontierLen = 0 //
////    Set<Airport> explored = new HashSet<Airport>();// Keeps a set of all visited airports
////    while(frontier.size() > 0):
////    print("Frontier", frontierstr)
////    node = frontier.pop()
////            frontierstr.pop()
////            if (len(frontier) > frontierLen): frontierLen = len(frontier)
////    processed_node_counter = processed_node_counter + 1
////            explored.add(grid_to_str(node.state))
////    print("Popped: ", node)
////    actions, successors = problem.actions(node.state)
////    print("Generated successor states: ", successors)
////    print(processed_node_counter)
////    print(node.state)
////        for i in range(len(actions)):
////    child = Node(successors[i], node, actions[i],
////                 node.path_cost+1)
////            if (grid_to_str(child.state) not in explored and child not in frontier):
////            if (problem.goal_test(child.state)):
////    print("Found a solution! ", child)
////                    return child.solution_path(), child.path_cost, processed_node_counter, frontierLen
////                frontier.append(copy.deepcopy(child))
////            frontierstr.append(child.state)
////
//
//}