package Estructuras;

import java.io.File;
import java.io.Serializable;

public class NodoImagen implements Serializable{
    
    private File imagen;
    private NodoImagen siguiente;
    private NodoImagen anterior;
    
    public NodoImagen(File imagen){  //Constructor
        this.imagen=imagen;
        this.siguiente=this;
        this.anterior=this;
    }
        
    public File getImagen(){   //Regresa el valor de un nodo
        return imagen;
    }
        
    public void setImagen(File imagen){   //Regresa el valor 1 de un nodo
        this.imagen=imagen;
    }
    
    public void linkSiguiente(NodoImagen n){   //Apunta al siguiente nodo
        siguiente=n;
    }
    
    public void linkAnterior(NodoImagen n){   //Apunta al siguiente nodo
        anterior=n;
    }
    
    public NodoImagen getSiguiente(){  //Obtiene el siguiente nodo
        return siguiente;
    }        
    
    public NodoImagen getAnterior(){  //Obtiene el anterior nodo
        return anterior;
    }        
}