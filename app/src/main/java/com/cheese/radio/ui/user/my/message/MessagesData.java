package com.cheese.radio.ui.user.my.message;

import com.cheese.radio.ui.user.my.message.entity.DetailsEntity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 29283 on 2018/3/31.
 */

public class MessagesData {

        private ArrayList<DetailsEntity> system;
        private ArrayList<DetailsEntity> book;
        private ArrayList<DetailsEntity> user;
        @SerializedName("class")
        private ArrayList<DetailsEntity> classX;

        public ArrayList<DetailsEntity> getSystem() {
            return system;
        }

        public void setSystem(ArrayList<DetailsEntity> system) {
            this.system = system;
        }

        public ArrayList<DetailsEntity> getBook() {
            return book;
        }

        public void setBook(ArrayList<DetailsEntity> book) {
            this.book = book;
        }

        public ArrayList<DetailsEntity> getUser() {
            return user;
        }

        public void setUser(ArrayList<DetailsEntity> user) {
            this.user = user;
        }

        public ArrayList<DetailsEntity> getClassX() {
            return classX;
        }

        public void setClassX(ArrayList<DetailsEntity> classX) {
            this.classX = classX;
        }



}
