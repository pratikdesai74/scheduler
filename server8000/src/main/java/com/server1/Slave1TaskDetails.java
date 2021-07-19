package com.server1;

import lombok.*;
import javax.persistence.Id;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Slave1TaskDetails implements Serializable {
    @Id
    @GeneratedValue
    private String id;

    private String taskType;

    private long startNumber;

    private long endNumber;

    private boolean isCompleted;

}
