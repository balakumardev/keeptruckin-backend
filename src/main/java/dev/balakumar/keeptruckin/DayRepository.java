package dev.balakumar.keeptruckin;

import dev.balakumar.keeptruckin.model.DaySchedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends MongoRepository<DaySchedule, String> {
}
