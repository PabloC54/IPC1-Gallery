package Estructuras;

import java.io.Serializable;

public class NodoUsuario implements Serializable{
    
    private String nombre;
    private ListaCategorias categorias;
    private NodoUsuario siguiente;
    
    public NodoUsuario(String nombre, ListaCategorias categorias){  //Constructor
        this.nombre=nombre;
        this.categorias=categorias;
        this.siguiente=null;
    }
        
    public String getNombre(){   //Regresa el valor 1 de un nodo
        return nombre;
    }
        
    public ListaCategorias getListaCategorias(){   //Regresa el valor 2 de un nodo
        return categorias;
    }
        
    public void setNombre(String nombre){   //Regresa el valor 1 de un nodo
        this.nombre=nombre;
    }
        
    public void setListaCategorias(ListaCategorias categorias){   //Regresa el valor 2 de un nodo
        this.categorias=categorias;
    }
    
    public void linkSiguiente(NodoUsuario n){   //Apunta al siguiente nodo
        siguiente=n;
    }    
    
    public NodoUsuario getSiguiente(){  //Obtiene el siguiente nodo
        return siguiente;
    }   
}