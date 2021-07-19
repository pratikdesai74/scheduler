package distributed.task.scheduler.jobs;

import com.fasterxml.jackson.databind.ObjectMapper;
import distributed.task.scheduler.info.TimerInfo;
import distributed.task.scheduler.playground.PlaygroundService;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrintJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(PrintJob.class);

    @Autowired
    PlaygroundService playgroundService;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    private static final String TASK_CREATE_TOPIC="task-created";

    @Autowired
    ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) {
        List<TaskDetails> list=playgroundService.getFirsttwoTasks();

        for(TaskDetails t:list){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",t.getId());
            jsonObject.put("taskType",t.getTaskType());
            jsonObject.put("startNumber",t.getStartNumber());
            jsonObject.put("endNumber",t.getEndNumber());

            kafkaTemplate.send(TASK_CREATE_TOPIC,t.getTaskType(),objectMapper.writeValueAsString(jsonObject));

        }
    }
}
