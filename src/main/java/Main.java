import java.util.Scanner;

import Flights.FlightService;
import com.github.cliftonlabs.json_simple.JsonException;
import org.apache.log4j.BasicConfigurator;
import org.joda.time.DateTime;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, JsonException {
        BasicConfigurator.configure();

        Scanner scanner = new Scanner(System.in);
        FlightService fs = new FlightService("src/test/resources/iata.json", "src/test/resources/cargo.json");
        System.out.println("Input what data do you want to get:\n");
        System.out.println("1. - Get info on cargo of flight");
        System.out.println("2. - Get info on airport traffic");
        int choice = scanner.nextInt();
        while(choice != 1 && choice !=2){
            System.out.println("Wrong number provided, input again:\n");
            System.out.println("1. - Get info on cargo of flight");
            System.out.println("2. - Get info on airport traffic");
            choice = scanner.nextInt();
        }
        if(choice == 1){
            System.out.println("Input what data do you want to get:\n");
            System.out.println("1. - Cargo Weight for Flight");
            System.out.println("2. - Baggage Weight for Flight");
            System.out.println("3. - Total Weight for Flight");
            int choice2 = scanner.nextInt();
            while(choice2 != 1 && choice2 !=2 && choice2 != 3){
                System.out.println("Wrong number provided, input again:\n");
                System.out.println("1. - Cargo Weight for Flight");
                System.out.println("2. - Baggage Weight for Flight");
                System.out.println("3. - Total Weight for Flight");
                choice2 = scanner.nextInt();
            }
            System.out.println("\nInput flight number: ");
            int flightNumber = scanner.nextInt();
            System.out.println("Input flight date in format YYYY-MM-DD: ");
            DateTime date = new DateTime(scanner.next());
            if(choice2 == 1){
                System.out.println("Cargo Weight of that flight = " + fs.getCargoWeight(flightNumber, date) +"kg");
            }
            else if(choice2 == 2){
                System.out.println("Baggage Weight of that flight = " + fs.getBaggageWeight(flightNumber, date)+"kg");
            }
            else{
                System.out.println("Total Weight of that flight = " + fs.getTotalWeight(flightNumber, date)+"kg");
            }

        }
        else{
            System.out.println("Input what data do you want to get:\n");
            System.out.println("1. - Number of flights departing from airport");
            System.out.println("2. - Number of flights arriving to airport");
            System.out.println("3. - Total number (pieces) of baggage arriving to airport");
            System.out.println("4. - Total number (pieces) of baggage departing to airport");
            int choice3 = scanner.nextInt();
            while(choice3 != 1 && choice3 !=2 && choice3 != 3 && choice3 != 4){
                System.out.println("Wrong number provided, input again:\n");
                System.out.println("1. - Number of flights departing from airport");
                System.out.println("2. - Number of flights arriving to airport");
                System.out.println("3. - Total number (pieces) of baggage arriving to airport");
                System.out.println("4. - Total number (pieces) of baggage departing to airport");
                choice3 = scanner.nextInt();
            }
            System.out.println("\nInput IATA Airport Code: ");
            String IATAcode = scanner.next();
            System.out.println("Input flight date in format YYYY-MM-DD: ");
            DateTime date = new DateTime(scanner.next());
            if(choice3 == 1){
                System.out.println("Number of flights departing from this airport = " + fs.getDepartingNumber(IATAcode, date));
            }
            else if(choice3 == 2){
                System.out.println("Number of flights arriving to this airport = " + fs.getArrivingNumber(IATAcode, date));
            }
            else if (choice3 == 3){
                System.out.println("Total number (pieces) of baggage arriving to this airport = " + fs.getArrivingBaggagePieces(IATAcode, date));
            }
            else{
                System.out.println("Total number (pieces) of baggage departing from this airport = " + fs.getArrivingBaggagePieces(IATAcode, date));
            }

        }

    }
}

