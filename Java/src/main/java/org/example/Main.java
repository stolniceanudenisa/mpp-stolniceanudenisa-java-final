package org.example;

import org.example.model.Booking;
import org.example.model.Client;
import org.example.model.Flight;
import org.example.persistence.jdbc.BookingDbRepository;
import org.example.persistence.jdbc.ClientDbRepository;
import org.example.persistence.jdbc.FlightDbRepository;
import org.example.persistence.jdbc.UserDbRepository;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(new FileReader("C:\\Users\\40766\\IdeaProjects\\mpp-proiect-java-stolniceanudenisa\\bd.properties"));
            System.out.println("Found");
            System.out.println();System.out.println();System.out.println();
        } catch (IOException e) {
            System.out.println("Cannot find bd.config " + e);System.out.println();System.out.println();System.out.println();
        }

        ClientDbRepository clientDbRepository = new ClientDbRepository(props);
//        clientDbRepository.add(new Client(99L,"Diana","AAAA"));
//        clientDbRepository.delete(99L);
//        clientDbRepository.findOne(1L);
//        Client client_u = new Client(6L,"Alexandru Pop","Strada Closca 9");
//        clientDbRepository.update(client_u);

//
        clientDbRepository.getAll().forEach(System.out::println);
        System.out.println();System.out.println();System.out.println();
//
//      sau asa
//        for(Client cl:clientDbRepository.getAll())
//            System.out.println(cl);



        UserDbRepository userDbRepository = new UserDbRepository(props);
//        userDbRepository.add(new User(12L,"diana090","123456"));
//        userDbRepository.delete(12L);
//        userDbRepository.findOne(1L);
//        User user_u = new User(12L,"alexandra090","12");
//        userDbRepository.update(user_u);

//        userDbRepository.getAll().forEach(System.out::println);
//        System.out.println();System.out.println();System.out.println();


        FlightDbRepository flightDbRepository = new FlightDbRepository(props);
//        flightDbRepository.add(new Flight(2L,"Charles de Gaulle", LocalDateTime.of(2024, 7, 17, 21, 0),"Avram Iancu", 180));
//        flightDbRepository.add(new Flight(3L,"Bucuresti", LocalDateTime.of(2024, 6, 1, 14, 30),"Timisoara", 70));
//        flightDbRepository.add(new Flight(4L,"Barcelona-El Prat", LocalDateTime.of(2024, 5, 5, 11, 15),"Schipol", 150));
//        flightDbRepository.add(new Flight(5L,"Roma Fiumicino", LocalDateTime.of(2024, 5, 22, 14, 50),"London Heathrow", 180));
//        flightDbRepository.add(new Flight(6L,"New York", LocalDateTime.of(2024, 8, 9, 9, 45),"Los Angeles", 260));
//        flightDbRepository.add(new Flight(7L,"Chicago", LocalDateTime.of(2024, 4, 14, 9, 45),"New York", 300));
//

        // sau asa Flight(6L,"Cluj", LocalDateTime.now(),"Aeroportul Cluj", 100);
//        flightDbRepository.delete(103L);

       // flightDbRepository.findOne(102L);  // eroare la parse

//        Flight flight_u = new Flight(6L,"Cluj", LocalDateTime.now(),"Aeroportul Cluj", 100);  // de terminat si update
//        flightDbRepository.update(flight_u);

//        flightDbRepository.getAll().forEach(System.out::println);
//        System.out.println();System.out.println();System.out.println();


        BookingDbRepository bookingDbRepository = new BookingDbRepository(props);
//        bookingDbRepository.add(new Booking(7L,flightDbRepository.findOne(3L),clientDbRepository.findOne(2L), null));
//        bookingDbRepository.add(new Booking(14L, flightDbRepository.findOne(3L), clientDbRepository.findOne(2L), List.of("Ana O.", "Ioana M.")));
//        sau asa
//        bookingDbRepository.add(new Booking(13L, new Flight(1L, "Cluj", LocalDateTime.of(2024, 3, 14, 9, 45), "Aeroportul Cluj", 100), new Client(1L, "ClientName", "ClientAddress"), List.of("ZZZ", "ZZZZ")));


//        bookingDbRepository.delete(13L);
//        bookingDbRepository.findOne(1L);
//        Booking booking_u = new Booking(12L,flightDbRepository.findOne(3L),clientDbRepository.findOne(2L), List.of("OOOOO", "OOOOOO"));
//        bookingDbRepository.update(booking_u);
        bookingDbRepository.getAll().forEach(System.out::println);
        System.out.println();System.out.println();System.out.println();


    }
}