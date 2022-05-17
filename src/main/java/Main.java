import java.util.Scanner;

import Flights.FlightService;
import com.github.cliftonlabs.json_simple.JsonException;
import org.apache.log4j.BasicConfigurator;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The Main class provides main method that let us use functionalities of implemented Flights package.
 * JSON file of flight entities - src/test/resources/iata.json
 * JSON file of cargo entities - src/test/resources/cargo.json
 */
public class Main {

    public static void main(String[] args) throws IOException, JsonException {
        BasicConfigurator.configure();
        Scanner scanner = new Scanner(System.in);
        FlightService fs = new FlightService("src/test/resources/iata.json", "src/test/resources/cargo.json");
        Pattern date_pattern = Pattern.compile("[0-2]\\d\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[01])");

        System.out.println("Input what data do you want to get:\n");
        System.out.println("1. - Get info on cargo of flight");
        System.out.println("2. - Get info on airport traffic");
        int choice = scanner.nextInt();
        while(choice != 1 && choice !=2){
            System.out.println("Wrong number provided, input again:\n");
            System.out.println("1. - Get info on cargo of flight");
            System.out.println("2. - Get info on airport traffic\n");
            choice = scanner.nextInt();
        }
        if(choice == 1){
            System.out.println("Input what data do you want to get:\n");
            System.out.println("1. - Cargo Weight for Flight");
            System.out.println("2. - Baggage Weight for Flight");
            System.out.println("3. - Total Weight for Flight\n");
            int choice2 = scanner.nextInt();
            while(choice2 != 1 && choice2 !=2 && choice2 != 3){
                System.out.println("Wrong number provided, input again:\n");
                System.out.println("1. - Cargo Weight for Flight");
                System.out.println("2. - Baggage Weight for Flight");
                System.out.println("3. - Total Weight for Flight\n");
                choice2 = scanner.nextInt();
            }
            System.out.println("\nInput flight number: ");
            int flightNumber = scanner.nextInt();
            System.out.println("Input flight date in format YYYY-MM-DD: ");
            String flight_date = scanner.next();
            Matcher date_match = date_pattern.matcher(flight_date);
            boolean matchFound = date_match.find();
            while(!matchFound){
                System.out.println("Wrong date format, please enter it again in format YYYY-MM-DD: ");
                flight_date = scanner.next();
                date_match = date_pattern.matcher(flight_date);
                matchFound = date_match.find();
            }
            DateTime date = new DateTime(flight_date);
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
            System.out.println("4. - Total number (pieces) of baggage departing from airport");
            int choice3 = scanner.nextInt();
            while(choice3 != 1 && choice3 !=2 && choice3 != 3 && choice3 != 4){
                System.out.println("Wrong number provided, input again:\n");
                System.out.println("1. - Number of flights departing from airport");
                System.out.println("2. - Number of flights arriving to airport");
                System.out.println("3. - Total number (pieces) of baggage arriving to airport");
                System.out.println("4. - Total number (pieces) of baggage departing from airport");
                choice3 = scanner.nextInt();
            }
            System.out.println("\nInput IATA Airport Code: ");
            String IATAcode = scanner.next();
            System.out.println("Input flight date in format YYYY-MM-DD: ");
            String provided_date = scanner.next();
            Matcher date_match = date_pattern.matcher(provided_date);
            boolean matchFound = date_match.find();
            while(!matchFound){
                System.out.println("Wrong date format, please enter it again in format YYYY-MM-DD: ");
                provided_date = scanner.next();
                date_match = date_pattern.matcher(provided_date);
                matchFound = date_match.find();
            }
            DateTime date = new DateTime(provided_date);
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

