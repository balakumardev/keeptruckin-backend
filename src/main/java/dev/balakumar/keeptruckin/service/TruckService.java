package dev.balakumar.keeptruckin.service;

import dev.balakumar.keeptruckin.dto.NewTruckDto;
import dev.balakumar.keeptruckin.model.Truck;
import dev.balakumar.keeptruckin.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TruckService {

    @Autowired
    private TruckRepository truckRepository;

    public void addTruck(NewTruckDto newTruckDto) {
        Truck truck = new Truck();
        truck.setName(newTruckDto.getTruckName());
        truck.setStartDate(newTruckDto.getStartDate());
        truck.setEndDate(newTruckDto.getEndDate());
        truck.setSunday(newTruckDto.isSunday());
        truck.setMonday(newTruckDto.isMonday());
        truck.setTuesday(newTruckDto.isTuesday());
        truck.setWednesday(newTruckDto.isWednesday());
        truck.setThursday(newTruckDto.isThursday());
        truck.setFriday(newTruckDto.isFriday());
        truck.setSaturday(newTruckDto.isSaturday());
        truckRepository.save(truck);
    }

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
        }

        return new ArrayList<>();
    }

    public boolean updateTruckName(String oldName, String newName) {
        Truck truck = truckRepository.findByName(oldName);
        truck.setName(newName);

        return truckRepository.save(truck).getName().equals(newName);
    }
}
