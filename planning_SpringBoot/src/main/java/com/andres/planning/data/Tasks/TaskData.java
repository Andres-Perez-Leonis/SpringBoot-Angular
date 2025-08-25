package com.andres.planning.data.Tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.andres.planning.data.SubTaks.SubTaskData;



public class TaskData {
    private TaskSummaryData _summaryData;

    private ArrayList<SubTaskData> _subTasks;

    public TaskData(String title, String category, LocalDateTime dueDate, int priority) {
        //this.title = title;
        //this.category = category;
        //this.dueDate = dueDate;
        //this.priority = priority;
        //this.completed = false;
        _summaryData = new TaskSummaryData(title, category, dueDate, priority);
        _subTasks = new ArrayList<SubTaskData>();
    }

    public TaskSummaryData GetTaskSummaryData() {
        return _summaryData;
    }
    
    public void SetTaskSummaryData(TaskSummaryData summaryData) {
        _summaryData = summaryData;
    }

    public ArrayList<SubTaskData> GetSubTasks() {
        return _subTasks;
    }

    public void SetSubTasks(ArrayList<SubTaskData> subtasks) {
        _subTasks = subtasks;
    }
}
