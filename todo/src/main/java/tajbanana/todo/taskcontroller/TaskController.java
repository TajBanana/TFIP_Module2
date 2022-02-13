package tajbanana.todo.taskcontroller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path="/task", produces = MediaType.TEXT_HTML_VALUE)
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(
            TaskController.class);

/*    @GetMapping("/")
    public String logTask(Model model) {
        Task task = new Task();
        model.addAttribute("TaskList", task);
        return "";
    }*/

/*    @PostMapping
    public String logTask(Model model, @ModelAttribute Task task) {
        model.addAttribute("task",task.getTaskName());
        logger.info("task: " + task.getTaskName());
        return "";
    }*/

    @PostMapping
    public String postMethodName(@RequestBody
                                 MultiValueMap<String,String> form,
                                 Model model){
        String taskName = form.getFirst("taskName");
        String contents = form.getFirst("contents");
        logger.info("contents: %s".formatted(contents));

        List<String> tasks = new ArrayList<>();

        logger.info("\n input task name: %s \n input contents: %s"
                .formatted(taskName,contents));

        if (null != contents && contents.trim().length() > 0){
            contents = "%s,%s".formatted(contents,taskName);
            tasks = Arrays.asList(contents.split(","));
            logger.info("if");
            logger.info("contents: %s".formatted(contents));
            logger.info("tasks: %s".formatted(tasks));
        } else {
            contents = taskName;
            tasks.add(contents);
            logger.info("else");
            logger.info("contents: %s".formatted(contents));
            logger.info("tasks: %s".formatted(tasks));
        }

        model.addAttribute("outputContent",contents);
        model.addAttribute("tasks", tasks);

        logger.info("contents: %s".formatted(contents));

        return "index";
    }
}
