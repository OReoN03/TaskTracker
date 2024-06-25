package com.example.tasktracker.service.mail;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);

    void sendSimpleMessageUsingTemplate(String to, String subject, String... template);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
}
