/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author pablo
 */
public class BMPtoJPEGImage extends ImageHandler{
    
	/**
	*	Array of bytes that will allocate all header and data for original file
	**/
	protected byte[] filebytes;
	/**
	*	File name that will be given to the copy of the original file
	**/
	protected String copyname;
        protected File imagen;
	

    public BMPtoJPEGImage(File imagen) {
        super(imagen.getName());  //
        this.imagen=imagen;
        this.copyname = "converted-" + imagen.getName().substring(0, imagen.getName().length()-4) + ".jpg";
    }

    @Override
    public void readFile() throws Exception {
        
        FileInputStream input = new FileInputStream(imagen.getAbsolutePath());
        filebytes = new byte[input.available()];
        input.read(filebytes);
        input.close();
        System.out.println("Imagen leida: " + imagen.getAbsolutePath());
    }

    @Override
    public void generateFiles() throws Exception {
        
        FileOutputStream output = new FileOutputStream(imagen.getParent()+"\\temp\\"+copyname);
        output.write(filebytes);
        output.close();
        System.out.println("Imagen generada: " + copyname);
    }
    
}
