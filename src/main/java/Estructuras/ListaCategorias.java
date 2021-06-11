package Estructuras;

public class ListaCategorias extends EstructuraDeDatos{
    
    private NodoCategoria cabeza;
    private NodoCategoria cola;
    private int size;
    
    public ListaCategorias(){
        cabeza=null;
        cola=null;
        size=0;
    }
    
    public String getNombre (int index){
        int contador=0;
        NodoCategoria temporal=cabeza;
        while(contador<index){
            temporal=temporal.getSiguiente();
            contador++;
        }
        return temporal.getNombre();
    }
    
    public ListaImagenes getListaImagenes (int index){
        int contador=0;
        NodoCategoria temporal=cabeza;
        while(contador<index){
            temporal=temporal.getSiguiente();
            contador++;
        }
        return temporal.getListaImagenes();
    }
    
    public NodoCategoria getNodoCategoria(int index){
        int contador=0;
        NodoCategoria temporal=cabeza;
        while(contador<index){
            temporal=temporal.getSiguiente();
            contador++;
        }        
        return temporal;
    }
    
    public NodoCategoria getUltimoNodo(){
        int contador=0;
        NodoCategoria temporal=cabeza;
        while(contador<size-1){
            temporal=temporal.getSiguiente();
            contador++;
        }        
        return temporal;
    }
    
    public boolean isVacia(){
        if(cabeza==null)
            return true;
        else
            return false;
    }
    
    public void addPrimero(String nombre,ListaImagenes imagenes){
        if(cabeza==null){
            cabeza=new NodoCategoria(nombre,imagenes);
            cola=cabeza;
        }
        else{
            NodoCategoria temp=cabeza;
            NodoCategoria nuevo=new NodoCategoria(nombre,imagenes);
            nuevo.linkSiguiente(temp);
            temp.linkAnterior(nuevo);
            cabeza=nuevo;
        }
        size++;
    }
    
    public void addUltimo(String nombre,ListaImagenes imagenes){
        if (cola==null){
            cola=new NodoCategoria(nombre,imagenes);
            cabeza=cola;
        }
        else{
            NodoCategoria temp=cola;
            NodoCategoria nuevo=new NodoCategoria(nombre,imagenes);
            nuevo.linkAnterior(temp);
            temp.linkSiguiente(nuevo);
            cola=nuevo;
        }
        size++;
    }
    
    public void removePrimero(){
        cabeza=cabeza.getSiguiente();
        size--;
    }
    
    public void removeUltimo(){
        cola=cola.getAnterior();
        size--;
    }
    
    public void removeNodo(int index){
        if(index==0)
            cabeza=cabeza.getSiguiente();
        else{            
            if(index==size-1)                
                this.getNodoCategoria(size-2).linkSiguiente(null);
            else{
                int contador=0;
                NodoCategoria temporal=cabeza;
                while(contador<index-1){
                    temporal=temporal.getSiguiente();
                    contador++;
                }
                temporal.linkSiguiente(temporal.getSiguiente().getSiguiente());
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
