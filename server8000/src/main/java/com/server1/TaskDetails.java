package com.server1;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetails implements Serializable{
    @Id
    @GeneratedValue
    private int id;

    private String taskType;

    private long startNumber;

    private long endNumber;

    private boolean isCompleted;

}
