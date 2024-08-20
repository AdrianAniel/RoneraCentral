package com.adriananiel.roneracentral.Corzo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DB {
    private String dbPath;
    private ArrayList<QualityRonTable> ronList;
    public DB(String dbPath){
        this.dbPath = dbPath;
    }

    public ArrayList<QualityRonTable> startDB() throws ClassNotFoundException, IOException {
        File db_File = new File(dbPath);

        if (db_File.exists()){
            ReadObject ro = new ReadObject(dbPath);
            ronList = (ArrayList<QualityRonTable>) ro.Read();
        }else{
            ronList = new ArrayList<>();
        }

        WriteObject db = new WriteObject(dbPath);
        db.Write(ronList);
        return ronList;
    }


}
