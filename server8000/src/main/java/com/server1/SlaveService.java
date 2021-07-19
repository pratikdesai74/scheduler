package com.server1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import distributed.task.scheduler.jobs.PrintJob;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SlaveService {
    private static final Logger log = LoggerFactory.getLogger(SlaveService.class);

    @Autowired
    Slave1Repository slave1Repository;

    @Autowired
    ObjectMapper objectMapper;

    private static final String TASK_CREATE_TOPIC="task-created";

    public void printTheValues(long start,long end){
        log.info("-------------------------------------");
        log.info("start: "+start);
        log.info("end: "+end);

        for (long i=start;i<=end;i++){
            System.out.println(i);
        }
    }

    @KafkaListener(topics = {TASK_CREATE_TOPIC},groupId = "task-group")
    public void runSlaveTask(String message) throws JsonProcessingException {
        log.info("Listner--------------------------"+message);
        JSONObject jsonObjectOFUserData=objectMapper.readValue(message, JSONObject.class);


        printTheValues((long)jsonObjectOFUserData.get("startNumber"),(long)jsonObjectOFUserData.get("endNumber"));
        TaskDetails slave1TaskDetails=TaskDetails.builder().
                id((int) jsonObjectOFUserData.get("id")).
                taskType((String)jsonObjectOFUserData.get("taskType")).
                startNumber((long)jsonObjectOFUserData.get("startNumber")).
                endNumber((long)jsonObjectOFUserData.get("endNumber")).
                isCompleted(true).
                build();
        slave1Repository.save(slave1TaskDetails);
    }
}
