package com.example.myapplication.localdatabase;


import android.provider.BaseColumns;

//DataBase Table
public final class LocalDB {

    public static final class CreateDB implements BaseColumns {
        public static final String NAME = "name";
        public static final String CHECK_WORD = "check_word";
        public static final String _TABLENAME = "address";
        public static final String _CREATE =
                "create table "+_TABLENAME+"("
                        +_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                        +NAME+" TEXT, "
                        +CHECK_WORD+" INTEGER DEFAULT 0);";
    }
}