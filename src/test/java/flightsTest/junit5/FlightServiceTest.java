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
    @DisplayName("Flights service baggage weight cacalculation test")
    void testBaggageWeightSum() throws JsonException, IOException {
        BasicConfigurator.configure();
        FlightService fs = new FlightService("src/test/resources/iata.json","src/test/resources/cargo.json");

        assertEquals(0, fs.getBaggageWeight(1983, new DateTime("2021-12-27")));
        assertEquals(2728, fs.getBaggageWeight(1983, new DateTime("2015-09-15")));

    }

    @Test
    @DisplayName("Flights service cargo weight cacalculation test")
    void testCargoWeightSum() throws JsonException, IOException {
        BasicConfigurator.configure();
        FlightService fs = new FlightService("src/test/resources/iata.json","src/test/resources/cargo.json");

        assertEquals(1289.35153645, fs.getCargoWeight(6359, new DateTime("2021-12-27")));
        assertEquals(0, fs.getCargoWeight(3100, new DateTime("2020-11-15")));


    }
    @Test
    @DisplayName("Flights service weight cacalculation test")
    void testTotalWeightSum() throws JsonException, IOException {
        BasicConfigurator.configure();
        FlightService fs = new FlightService("src/test/resources/iata.json","src/test/resources/cargo.json");

        assertEquals(1951.42279017, fs.getTotalWeight(3100, new DateTime("2021-12-27")));
        assertEquals(4516.32132793, fs.getTotalWeight(6359, new DateTime("2022-04-19")));
        assertEquals(0, fs.getTotalWeight(1111, new DateTime("2022-04-19")));


    }

    @Test
    @DisplayName("Total arriving flights test")
    void testTotalArriving() throws JsonException, IOException {
        BasicConfigurator.configure();
        FlightService fs = new FlightService("src/test/resources/iata.json","src/test/resources/cargo.json");

        assertEquals(3, fs.getArrivingNumber("GDN", new DateTime("2021-12-27")));
        assertEquals(0, fs.getArrivingNumber("YYT", new DateTime("2021-12-27")));
    }

    @Test
    @DisplayName("Total departing flights test")
    void testTotalDeparting() throws JsonException, IOException {
        BasicConfigurator.configure();
        FlightService fs = new FlightService("src/test/resources/iata.json","src/test/resources/cargo.json");

        assertEquals(2, fs.getDepartingNumber("SEA", new DateTime("2021-12-27")));
        assertEquals(0, fs.getDepartingNumber("GDN", new DateTime("2021-12-27")));
    }

    @Test
    @DisplayName("Total arriving pieces of baggage test")
    void testTotalArrivingBaggagePieces() throws JsonException, IOException {
        BasicConfigurator.configure();
        FlightService fs = new FlightService("src/test/resources/iata.json","src/test/resources/cargo.json");

        assertEquals(5602, fs.getArrivingBaggagePieces("GDN", new DateTime("2021-12-27")));
        assertEquals(3632, fs.getArrivingBaggagePieces("PPX", new DateTime("2021-12-27")));
    }


    @Test
    @DisplayName("Total departing pieces of baggage test")
    void testTotalDepartingBaggagePieces() throws JsonException, IOException {
        BasicConfigurator.configure();
        FlightService fs = new FlightService("src/test/resources/iata.json","src/test/resources/cargo.json");

        assertEquals(1259, fs.getDepartingBaggagePieces("YYT", new DateTime("2021-12-27")));
        assertEquals(5915, fs.getDepartingBaggagePieces("SEA", new DateTime("2021-12-27")));
    }
}
