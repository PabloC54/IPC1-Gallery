package Estructuras;

import java.io.File;

public class ColadeProcesamiento extends EstructuraDeDatos{
    
    private NodoArchivo nodoactual;
    private int size;
    
    public ColadeProcesamiento(){
        nodoactual=null;
        size=0;
    }
    public NodoArchivo getNodo(int index){
        int contador=0;
        NodoArchivo temporal=nodoactual;
        while(contador<index){
            temporal=temporal.getSiguiente();
            contador++;
        }        
        return temporal;
    }
        
    public File getArchivo(){
       return nodoactual.getArchivo();        
    }
            
    public File getArchivo(int index){
        int contador=0;
        NodoArchivo temporal=nodoactual;
        while(contador<index){
            temporal=temporal.getSiguiente();
            contador++;
        }        
        return temporal.getArchivo();
    }
    
    public boolean isVacia(){
        if(nodoactual==null)
            return true;
        else
            return false;
    }
    
    public void push(File archivo){
        if(nodoactual==null)
            nodoactual=new NodoArchivo(archivo);        
        else{
            NodoArchivo nuevo=new NodoArchivo(archivo);
            NodoArchivo temp=nodoactual;
            
            for(int i=0;i<size-1;i++)
                temp=temp.getSiguiente();    
            
            temp.linkSiguiente(nuevo);
        }
        size++;
    }
    
    public void pop(){
        nodoactual=nodoactual.getSiguiente();
        size--;        
    }
    
    public void pop(int index){
        if(index==0)
            nodoactual=nodoactual.getSiguiente();
        else{            
            if(index==size-1)                
                this.getNodo(size-2).linkSiguiente(null);
            else{
                int contador=0;
                NodoArchivo temporal=nodoactual;
                while(contador<index-1){
                    temporal=temporal.getSiguiente();
                    contador++;
                }
                temporal.linkSiguiente(temporal.getSiguiente().getSiguiente());
            }
        }
        size--;
    }
        
    public int getSize(){
        return size;
    }    
}
