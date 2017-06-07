package com.jdasin.www.simplecrud.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by az on 06-06-17.
 */
@Database(name = PeopleDB.DB_NAME, version = PeopleDB.VERSION)
public class PeopleDB {
    public static final int VERSION = 1;
    public static final String DB_NAME = "People";


}
