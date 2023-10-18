package com.example;

parcelable Task;

interface ITaskManager {
    void addTask(Task task);
    void removeTask(Task task);
    Task getTask(int index);
    List<Task> getAllTasks();
}