package org.example;

import org.example.persistence.jdbc.ClientDbRepository;
import org.example.persistence.jdbc.FlightDbRepository;
import org.example.persistence.jdbc.UserDbRepository;

import java.io.FileReader;
import java.io.IOException;
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
// sau asa
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
//        flightDbRepository.add(new Flight(103L,"Cluj", LocalDateTime.of(2024, 3, 14, 9, 45),"Aeroportul Cluj", 100));
        // sau asa Flight(6L,"Cluj", LocalDateTime.now(),"Aeroportul Cluj", 100);
//        flightDbRepository.delete(103L);

       // flightDbRepository.findOne(102L);  // eroare la parse

//        Flight flight_u = new Flight(6L,"Cluj", LocalDateTime.now(),"Aeroportul Cluj", 100);  // de terminat si update
//        flightDbRepository.update(flight_u);


//        flightDbRepository.getAll().forEach(System.out::println);
//        System.out.println();System.out.println();System.out.println();

    }
}