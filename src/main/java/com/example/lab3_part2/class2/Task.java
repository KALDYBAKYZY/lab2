package com.example.lab3_part2.class2;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String name;
    private String deadlineDate;
    private boolean isCompleted;
    private String description;
}

