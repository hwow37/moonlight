package com.example.myapplication.localdatabase;

public class InfoDB {
    public int _id;
    public String name;
    public int check_word;

    public InfoDB(int _id , String name , int check_word){
        this._id = _id;
        this.name = name;
        this.check_word = check_word;
    }

    public String getName(){
        return name;
    }
}
