package com.adriananiel.roneracentral.Corzo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadObject {
    // atributo de la clase para especificar la direccion en el disco del archivo base de datos
    private String filePath;
    // constructor de la clase
    public ReadObject(String path){
        this.filePath = path;
    }

    // metodo principal de la clase para leer el archivo que se paso como parametro al constructor de la clase,
    // el metodo devuelve un arrayList de usuarios, tiene manejo de excepciones
    public ArrayList<?> Read() throws IOException, ClassNotFoundException{

        // crea un stream de datos de entrada del archivo
        FileInputStream fis = new FileInputStream(filePath);

        // en el try intenta de crear un stream de datos de entrada convirtiendo los datos del stream del archivo a objetos
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {

            // crea una nueva lista de usuarios vacia
            ArrayList<QualityRonTable> ronList;

            // de los objetos cargados del archivo, los añade a la lista vacia que se creo anteriormente,
            ronList = (ArrayList<QualityRonTable>) ois.readObject();

            // retorna esta lista, que representa todos los objetos guardados en el archivo
            return ronList;
        }
    }

}
