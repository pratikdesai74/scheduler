package distributed.task.scheduler.playground;

import distributed.task.scheduler.jobs.TaskDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaygroundRepository extends JpaRepository<TaskDetails,Integer> {
  //  void saveAll(List<TaskDetails> list);
  @Query("select b from TaskDetails b where b.isCompleted=false")
  public List<TaskDetails> getFirsttwoTasks();
}
