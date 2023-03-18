package dev.balakumar.keeptruckin.controller;

import dev.balakumar.keeptruckin.dto.RequestDto;
import dev.balakumar.keeptruckin.model.DaySchedule;
import dev.balakumar.keeptruckin.service.DayService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class DayController {
    @Autowired
    DayService dayService;
    @PostMapping("/api/truck")
    ResponseEntity<Void> postTruck (@RequestBody RequestDto request) throws URISyntaxException {

        if (request.getDayCode() == null || StringUtils.isBlank(request.getTruckName()))
            return ResponseEntity.badRequest().build();

        dayService.addTruck(request);
        return ResponseEntity.created(new URI("")).build();
    }

    @GetMapping("/api/schedule")
    ResponseEntity<DaySchedule> getTrucksForToday() {
        return ResponseEntity.ok(dayService.getTrucksForToday());
    }
}
