package distributed.task.scheduler.playground;

import distributed.task.scheduler.info.TimerInfo;
import distributed.task.scheduler.jobs.PrintJob;
import distributed.task.scheduler.jobs.TaskDetails;
import distributed.task.scheduler.timerservice.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaygroundService {
    private static final Logger log = LoggerFactory.getLogger(PlaygroundService.class);

    private final SchedulerService scheduler;

    @Autowired
    PlaygroundRepository playgroundRepository;

    @Autowired
    public PlaygroundService(final SchedulerService scheduler) {
        this.scheduler = scheduler;
    }

    public void runMasterJob() {
        final TimerInfo info = new TimerInfo();
        info.setTotalFireCount(5);
        info.setRemainingFireCount(info.getTotalFireCount());
        info.setRepeatIntervalMs(5000);
        info.setInitialOffsetMs(1000);
        info.setCallbackData("My callback data");

        scheduler.schedule(PrintJob.class, info);
    }

    public void saveTaskDetailsInDb(long n){
        TaskDetails taskDetails=TaskDetails.builder().taskType("Print numbers")
                                .id((int)Math.random()*10000).startNumber(0).endNumber(n/2)
                                .isCompleted(false).build();
        log.info("----------task----------"+taskDetails);
        playgroundRepository.save(taskDetails);

        TaskDetails taskDetails1=TaskDetails.builder().taskType("Print numbers")
                .id((int)Math.random()*10000).startNumber((n/2)+1).endNumber(n)
                .isCompleted(false).build();
        log.info("----------task 2----------"+taskDetails1);

        playgroundRepository.save(taskDetails);


    }
    public Boolean deleteTimer(final String timerId) {
        return scheduler.deleteTimer(timerId);
    }

    public List<TaskDetails> getFirsttwoTasks() {
        return playgroundRepository.getFirsttwoTasks();
    }

    public TimerInfo getRunningTimer(final String timerId) {
        return scheduler.getRunningTimer(timerId);
    }
}
