package com.example.tasktracker.app.api.user;

import com.example.tasktracker.domain.user.User;

import java.util.List;

public interface FindAllUsersInbound {
    List<User> execute();
}
