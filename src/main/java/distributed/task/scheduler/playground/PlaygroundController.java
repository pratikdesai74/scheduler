package distributed.task.scheduler.playground;

import distributed.task.scheduler.info.TimerInfo;
import distributed.task.scheduler.jobs.PrintJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timer")
public class PlaygroundController {
    private static final Logger log = LoggerFactory.getLogger(PlaygroundController.class);

    //private final
    @Autowired
    PlaygroundService service;

//    @Autowired
//    public PlaygroundController(PlaygroundService service) {
//        this.service = service;
//    }

    @GetMapping("/runmainjob/{numbertoprint}")
    public Boolean runMasterJob(@PathVariable String numbertoprint) {
        log.info("input number: "+numbertoprint);
        service.saveTaskDetailsInDb(Long.valueOf(numbertoprint));
        return true;
    }

}
