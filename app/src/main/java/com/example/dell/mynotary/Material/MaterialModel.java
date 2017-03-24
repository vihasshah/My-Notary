package com.example.dell.mynotary.Material;

import java.util.List;

/**
 * Created by Vihas on 24-03-2017.
 */

public class MaterialModel {


    /**
     * success : 1
     * message : File Found
     * data : [{"firstname":"Manisha","lastname":"Tahelyani","file":"Test1"},{"firstname":"Manisha","lastname":"Tahelyani","file":"Test2"}]
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

    public static class DataBean {
        /**
         * firstname : Manisha
         * lastname : Tahelyani
         * file : Test1
         */

        private String firstname;
        private String lastname;
        private String file;

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }
    }
}
