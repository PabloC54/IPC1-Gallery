package Estructuras;

import java.io.Serializable;

public class NodoCategoria implements Serializable{
    
    private String nombre;
    private ListaImagenes imagenes;
    private NodoCategoria siguiente;
    private NodoCategoria anterior;
    
    public NodoCategoria(String nombre,ListaImagenes imagenes){  //Constructor
        this.nombre=nombre;
        this.imagenes=imagenes;
        this.siguiente=this;
    }
        
    public String getNombre(){   //Regresa el valor 1 de un nodo
        return nombre;
    }
        
    public ListaImagenes getListaImagenes(){   //Regresa el valor 1 de un nodo
        return imagenes;
    }
        
    public void setNombre(String nombre){   //Regresa el valor 1 de un nodo
        this.nombre=nombre;
    }
        
    public void setListaImagenes(ListaImagenes imagenes){   //Regresa el valor 1 de un nodo
        this.imagenes=imagenes;
    }
    
    public void linkSiguiente(NodoCategoria n){   //Apunta al siguiente nodo
        siguiente=n;
    }    
        
    public void linkAnterior(NodoCategoria n){   //Apunta al anterior nodo
        anterior=n;
    }
    
    public NodoCategoria getSiguiente(){  //Obtiene el siguiente nodo
        return siguiente;
    }      
    
    public NodoCategoria getAnterior(){  //Obtiene el siguiente nodo
        return anterior;
    }    
}