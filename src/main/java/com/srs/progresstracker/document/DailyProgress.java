package com.srs.progresstracker.document;

import com.srs.progresstracker.dto.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// Add the required annotations to make this class a document class.

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
@Builder
public class DailyProgress {
    @Id
    private String date;
    private List<TaskProgress> progressList;
}
