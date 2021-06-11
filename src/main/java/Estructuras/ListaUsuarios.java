package Estructuras;

public class ListaUsuarios extends EstructuraDeDatos{
    
    private NodoUsuario cabeza;
    private int size;
    
    public ListaUsuarios(){
        cabeza=null;
        size=0;
    }
    
    public String getNombre (int index){
        int contador=0;
        NodoUsuario temporal=cabeza;
        while(contador<index){
            temporal=temporal.getSiguiente();
            contador++;
        }
        return temporal.getNombre();
    }
    
    public ListaCategorias getListaCategorias (int index){
        int contador=0;
        NodoUsuario temporal=cabeza;
        while(contador<index){
            temporal=temporal.getSiguiente();
            contador++;
        }
        return temporal.getListaCategorias();
    }
    
    public NodoUsuario getNodoCategoria(int index){
        int contador=0;
        NodoUsuario temporal=cabeza;
        while(contador<index){
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
    
    public void addPrimero(String nombre, ListaCategorias categorias){
        if(cabeza==null)
            cabeza=new NodoUsuario(nombre,categorias);        
        else{
            NodoUsuario temp=cabeza;
            NodoUsuario nuevo=new NodoUsuario(nombre,categorias);
            nuevo.linkSiguiente(temp);
            cabeza=nuevo;
        }
        size++;
    }
    
    public void addUltimo(String nombre, ListaCategorias categorias){
        if(cabeza==null)
            cabeza=new NodoUsuario(nombre,categorias);
        else{        
            NodoUsuario nuevo=new NodoUsuario(nombre,categorias);
            this.getNodoCategoria(size-1).linkSiguiente(nuevo);
        }
        size++;
    }
    
    public void removePrimero(){
        cabeza=cabeza.getSiguiente();
        size--;
    }
    
    public void removeUltimo(){
        this.getNodoCategoria(size-2).linkSiguiente(null);
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
                NodoUsuario temporal=cabeza;
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
        size=0;
    }
    
    public int getSize(){
        return size;
    }    
}
