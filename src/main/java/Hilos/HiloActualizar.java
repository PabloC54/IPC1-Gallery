package Hilos;

import static Ventanas.Conversor.coladearchivos;
import static Ventanas.Conversor.piladearchivos;
import static Ventanas.Conversor.jList1;
import static Ventanas.Conversor.jProgressBar1;
import static Ventanas.Conversor.jTextArea1;
import static Ventanas.Conversor.listanombres;
import static Ventanas.Conversor.count;
import static Ventanas.Conversor.pos;
import static Ventanas.Conversor.progreso;
import static Ventanas.Conversor.total;
import static Ventanas.Conversor.barrahabilitada;
import static Ventanas.Conversor.vertical;
import static Ventanas.Conversor.vertical2;
import static Ventanas.Conversor.threadfinalizado;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloActualizar extends Thread {

    public int op;
    public int index;

    public HiloActualizar(int index, int op) {
        this.op = op;
        this.op = index;
    }

    @Override
    public void run() {
        /*
        try {
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloHandlerCola.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        System.out.println("----------ACTUALIZANDO");
        

        progreso += 100 / total;
        //Lista de procesos
        jList1.setSelectedIndex(listanombres.getSize() - 1);
        jList1.repaint();

        //Consola
        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
        jTextArea1.repaint();

        //Barra de progreso
        jProgressBar1.setValue(progreso);
        jProgressBar1.repaint();



        //Barras deslizables
        vertical.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (barrahabilitada = false) {
                    e.getAdjustable().setValue(e.getAdjustable().getMaximum());
                }
            }
        });

        vertical2.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (barrahabilitada = false) {
                    e.getAdjustable().setValue(e.getAdjustable().getMaximum());
                }
            }
        });



        // Actualizando la cola / pila
        if (op == 1) {
            //pila de archivos     
            pos = count + 1;
            listanombres.removeElement(pos + ") " + piladearchivos.getArchivo().getName());
            System.out.println("eliminado:     " + pos + ") " + piladearchivos.getArchivo().getName());
            piladearchivos.pop(index);
        }
        if (op == 2) {
            //cola de archivos
            listanombres.removeElement(pos + ") " + coladearchivos.getArchivo().getName());
            coladearchivos.pop(index);
            pos++;
        }
        threadfinalizado=true;
        
    }

}
