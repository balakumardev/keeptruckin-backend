package dev.balakumar.keeptruckin;

import dev.balakumar.keeptruckin.dto.NewTruckDto;
import dev.balakumar.keeptruckin.model.Truck;
import dev.balakumar.keeptruckin.service.TruckService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class KeeptruckinBackendApplicationTests extends ContainerBase {

    @Autowired
    TruckService truckService;

    @Test
    void contextLoads() {
    }
    @Test
    @DisplayName("Create truck should be ok")
    @Order(1)
    void testCreate() throws ParseException {
        NewTruckDto truckDto = new NewTruckDto();
        truckDto.setTruckName("testtruck");
        truckDto.setEndDate(new SimpleDateFormat("yyyy/MM/dd").parse("2100/03/18"));
        truckDto.setSaturday(true);
        truckDto.setMonday(true);
        truckDto.setTuesday(true);
        truckDto.setWednesday(true);
        truckDto.setThursday(true);
        truckDto.setFriday(true);
        truckDto.setSunday(true);

        Truck savedTruck = truckService.addTruck(truckDto);

        Assertions.assertNotNull(savedTruck.getId());
    }


    @Test
    @DisplayName("Todays schedule should return the truck")
    @Order(2)
    void todayScheduleTest() {
        var foundTruck = truckService.getTodaysSchedule();

        Assertions.assertFalse(foundTruck.isEmpty());
    }

    @Test
    @DisplayName("Updating the truck name should reflect")
    @Order(3)
    void updateNameTest() {
        var foundTruck = truckService.updateTruckName("testtruck", "testtrucknew");

        Assertions.assertTrue(foundTruck);
    }
}
