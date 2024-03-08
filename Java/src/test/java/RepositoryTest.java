import org.example.persistence.jdbc.BookingDbRepository;
import org.example.persistence.jdbc.ClientDbRepository;
import org.example.persistence.jdbc.FlightDbRepository;
import org.example.persistence.jdbc.UserDbRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class RepositoryTest {


    @Test
    @DisplayName("Test Client")
    public void testClient() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("C:\\Users\\40766\\IdeaProjects\\mpp-proiect-java-stolniceanudenisa\\bd.properties"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        ClientDbRepository clientRepository = new ClientDbRepository(properties);
        System.out.println(clientRepository.getAll());
    }

    @Test
    @DisplayName("Test User")
    public void testUser() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("C:\\Users\\40766\\IdeaProjects\\mpp-proiect-java-stolniceanudenisa\\bd.properties"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        UserDbRepository userRepository = new UserDbRepository(properties);
        System.out.println(userRepository.getAll());

    }


    @Test
    @DisplayName("Test Flight")
    public void testFLight() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("C:\\Users\\40766\\IdeaProjects\\mpp-proiect-java-stolniceanudenisa\\bd.properties"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        FlightDbRepository flightRepository = new FlightDbRepository(properties);
        //System.out.println(flightRepository.getAll());
        System.out.println(flightRepository.findOne(1L));
    }

    @Test
    @DisplayName("Test Booking")
    public void testBooking() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("C:\\Users\\40766\\IdeaProjects\\mpp-proiect-java-stolniceanudenisa\\bd.properties"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        BookingDbRepository bookingRepository = new BookingDbRepository(properties);
        System.out.println(bookingRepository.getAll());
        System.out.println(bookingRepository.findOne(2L));
    }

}