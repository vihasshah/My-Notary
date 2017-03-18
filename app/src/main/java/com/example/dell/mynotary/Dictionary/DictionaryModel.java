package com.example.dell.mynotary.Dictionary;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 3/15/2017.
 */

public class DictionaryModel {

    /**
     * success : 1
     * message : data found
     * data : [{"id":"1","word":"A Deuce","description":"Slang for a drunk driving offence"},{"id":"2","word":"341 Notice","description":"A bankruptcy notice that s sent to debtors and creditors in a bankruptcy case which provides information of a meeting of the creditors."}]
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
         * word : A Deuce
         * description : Slang for a drunk driving offence
         */

        private String id;
        private String word;
        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
