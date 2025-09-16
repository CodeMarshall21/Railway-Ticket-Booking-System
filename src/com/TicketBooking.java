package com;
import java.util.*;


public class TicketBooking {
    private final List<String> availableBerth = new ArrayList<>(Arrays.asList("L","U","M"));

    private final Queue<Passenger> racQueue = new LinkedList<>();

    private final Queue<Passenger> waitingList = new LinkedList<>();

    private final List<Passenger> confirmedPassengers = new ArrayList<>();

    private int ticketCounter = 1;

    private void bookTicket(String name, int age, String gender, String berthPreference){
        String ticketId = "T" + ticketCounter++;
        Passenger passenger;

        if(!availableBerth.isEmpty()){
            String allotedBerth = allotedBerth(gender, age, berthPreference);
            passenger = new Passenger(name, age, gender, berthPreference, allotedBerth, ticketId);

            confirmedPassengers.add(passenger);     // Add the confirmed passenger if they get the alloted berth

            availableBerth.remove(allotedBerth);    // Remove the alloted berth after giving it to a passenger as it has been occupied

            System.out.println("Ticket Confirmed : "+passenger);    // Prints the details of the Passenger

        }else if(racQueue.size() < 1){  // if the availableBerth is full
            passenger = new Passenger(name, age, gender, berthPreference, "RAC", ticketId);

            racQueue.offer(passenger);                           // Adds the passenger in tha tail of the RAC queue
            System.out.println("Ticket in RAC : "+passenger);    // Prints the details of the Passenger

        }else if(waitingList.size() < 1){  // if the racQueue is full
            passenger = new Passenger(name, age, gender, berthPreference, "RAC", ticketId);

            waitingList.offer(passenger);                                 // Adds the passenger in tha tail of the waitingList queue
            System.out.println("Ticket in Waiting List : "+passenger);    // Prints the details of the Passenger

        }else{
            System.out.println("NO TICKETS AVAILABLE !!");  // this happens otherwise....
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