package flightsTest.junit5;

import Flights.Flight;
import Flights.FlightService;
import Flights.Parser;
import Flights.WholeCargo;

import org.apache.log4j.BasicConfigurator;

import com.github.cliftonlabs.json_simple.JsonException;
import org.joda.time.DateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;

import java.util.ArrayList;


public class FlightServiceTest {

    @Test
    @DisplayName("Flights service module test")
    void testCargoSum() throws JsonException, IOException {
        BasicConfigurator.configure();
        ArrayList<Flight> flights = Parser.parseFlight("src/test/resources/generated.json");
        ArrayList<WholeCargo> wholeCargos = Parser.parseCargo("src/test/resources/cargo.json");
        FlightService fs = new FlightService(flights,wholeCargos);


        assertEquals(1951.42279017, fs.getTotalWeight(3100, new DateTime("2021-12-27")));
        assertEquals(4516.32132793, fs.getTotalWeight(6359, new DateTime("2022-04-19")));


    }

    @Test
    @DisplayName("Total arriving flights test")
    void testTotalArriving() throws JsonException, IOException {
        BasicConfigurator.configure();
        ArrayList<Flight> flights = Parser.parseFlight("src/test/resources/iata.json");
        ArrayList<WholeCargo> wholeCargos = Parser.parseCargo("src/test/resources/cargo.json");
        FlightService fs = new FlightService(flights,wholeCargos);


        assertEquals(3, fs.getArrivingNumber("GDN", new DateTime("2021-12-27")));
        assertEquals(0, fs.getArrivingNumber("YYT", new DateTime("2021-12-27")));
        assertEquals(2, fs.getDepartingNumber("SEA", new DateTime("2021-12-27")));
        assertEquals(0, fs.getDepartingNumber("GDN", new DateTime("2021-12-27")));
//        assertEquals(4516.32132793, fs.getTotalWeight(6359, new DateTime("2022-04-19")));


    }
}
