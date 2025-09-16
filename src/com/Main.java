package com;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        TicketBooking ticketBooking = new TicketBooking();
        Scanner in = new Scanner(System.in);

        boolean isRunning = true;

        while(isRunning){
            System.out.println("\nRailway Booking System: ");
            System.out.println("1. Book Ticket \n2. Cancel Ticket \n3. View Confirmed Tickets \n4. View Available Tickets " +
                    "\n5. View RAC Tickets \n6. View Waiting List Tickets \n7. Exit");
            System.out.print("Enter your choice : ");
            int choice = in.nextInt();
            in.nextLine();

            switch (choice){
                case 1:
                    System.out.print("\nEnter Name: ");
                    String name = in.nextLine();

                    System.out.print("\nEnter Age: ");
                    int age = in.nextInt();

                    in.nextLine();

                    System.out.print("\nEnter Gender: ");
                    String gender = in.nextLine();

                    System.out.print("\nEnter Berth Preference (L / U / M): ");
                    String berthPreference = in.next();
                    ticketBooking.bookTicket(name, age, gender, berthPreference.toUpperCase());
                    break;

                case 2:
                    System.out.print("\nEnter Ticket ID: ");
                    String ticketId = in.next();

                    ticketBooking.cancelTicket(ticketId.toUpperCase());
                    break;

                case 3:
                    ticketBooking.printConfirmedTickets();
                    break;

                case 4:
                    ticketBooking.printAvailableTickets();
                    break;

                case 5:
                    ticketBooking.printRacTickets();
                    break;

                case 6:
                    ticketBooking.printWaitingListTickets();
                    break;

                case 7:
                    System.out.println("Exiting... See you soon !!!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("INVALID INPUT");
            }
        }
    }
}
