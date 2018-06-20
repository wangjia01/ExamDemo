package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.*;

/*
 *类名和方法不能修改
 */
public class Schedule {

    private TaskInfo taskInfo = new TaskInfo();
    private Map<Integer , Integer> taskMap = new HashMap<Integer, Integer>();


    public static final int TRANSFER= 30;


    public int init() {
        if (taskInfo.getNodeId() >= 0) {
            registerNode(taskInfo.getNodeId());
            return ReturnCodeKeys.E001;
        }
        if (taskInfo.getNodeId() > -1) {
            registerNode(-1);
            return ReturnCodeKeys.E001;
        }
        // TODO 方法未实现qing
        return ReturnCodeKeys.E000;
    }


    public int registerNode(int nodeId) {
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }

        if (nodeId >= 0) {
            if (taskInfo.getNodeId() == 0) {
                taskInfo.setNodeId(nodeId);
                return ReturnCodeKeys.E003;
            } else {
                return ReturnCodeKeys.E005;
            }
        }

        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }

    public int unregisterNode(int nodeId) {
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }
        if (nodeId!=taskInfo.getNodeId() ){
            return ReturnCodeKeys.E007;
        }
        if (taskInfo.getNodeId() == nodeId){
            if (taskInfo.getTaskId()  == 0){
                deleteTask(nodeId);
                return ReturnCodeKeys.E006;
            }else{
                scheduleTask(TRANSFER);
                return ReturnCodeKeys.E006;
            }
        }
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }


    public int addTask(int taskId, int consumption) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        }
        if (taskMap.size() != 0){
            Iterator iter =taskMap.keySet().iterator();
            while (iter.hasNext()) {
                Integer key = (Integer) iter.next();
                if (taskId == key){
                    return ReturnCodeKeys.E010;
                }

            }
        }
        if (taskId >= 0){
            taskInfo.setTaskId(taskId);
            taskMap.put(taskInfo.getTaskId() , consumption);
            return ReturnCodeKeys.E008;
        }

        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }


    public int deleteTask(int taskId) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        }
        if (taskId >= 0){
            Iterator iter =taskMap.keySet().iterator();
            while (iter.hasNext()) {
                Integer key = (Integer) iter.next();
                if (taskId == key){
                    taskMap.remove(taskId);
                    return ReturnCodeKeys.E011;
                }else{
                    return ReturnCodeKeys.E012;
                }
            }
        }
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }


    public int scheduleTask(int threshold) {
        if (threshold <= 0){
            return ReturnCodeKeys.E002;
        }
        if (threshold < TRANSFER){
            return  ReturnCodeKeys.E013;
        }
        if (threshold <  TRANSFER){
            return  ReturnCodeKeys.E014;
        }
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        if (tasks.size() == 0){
            return  ReturnCodeKeys.E016;
        }
        List<TaskInfo> newTasks = new ArrayList<TaskInfo>();
        TaskInfo taskInfoNodes = new TaskInfo();
        if (tasks != null){
            for (TaskInfo task : tasks){
                if (task.getNodeId() == taskInfo.getNodeId()){
                    taskInfoNodes.setNodeId(task.getNodeId());
                }

                if (task.getTaskId() == taskInfo.getTaskId()){
                    taskInfoNodes.setTaskId(task.getTaskId());
                }

            }
            newTasks.add(taskInfoNodes);
            return ReturnCodeKeys.E015;
        }


        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }

}
