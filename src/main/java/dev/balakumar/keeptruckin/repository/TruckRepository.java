package dev.balakumar.keeptruckin.repository;

import dev.balakumar.keeptruckin.model.Truck;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TruckRepository extends MongoRepository<Truck, String> {
    /* These find functions retreives the List of trucks which operate on the corresponding day and whose "endDate" has not passed */
    List<Truck> findAllByEndDateIsAfterAndSunday(Date today, boolean sunday);
    List<Truck> findAllByEndDateIsAfterAndMonday(Date today, boolean monday);
    List<Truck> findAllByEndDateIsAfterAndTuesday(Date today, boolean tuesday);
    List<Truck> findAllByEndDateIsAfterAndWednesday(Date today, boolean wednesday);
    List<Truck> findAllByEndDateIsAfterAndThursday(Date today, boolean thursday);
    List<Truck> findAllByEndDateIsAfterAndFriday(Date today, boolean friday);
    List<Truck> findAllByEndDateIsAfterAndSaturday(Date today, boolean saturday);

    /* Used for finding update scenario, where you'd need the Truck object first for the update to happen */
    Optional<Truck> findFirstByName(String name);
}
