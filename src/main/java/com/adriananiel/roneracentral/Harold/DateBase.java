package com.adriananiel.roneracentral.Harold;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DateBase {
    private String dateBasePath;
    private ArrayList<SuministroTable> suministroList;
    public DateBase(String dateBasePath){this.dateBasePath = dateBasePath;}

    public ArrayList<SuministroTable> startDateBase() throws ClassNotFoundException, IOException {
        File dateBase_File = new File(dateBasePath);

        if (dateBase_File.exists()){
            LeerObjetos ro = new LeerObjetos(dateBasePath);
            suministroList = (ArrayList<SuministroTable>) ro.Read();
        }else{
            suministroList = new ArrayList<>();
        }

        EscribirObjeto dateBase = new EscribirObjeto(dateBasePath);
        dateBase.Write(suministroList);
        return suministroList;
    }
}
