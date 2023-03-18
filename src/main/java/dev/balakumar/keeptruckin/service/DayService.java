package dev.balakumar.keeptruckin.service;

import dev.balakumar.keeptruckin.DayRepository;
import dev.balakumar.keeptruckin.dto.RequestDto;
import dev.balakumar.keeptruckin.model.DaySchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DayService {

    @Autowired
    private DayRepository dayRepository;


    public void addTruck(RequestDto request) {
        DaySchedule daySchedule = dayRepository.findById(request.getDayCode()).orElse(null);

        if (daySchedule == null) {
            daySchedule = new DaySchedule(request.getDayCode(), new ArrayList<String>());
        }

        List<String> trucks = daySchedule.getTrucks();
        trucks.add(request.getTruckName());

        dayRepository.save(daySchedule);
    }


    public DaySchedule getTrucksForToday() {

        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date currentDate = new Date();
        String currentDateString = dateFormat.format(currentDate);
        DaySchedule daySchedule = dayRepository.findById(currentDateString).orElse(new DaySchedule(currentDateString, new ArrayList<>()));

        return daySchedule;
    }
}
