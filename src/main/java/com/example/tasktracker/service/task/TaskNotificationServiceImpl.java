package com.example.tasktracker.service.task;

import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.task.TaskRepository;
import com.example.tasktracker.service.mail.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskNotificationServiceImpl implements TaskNotificationService {
    private final TaskRepository taskRepository;

    private final EmailService emailService;

    @Scheduled(cron = "0 0 9 * * MON-FRI")
    public void sendTaskNotifications() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeDaysFromNow = now.plusDays(1);
        LocalDateTime oneDayFromNow = now.plusDays(3);

        List<Task> tasks = taskRepository.findByDeadlineBetween(now, threeDaysFromNow);
        tasks.addAll(this.taskRepository.findByDeadlineBetween(now, oneDayFromNow));

        for (Task task : tasks) {
            sendNotification(task);
        }
    }

    private void sendNotification(Task task) {
        String assigneeEmail = task.getAssignee().getEmail();
        String subject = "Task deadline approaching: " + task.getTitle();
        String body = "Dear " + task.getAssignee().getFirstName() + ",\n"
                + "you have " + getRemainingDays(task)
                + " days left to complete task " + task.getTitle() + ".\n";
        emailService.sendSimpleMessage(assigneeEmail, subject, body);
    }

    private int getRemainingDays(Task task) {
        return (int) ChronoUnit.DAYS.between(LocalDateTime.now(), task.getDeadline());
    }
}
