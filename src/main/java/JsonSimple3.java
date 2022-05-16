import Flights.Flight;
import Flights.Parser;
import Flights.WholeCargo;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.Jsoner;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.apache.log4j.BasicConfigurator;
import org.joda.time.DateTime;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JsonSimple3{

    public static void main(String[] args) throws IOException, JsonException {
        BasicConfigurator.configure();

//        ArrayList<Flight> flights = Parser.parseFlight("src/main/resources/generated.json");
        DateTime date = new DateTime("2021-12-27");
//        Flight fl0 = flights.get(0);
        System.out.println(date.toLocalDate());
        // The file `user3.json` is generated from above example 3.1
//        try (FileReader fileReader = new FileReader(("src/main/resources/generated.json"))) {


//            ArrayList<Flights.Flight> flight_register = o.stream()
//                    .map(x -> mapper.map(x, Flights.Flight.class)).collect(Collectors.toCollection(ArrayList::new));

//            System.out.println(flight_register.get(0).getDepartureDate().monthOfYear().getAsText());
            //end

            //working .json to Array<Flights.Cargo> code
//            JsonArray deserialized = Jsoner.deserializeMany(fileReader);
//            Mapper mapper = new DozerBeanMapper();
//            JsonArray o = (JsonArray) deserialized.get(0);
//            ArrayList<WholeCargo> cargos_register = o.stream()
//                    .map(x -> mapper.map(x, WholeCargo.class)).collect(Collectors.toCollection(ArrayList::new));
//
//
//            System.out.println(cargos_register.get(0).getFlightId());
//            System.out.println(cargos_register.get(0).getBaggage().get(0).toJson());
//            System.out.println(cargos_register.get(0).getBaggage().get(0).getWeightUnit());
//            System.out.println(cargos_register.get(0).getCargo().get(0).toJson());
            //end
//        }

    }

}