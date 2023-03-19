package dev.balakumar.keeptruckin.service;

import dev.balakumar.keeptruckin.dto.NewTruckDto;
import dev.balakumar.keeptruckin.model.Truck;
import dev.balakumar.keeptruckin.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class TruckService {

    @Autowired
    private TruckRepository truckRepository;

    /* Creates a new Truck document with given info */
    public Truck addTruck(NewTruckDto newTruckDto) {

        Truck truck = Truck.builder()
                .name(newTruckDto.getTruckName())
                .startDate(newTruckDto.getStartDate())
                .endDate(newTruckDto.getEndDate())
                .sunday(newTruckDto.isSunday())
                .monday(newTruckDto.isMonday())
                .tuesday(newTruckDto.isTuesday())
                .wednesday(newTruckDto.isWednesday())
                .thursday(newTruckDto.isThursday())
                .friday(newTruckDto.isFriday())
                .saturday(newTruckDto.isSaturday()).build();

        return truckRepository.save(truck);
    }

    /* Retreives the list of Trucks that operate on the current day and whose endDate has not passed */
    public List<Truck> getTodaysSchedule() {
        DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());

        switch (dayOfWeek) {
            case SUNDAY -> {
                return truckRepository.findAllByEndDateIsAfterAndSunday(new Date(), true);
            }
            case MONDAY -> {
                return truckRepository.findAllByEndDateIsAfterAndMonday(new Date(), true);
            }
            case TUESDAY -> {
                return truckRepository.findAllByEndDateIsAfterAndTuesday(new Date(), true);
            }
            case WEDNESDAY -> {
                return truckRepository.findAllByEndDateIsAfterAndWednesday(new Date(), true);
            }
            case THURSDAY -> {
                return truckRepository.findAllByEndDateIsAfterAndThursday(new Date(), true);
            }
            case FRIDAY -> {
                return truckRepository.findAllByEndDateIsAfterAndFriday(new Date(), true);
            }
            case SATURDAY -> {
                return truckRepository.findAllByEndDateIsAfterAndSaturday(new Date(), true);
            }
        }

        return List.of(); // return an empty list if none of the above pass
    }

    /* Retrieves the truck with the corresponding oldName and updates it to newName */
    public boolean updateTruckName(String oldName, String newName) {
        Truck truck = truckRepository.findFirstByName(oldName).orElse(null);
        if (truck == null) return false; // if the truck wasn't found return false

        truck.setName(newName);

        return truckRepository.save(truck).getName().equals(newName);
    }
}
