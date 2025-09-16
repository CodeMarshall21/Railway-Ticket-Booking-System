package com;
import java.sql.SQLOutput;
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
            String allotedBerth = allotBerth(gender, age, berthPreference);
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

    private String allotBerth(String gender, int age ,String berthPreference){
        if(age > 60 || gender.equalsIgnoreCase("female") && availableBerth.contains("L")){
            return "L";
        }
        if(availableBerth.contains(berthPreference)){
            return berthPreference;
        }

        return availableBerth.get(0);
    }

    private void cancelTicket(String ticketID){

        boolean foundIt = false;

        for(Passenger passenger: confirmedPassengers){
            if (passenger.ticketId.equalsIgnoreCase(ticketID)){
                availableBerth.add(passenger.allotedBerth);
                confirmedPassengers.remove(passenger);

                if(!racQueue.isEmpty()){
                    Passenger racPassenger = racQueue.poll();
                    racPassenger.allotedBerth = passenger.allotedBerth;
                    confirmedPassengers.add(racPassenger);
                    System.out.println("RAC passenger moved to Confirmed ticket: "+racPassenger);
                }

                if(!waitingList.isEmpty()){
                    Passenger waitingListPassenger = waitingList.poll();
                    waitingListPassenger.allotedBerth = "RAC";
                    racQueue.offer(waitingListPassenger);
                    System.out.println("Waiting List passenger moved to RAC ticket: "+ waitingListPassenger);
                }
                foundIt = true;
                break;
            }
        }
        if(!foundIt){
            System.out.println("NO CONFIRMED TICKET FOUND WITH GIVEN ID !!!");
        }
    }

    private void printTickets(){
        if(!confirmedPassengers.isEmpty()){
            System.out.println("Confirmed Tickets :- ");
            for (Passenger passenger: confirmedPassengers){
                System.out.println(passenger);
            }
        }else{
            System.out.println("NO CONFIRMED BOOKINGS SO FAR...!!!");
        }
    }

}