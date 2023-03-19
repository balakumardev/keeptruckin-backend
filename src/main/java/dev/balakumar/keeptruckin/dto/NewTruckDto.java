package dev.balakumar.keeptruckin.dto;

import lombok.Data;

import java.util.Date;
@Data
public class NewTruckDto {
    String truckName;
    Date startDate;
    Date endDate;

    boolean sunday;
    boolean monday;
    boolean tuesday;
    boolean wednesday;
    boolean thursday;
    boolean friday;
    boolean saturday;
}
