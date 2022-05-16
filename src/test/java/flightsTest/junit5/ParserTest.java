package flightsTest.junit5;

import Flights.Flight;
import Flights.Parser;
import Flights.WholeCargo;

import org.apache.log4j.BasicConfigurator;

import com.github.cliftonlabs.json_simple.JsonException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.ArrayList;


public class ParserTest {


    @Test
    @DisplayName("Flights parsing test")
    void testParserFlight() throws JsonException, IOException {
        BasicConfigurator.configure();
        ArrayList<Flight> flights = Parser.parseFlight("src/test/resources/generated.json");
        assertEquals(1, flights.get(0).getFlightId());
        assertEquals(3100, flights.get(0).getFlightNumber());
        assertEquals("YYZ", flights.get(0).getDepartureAirportIATACode());
        assertEquals("MIT", flights.get(0).getArrivalAirportIATACode());
        assertEquals("2021-12-27T07:40:59-01:00", flights.get(0).getDepartureDate());

        assertEquals(3, flights.get(3).getFlightId());
        assertEquals(9965, flights.get(3).getFlightNumber());
        assertEquals("SEA", flights.get(3).getDepartureAirportIATACode());
        assertEquals("MIT", flights.get(3).getArrivalAirportIATACode());
        assertEquals("2018-12-14T12:31:18-01:00", flights.get(3).getDepartureDate());

    }

    @Test
    @DisplayName("Cargo parsing test")
    void testParserCargo() throws JsonException, IOException {
        BasicConfigurator.configure();
        ArrayList<WholeCargo> wholeCargos = Parser.parseCargo("src/test/resources/cargo.json");
        assertEquals(0, wholeCargos.get(0).getFlightId());
        assertEquals(0, wholeCargos.get(0).getBaggage().get(0).getId());

        assertEquals(1, wholeCargos.get(1).getFlightId());
        assertEquals(1, wholeCargos.get(1).getBaggage().get(1).getId());

        assertEquals(555, wholeCargos.get(1).getBaggage().get(1).getWeight());
        assertEquals("lb", wholeCargos.get(1).getBaggage().get(1).getWeightUnit());
        assertEquals(247, wholeCargos.get(1).getBaggage().get(1).getPieces());


    }
}
