package com.example.dell.mynotary.CaseDetails;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2/17/2017.
 */

public class CaseDetailsModel {

    /**
     * success : 1
     * message : data found
     * data : [{"id":"1","caseno":"1","title":"Murder","client_name":"Mr. Varma","details":"Murder of business man","court_name":"High court ","date":"01/03/2017"}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * caseno : 1
         * title : Murder
         * client_name : Mr. Varma
         * details : Murder of business man
         * court_name : High court
         * date : 01/03/2017
         */

        private String id;
        private String caseno;
        private String title;
        private String client_name;
        private String details;
        private String court_name;
        private String date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCaseno() {
            return caseno;
        }

        public void setCaseno(String caseno) {
            this.caseno = caseno;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getClient_name() {
            return client_name;
        }

        public void setClient_name(String client_name) {
            this.client_name = client_name;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getCourt_name() {
            return court_name;
        }

        public void setCourt_name(String court_name) {
            this.court_name = court_name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
