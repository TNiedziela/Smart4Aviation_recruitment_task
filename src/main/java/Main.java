import java.io.Console;
import java.util.Scanner;

import Flights.FlightService;
import com.github.cliftonlabs.json_simple.JsonException;
import org.apache.log4j.BasicConfigurator;
import org.joda.time.DateTime;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, JsonException {
        BasicConfigurator.configure();
        Console console = System.console();

        if(console != null){
            Scanner scanner = new Scanner(console.reader());
            FlightService fs = new FlightService("src/test/resources/iata.json", "src/test/resources/cargo.json");
            console.writer().println("Input what do you want to know");
            console.writer().println("1. - Get info on cargo of flight");
            console.writer().println("2. - Get info on airport traffic");
            int choice = scanner.nextInt();
            if(choice == 1){
                console.writer().println("Input flight number: ");
                int flightNumber = scanner.nextInt();
                console.writer().println("Input flight date: ");
                DateTime date = new DateTime(scanner.next());
                console.writer().println("Result = ");
                console.writer().println(fs.getTotalWeight(flightNumber, date));
            }
        }
        else {
            System.out.println("Console failed");
        }

    }

}