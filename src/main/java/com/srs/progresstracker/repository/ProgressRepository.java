package com.srs.progresstracker.repository;

import com.srs.progresstracker.document.DailyProgress;
import com.srs.progresstracker.document.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ProgressRepository extends MongoRepository<DailyProgress, LocalDate> {
}
