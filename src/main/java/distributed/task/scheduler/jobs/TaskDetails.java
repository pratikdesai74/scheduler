package distributed.task.scheduler.jobs;

import lombok.*;
//import org.springframework.data.annotation.Id;
import javax.persistence.Id;

import javax.persistence.*;
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
