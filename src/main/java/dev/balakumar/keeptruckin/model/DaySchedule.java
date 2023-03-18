package dev.balakumar.keeptruckin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "dayschedules")
public class DaySchedule {
    // day code will be DDMMYYYY
    @Id
    String dayCode;

    List<String> trucks;
}
