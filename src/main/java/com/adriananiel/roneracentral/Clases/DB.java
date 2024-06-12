package com.adriananiel.roneracentral.Clases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DB {

    private final String dbPath;

    public  DB(String dbPath){
        this.dbPath = dbPath;
    }

    public void startDB() throws ClassNotFoundException, IOException{

        File db_File = new File(dbPath);
        

        ArrayList<Usuario> usersList;

        if (db_File.exists()){
            ReadFile ro = new ReadFile(dbPath);
            usersList = ro.Read();
        }else{
            usersList = new ArrayList<>();
        }
        

        WriteFile db = new WriteFile(dbPath);
        db.Write(usersList);
    }
}
