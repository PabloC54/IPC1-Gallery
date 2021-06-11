package Estructuras;

import java.io.File;
import java.io.Serializable;

public class NodoArchivo implements Serializable {
    
    private File archivo;
    private NodoArchivo siguiente;
    
    public NodoArchivo(File archivo){  //Constructor
        this.archivo=archivo;
        this.siguiente=null;
    }
        
    public File getArchivo(){   //Regresa el valor de un NodoArchivo
        return archivo;
    }
            
    public void linkSiguiente(NodoArchivo n){   //Apunta al siguiente NodoArchivo
        siguiente=n;
    }
    
    public NodoArchivo getSiguiente(){  //Obtiene el siguiente NodoArchivo
        return siguiente;
    }        
}