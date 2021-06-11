package Hilos;

import Handlers.BMPtoJPEGImage;
import Handlers.JPEGHandler;
import Handlers.JPEGImageCopy;
import Handlers.JPEGImageHandlerBN;
import Handlers.JPEGImageHandlerColors;
import Handlers.JPEGImageHandlerRotator;
import Handlers.JPEGtoBMPImage;

import static Ventanas.Conversor.piladearchivos;
import static Ventanas.Conversor.jTextArea1;
import static Ventanas.Conversor.bmp;
import static Ventanas.Conversor.threadfinalizado;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class HiloHandlerPila extends Thread {

    public int index;
    public int op;
    public int estrujones=0;

    public HiloHandlerPila(int index, int op) {
        this.index = index;
        this.op = op;
    }

    @Override
    public void run() { System.out.println("---------CONVERSION");

    
        while(threadfinalizado=false){
            estrujones++;
        }
        threadfinalizado=false;
    
        
    
        JPEGHandler handler;

        switch (op) {

            case 1:

                try {
                    if (piladearchivos.getArchivo().getName().endsWith(".bmp") || piladearchivos.getArchivo().getName().endsWith(".BMP")) {
                        bmp = true;
                    }

                    if (bmp == true) {
                        handler = new JPEGHandler(new BMPtoJPEGImage(piladearchivos.getArchivo()));
                        //jTextArea1.append("\n--> Convirtiendo " + piladearchivos.getArchivo().getName() + " a JPEG        Convertido!");
                        bmp = false;
                    } else {
                        handler = new JPEGHandler(new JPEGtoBMPImage(piladearchivos.getArchivo()));
                        //jTextArea1.append("\n--> Convirtiendo " + piladearchivos.getArchivo().getName() + " a BMP        Convertido!");
                    }

                } catch (Exception ex) {
                    jTextArea1.append("\n--> Convirtiendo " + piladearchivos.getArchivo().getName() + "    --No se encontró el archivo--");
                }

                break;

            case 2:

                try {
                    if (piladearchivos.getArchivo().getName().endsWith(".jpg")) {
                        handler = new JPEGHandler(new JPEGImageCopy(piladearchivos.getArchivo()));
                        jTextArea1.append("\n--> Copiando " + piladearchivos.getArchivo().getName() + "      Copiado!");
                    } else {
                        jTextArea1.append("\n--> Copiando " + piladearchivos.getArchivo().getName() + "    --No es un archivo JPEG--");
                    }

                } catch (Exception ex) {
                    jTextArea1.append("\n--> Copiando " + piladearchivos.getArchivo().getName() + "    --No se encontró el archivo--");
                }

                break;

            case 3:

                try {
                    handler = new JPEGHandler(new JPEGImageHandlerColors(piladearchivos.getArchivo()));
                    jTextArea1.append("\n--> Generando las imágenes: rojo, azul, verde y sepia de " + piladearchivos.getArchivo().getName() + "      Generadas!");
                } catch (Exception ex) {
                    jTextArea1.append("\n--> Generando las imágenes: rojo, azul, verde y sepia de " + piladearchivos.getArchivo().getName() + "    --No se encontró el archivo--");
                }
                break;

            case 4:

                try {
                    handler = new JPEGHandler(new JPEGImageHandlerRotator(piladearchivos.getArchivo()));
                    jTextArea1.append("\n--> Generando las imágenes: espejo-h y espejo-v de " + piladearchivos.getArchivo().getName() + "      Generadas!");
                } catch (Exception ex) {
                    jTextArea1.append("\n--> Generando las imágenes: espejo-h y espejo-v de " + piladearchivos.getArchivo().getName() + "    --No se encontró el archivo--");
                }

                break;

            case 5:

                try {
                    handler = new JPEGHandler(new JPEGImageHandlerBN(piladearchivos.getArchivo()));
                    jTextArea1.append("\n--> Generando una imagen blanco y negro de " + piladearchivos.getArchivo().getName() + "      Generada!");
                } catch (Exception ex) {
                    jTextArea1.append("\n--> Generando una imagen blanco y negro de " + piladearchivos.getArchivo().getName() + "    --No se encontró el archivo--");
                }

                break;
        }
        
        HiloActualizar actualizar=new HiloActualizar(index,op);
        actualizar.start();
                
    }

}
