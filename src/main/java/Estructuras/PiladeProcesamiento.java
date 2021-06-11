package Estructuras;

import java.io.File;

public class PiladeProcesamiento extends EstructuraDeDatos {

    private NodoArchivo nodoactual;
    private int size;

    public PiladeProcesamiento() {
        nodoactual = null;
        size = 0;
    }

    public File getArchivo() {
        return nodoactual.getArchivo();
    }

    public File getArchivo(int index) {
        int contador = 0;
        NodoArchivo temporal = nodoactual;
        while (contador < size - index - 1) {
            temporal = temporal.getSiguiente();
            contador++;
        }
        return temporal.getArchivo();
    }

    public NodoArchivo getNodo(int index) {
        int contador = 0;
        NodoArchivo temporal = nodoactual;
        while (contador < size - index - 1) {
            temporal = temporal.getSiguiente();
            contador++;
        }
        return temporal;
    }

    public boolean isVacia() {
        if (nodoactual == null) {
            return true;
        } else {
            return false;
        }
    }

    public void push(File archivo) {
        if (nodoactual == null) {
            nodoactual = new NodoArchivo(archivo);
        } else {
            NodoArchivo nuevo = new NodoArchivo(archivo);
            NodoArchivo temp = nodoactual;
            nuevo.linkSiguiente(temp);
            nodoactual = nuevo;
        }
        size++;
        System.out.println("||||||||||||| tamaño: " + size + "     añadido: " + nodoactual.getArchivo().getName());
    }

    public void pop() {
        nodoactual = nodoactual.getSiguiente();
        size--;
    }
    
    public void pop(int index) {
        if (index == 0) {
            this.getNodo(size - 2).linkSiguiente(null);
        } else {
            if (index == size - 1) {
                nodoactual = nodoactual.getSiguiente();
            } else {
                int contador = 0;
                NodoArchivo temporal = nodoactual;
                while (contador < size - index - 2) {
                    temporal = temporal.getSiguiente();
                    contador++;
                }
                temporal.linkSiguiente(temporal.getSiguiente().getSiguiente());
            }
        }
        size--;
    }

    public int getSize() {
        return size;
    }
}
