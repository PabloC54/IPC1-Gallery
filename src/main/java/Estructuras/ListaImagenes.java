package Estructuras;

import java.io.File;

public class ListaImagenes extends EstructuraDeDatos{
    
    private NodoImagen cabeza;
    private NodoImagen cola;
    private int size;
    
    public ListaImagenes(){
        cabeza=null;
        cola=null;
        size=0;
    }
    
    public File getImagen (int index){
        int contador=0;
        NodoImagen temporal=cola;
        while(contador<index){
            temporal=temporal.getSiguiente();
            contador++;
        }
        return temporal.getImagen();
    }
    
    public NodoImagen getNodoImagen(int index){
        int contador=0;
        NodoImagen temporal=cola;
        while(contador<index){
            temporal=temporal.getSiguiente();
            contador++;
        }        
        return temporal;
    }
    
    public NodoImagen getUltimoNodo(){   
        return cabeza;
    }
    
    public boolean isVacia(){
        if(cola==null)
            return true;
        else
            return false;
    }
    
    public void addNodo(File imagen){
        NodoImagen nuevo=new NodoImagen(imagen);
        if(cola!=null){
            cabeza.linkSiguiente(nuevo);
            nuevo.linkSiguiente(cola);
            nuevo.linkAnterior(cabeza);
            cabeza=nuevo;
            cola.linkAnterior(cabeza);
        }
        else{
            cola=nuevo;
            cola.linkSiguiente(cola);
            cabeza=nuevo;
            cola.linkAnterior(cabeza);
        }
        size++;
    }
    
    public void removePrimero(){
        cola=cola.getSiguiente();        
        cola.linkAnterior(cabeza);
        cabeza.linkSiguiente(cola);
        size--;
    }
    
    public void removeUltimo(){
        cabeza=cabeza.getAnterior();
        cola.linkAnterior(cabeza);
        cabeza.linkSiguiente(cola);
        size--;
    }
    
    public void removeNodo(int index){  
        int ultimo=size-1;
        
        if(index==0 || index%size==0){
            cola=cola.getSiguiente(); 
            cola.linkAnterior(cabeza);
            cabeza.linkSiguiente(cola);
        }
        else{            
            if(index==size-1 || index%ultimo==0){
                cabeza=cabeza.getAnterior();  
                cola.linkAnterior(cabeza);
                cabeza.linkSiguiente(cola);
            }
            else{                
                if(index==1){
                    cola.linkSiguiente(cola.getSiguiente().getSiguiente());
                    cola.getSiguiente().linkAnterior(cola);
                    cola.linkAnterior(cabeza);
                    cabeza.linkSiguiente(cola);
                }
                else{
                    if(index==size-2){
                        cabeza.linkAnterior(cabeza.getAnterior().getAnterior());
                        cabeza.getAnterior().linkSiguiente(cabeza);
                        cola.linkAnterior(cabeza);
                        cabeza.linkSiguiente(cola);
                    }
                    else{                
                        int contador=0;
                        NodoImagen temporal=cola;
                        while(contador<index-1){
                            temporal=temporal.getSiguiente();
                            contador++;
                        }                                
                        temporal.linkSiguiente(temporal.getSiguiente().getSiguiente());
                        temporal.getSiguiente().getSiguiente().linkAnterior(temporal);                        
                    }
                }
            }
        }
        size--;
    }
    
    public void removeTodo(){
        cabeza=null;
        cola=null;
        size=0;
    }
    
    public int getSize(){
        return size;
    }    
}
