package dev.balakumar.keeptruckin.repository;

import dev.balakumar.keeptruckin.model.Truck;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TruckRepository extends MongoRepository<Truck, String> {
    List<Truck> findAllByEndDateIsAfterAndSunday(Date today, boolean sunday);
    List<Truck> findAllByEndDateIsAfterAndMonday(Date today, boolean monday);
    List<Truck> findAllByEndDateIsAfterAndTuesday(Date today, boolean tuesday);
    List<Truck> findAllByEndDateIsAfterAndWednesday(Date today, boolean wednesday);
    List<Truck> findAllByEndDateIsAfterAndThursday(Date today, boolean thursday);
    List<Truck> findAllByEndDateIsAfterAndFriday(Date today, boolean friday);
    Truck findByName(String name);
}
