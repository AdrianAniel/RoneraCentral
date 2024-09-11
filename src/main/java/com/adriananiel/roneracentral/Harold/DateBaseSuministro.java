package com.adriananiel.roneracentral.Harold;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DateBaseSuministro {
    private String dateBasePath;
    private ArrayList<SuministroTable> suministroList;
    public DateBaseSuministro(String dateBasePath){this.dateBasePath = dateBasePath;}

    public ArrayList<SuministroTable> startDateBase() throws ClassNotFoundException, IOException {
        File dateBase_File = new File(dateBasePath);

        if (dateBase_File.exists()){
            LeerObjetosSuministro ro = new LeerObjetosSuministro(dateBasePath);
            suministroList = (ArrayList<SuministroTable>) ro.Read();
        }else{
            suministroList = new ArrayList<>();
        }

        com.adriananiel.roneracentral.Harold.EscribirObjetoSuministro dateBase = new com.adriananiel.roneracentral.Harold.EscribirObjetoSuministro(dateBasePath);
        dateBase.Write(suministroList);
        return suministroList;
    }
}
