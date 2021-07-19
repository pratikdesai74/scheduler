package distributed.task.scheduler.masterServer;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class TimerService{
    private static final Logger log= LoggerFactory.getLogger(TimerService.class);
    private final Scheduler scheduler;

    @Autowired
    public TimerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void init(){
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void preDestroy(){
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
