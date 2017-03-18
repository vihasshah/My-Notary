package com.example.dell.mynotary.Schedule;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2/21/2017.
 */

public class ScheduleModel {

    /**
     * success : 1
     * message : data found
     * data : [{"id":"1","university":"Gujarat Law University","subject":"Political Science","date":"12/2/2017","time":"1:00 PM","description":""},{"id":"2","university":"Boston University","subject":"Criminal Law","date":"15/2/2017","time":"1:00 PM","description":""}]
     */

    private int success;
    private String message;
    private List<DataBean> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * university : Gujarat Law University
         * subject : Political Science
         * date : 12/2/2017
         * time : 1:00 PM
         * description :
         */

        private String id;
        private String university;
        private String subject;
        private String date;
        private String time;
        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
