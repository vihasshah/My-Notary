package com.example.dell.mynotary.Login;

import java.util.List;

/**
 * Created by Vihas on 17-03-2017.
 */

public class LoginModel {

    /**
     * success : 1
     * message : Login successful
     * data : [{"id":"3","email":"manishat@gmail.com","password":"1234","username":"manisha","firstname":"manisha","phone":"2525","gender":"","lastname":"","dob":"","role":"1"}]
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
         * id : 3
         * email : manishat@gmail.com
         * password : 1234
         * username : manisha
         * firstname : manisha
         * phone : 2525
         * gender :
         * lastname :
         * dob :
         * role : 1
         */

        private String id;
        private String email;
        private String password;
        private String username;
        private String firstname;
        private String phone;
        private String gender;
        private String lastname;
        private String dob;
        private String role;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
