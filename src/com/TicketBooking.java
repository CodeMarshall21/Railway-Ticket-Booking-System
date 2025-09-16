package com;
import java.util.*;


public class TicketBooking {
    private final List<String> availableBerth = new ArrayList<>(Arrays.asList("L","U","M"));

    private final Queue<Passenger> racQueue = new LinkedList<>();

    private final Queue<Passenger> waitingList = new LinkedList<>();

    private final List<Passenger> confirmedPassengers = new ArrayList<>();

    private int ticketCounter = 1;

    public void bookTicket(String name, int age, String gender, String berthPreference){
        String ticketId = "T" + ticketCounter++;
        Passenger passenger;

        if(!availableBerth.isEmpty()){
            String allotedBerth = allotedBerth(gender, age, berthPreference);
        }


    }

    private String allotedBerth(String gender, int age ,String berthPreference){
        if(age > 60 || gender.equalsIgnoreCase("female") && availableBerth.contains("L")){
            return "L";
        }
        if(availableBerth.contains(berthPreference)){
            return berthPreference;
        }

        return availableBerth.get(0);
    }
}