package dev.balakumar.keeptruckin;

import dev.balakumar.keeptruckin.model.Truck;
import dev.balakumar.keeptruckin.repository.TruckRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositoryTest extends ContainerBase {

    @Autowired
    TruckRepository truckRepository;

    @Test
    @DisplayName("Create truck should be ok")
    @Order(1)
    void testCreate() throws ParseException {
        var truck = Truck.builder()
                .name("mytruck")
                .endDate(new SimpleDateFormat("yyyy/MM/dd").parse("2023/03/18"))
                .saturday(true)
                .build();
        var savedTruck = truckRepository.save(truck);

        Assertions.assertNotNull(savedTruck.getId());
    }

    @Test
    @DisplayName("Finding the truck should be ok")
    @Order(2)
    void testFind() {
        var foundTruck = truckRepository.findFirstByName("mytruck");

        Assertions.assertTrue(foundTruck.isPresent());
    }

    @Test
    @DisplayName("Truck which is outside the window shouldn't be returned")
    @Order(3)
    void testEndDateNegative() {
        var foundTruck = truckRepository.findAllByEndDateIsAfterAndSaturday(new Date(), true);

        Assertions.assertTrue(foundTruck.isEmpty());
    }

    @Test
    @DisplayName("Truck which is inside the window should be returned")
    @Order(4)
    void testEndDatePositive() throws ParseException {
        var foundTruck = truckRepository.findAllByEndDateIsAfterAndSaturday(new SimpleDateFormat("yyyy/MM/dd").parse("2023/01/18"), true);

        Assertions.assertFalse(foundTruck.isEmpty());
    }
}
