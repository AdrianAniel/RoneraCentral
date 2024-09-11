package com.adriananiel.roneracentral.Harold;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DateBase {
    private String dateBasePath;
    private ArrayList<SuministroTable> suministroList;

    public DateBase(String dateBasePath) {

        this.dateBasePath = dateBasePath;
    }
    // Database

    public ArrayList<SuministroTable> startDateBase() throws ClassNotFoundException, IOException {
        File dateBase_File = new File(dateBasePath);

        if (dateBase_File.exists()) {
            LeerObjetosSuministro ro = new LeerObjetosSuministro(dateBasePath);
            suministroList = (ArrayList<SuministroTable>) ro.Read();
        } else {
            suministroList = new ArrayList<>();
        }

        EscribirObjetoSuministro dateBase = new EscribirObjetoSuministro(dateBasePath);
        dateBase.Write(suministroList);
        return suministroList;
    }
}
