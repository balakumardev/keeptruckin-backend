package dev.balakumar.keeptruckin.controller;

import dev.balakumar.keeptruckin.dto.NewTruckDto;
import dev.balakumar.keeptruckin.dto.UpdateTruckDto;
import dev.balakumar.keeptruckin.model.Truck;
import dev.balakumar.keeptruckin.service.TruckService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DayController {
    @Autowired
    TruckService truckService;

    /* creates a truck document with the given info from the request body */
    @PostMapping("/api/truck")
    ResponseEntity<Void> postTruck(@RequestBody NewTruckDto request) {

        if (request.getEndDate() == null || StringUtils.isBlank(request.getTruckName()))
            return ResponseEntity.badRequest().build();

        truckService.addTruck(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* updates the existing truck record with the given info */
    @PutMapping("/api/truck")
    ResponseEntity<Void> updateTruck(@RequestBody UpdateTruckDto request) {

        if (StringUtils.isBlank(request.getOldName()) || StringUtils.isBlank(request.getNewName()))
            return ResponseEntity.badRequest().build();

        if (truckService.updateTruckName(request.getOldName(), request.getNewName()))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }


    /* retrieves the list of trucks that operate on the current day */
    @GetMapping("/api/schedule/today")
    ResponseEntity<List<Truck>> getTrucksForToday() {
        return ResponseEntity.ok(truckService.getTodaysSchedule());
    }
}
