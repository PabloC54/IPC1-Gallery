package Hilos;

import Handlers.BMPtoJPEGImage;
import Handlers.JPEGHandler;
import Handlers.JPEGImageCopy;
import Handlers.JPEGImageHandlerBN;
import Handlers.JPEGImageHandlerColors;
import Handlers.JPEGImageHandlerRotator;
import Handlers.JPEGtoBMPImage;

import static Ventanas.Conversor.coladearchivos;
import static Ventanas.Conversor.jList1;
import static Ventanas.Conversor.jProgressBar1;
import static Ventanas.Conversor.jTextArea1;
import static Ventanas.Conversor.listanombres;
import static Ventanas.Conversor.progreso;
import static Ventanas.Conversor.total;
import static Ventanas.Conversor.pos;
import static Ventanas.Conversor.vertical;
import static Ventanas.Conversor.vertical2;
import static Ventanas.Conversor.barrahabilitada;
import static Ventanas.Conversor.bmp;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 *
 * @author pablo
 */
public class HiloProceso extends Thread {

    public int index;
    public int op;

    public HiloProceso(int index, int op) {
        this.index = index + 1;
        this.op = op;
    }

    @Override
    public void run() {

        JPEGHandler handler;

        switch (op) {

            case 1:

                try {
                    if (coladearchivos.getArchivo().getName().endsWith(".bmp") || coladearchivos.getArchivo().getName().endsWith(".BMP")) {
                        bmp = true;
                    }

                    if (bmp == true) {
                        handler = new JPEGHandler(new BMPtoJPEGImage(coladearchivos.getArchivo()));
                        jTextArea1.append("\n--> Convirtiendo " + coladearchivos.getArchivo().getName() + " a JPEG        Convertido!");
                        bmp = false;
                    } else {
                        handler = new JPEGHandler(new JPEGtoBMPImage(coladearchivos.getArchivo()));
                        jTextArea1.append("\n--> Convirtiendo " + coladearchivos.getArchivo().getName() + " a BMP        Convertido!");
                    }

                } catch (Exception ex) {
                    jTextArea1.append("\n--> Convirtiendo " + coladearchivos.getArchivo().getName() + "    --No se encontró el archivo--");
                }

                break;

            case 2:

                try {
                    if (coladearchivos.getArchivo().getName().endsWith(".jpg")) {
                        handler = new JPEGHandler(new JPEGImageCopy(coladearchivos.getArchivo()));
                        jTextArea1.append("\n--> Copiando " + coladearchivos.getArchivo().getName() + "      Copiado!");
                    } else {
                        jTextArea1.append("\n--> Copiando " + coladearchivos.getArchivo().getName() + "    --No es un archivo JPEG--");
                    }

                } catch (Exception ex) {
                    jTextArea1.append("\n--> Copiando " + coladearchivos.getArchivo().getName() + "    --No se encontró el archivo--");
                }

                break;

            case 3:

                try {
                    handler = new JPEGHandler(new JPEGImageHandlerColors(coladearchivos.getArchivo()));
                    jTextArea1.append("\n--> Generando las imágenes: rojo, azul, verde y sepia de " + coladearchivos.getArchivo().getName() + "      Generadas!");
                } catch (Exception ex) {
                    jTextArea1.append("\n--> Generando las imágenes: rojo, azul, verde y sepia de " + coladearchivos.getArchivo().getName() + "    --No se encontró el archivo--");
                }
                break;

            case 4:

                try {
                    handler = new JPEGHandler(new JPEGImageHandlerRotator(coladearchivos.getArchivo()));
                    jTextArea1.append("\n--> Generando las imágenes: espejo-h y espejo-v de " + coladearchivos.getArchivo().getName() + "      Generadas!");
                } catch (Exception ex) {
                    jTextArea1.append("\n--> Generando las imágenes: espejo-h y espejo-v de " + coladearchivos.getArchivo().getName() + "    --No se encontró el archivo--");
                }

                break;

            case 5:

                try {
                    handler = new JPEGHandler(new JPEGImageHandlerBN(coladearchivos.getArchivo()));
                    jTextArea1.append("\n--> Generando una imagen blanco y negro de " + coladearchivos.getArchivo().getName()+"      Generada!");
                } catch (Exception ex) {
                    jTextArea1.append("\n--> Generando una imagen blanco y negro de " + coladearchivos.getArchivo().getName()+"    --No se encontró el archivo--");
                }
                
                break;
        }

        actualizar();

    }

    public void actualizar() {

        progreso += 100 / total;
        //Lista de procesos
        jList1.setSelectedIndex(listaprocesos.getSize() - 1);
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

        //cola de archivos
        listaprocesos.removeElement(pos + ") " + coladearchivos.getArchivo().getName());
        coladearchivos.pop();
        pos++;
    }

}
