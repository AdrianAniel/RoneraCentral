package com.adriananiel.roneracentral.Corzo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriteObject {

    // atributo de la clase para especificar la direccion en el disco del archivo base de datos
    private String filePath;

    // constructor de la clase
    public WriteObject(String filePath){
        this.filePath = filePath;
    }

    // metodo principal de la clase el cual escribe en el archivo especificado la lista que le pases como parametro a este metodo
    public void Write(ArrayList<QualityRonTable> list){

        try {

            // crea una nueva instancia del archivo en la direccion que le especificaste
            File file = new File(filePath);
            // si el archivo no existe crea uno nuevo en esa ruta
            if (!file.exists()){
                file.createNewFile();
            }
            // se crean los stream de salida para primero convertir los objetos al output stream de un archivo y poder escribir en el archivo
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // escribe la lista en el archivo usando los streams
            oos.writeObject(list);
            System.out.println("Se escribio la lista");
            if (list.isEmpty()){
                System.out.println("lista desde write object vacia");
            } else {
                System.out.println(list.getLast().getProduct());
            }

            // cierra los streams
            oos.close();
            fos.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
