/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author pablo
 */
public class JPEGImageHandlerBN extends ImageHandler{

	/**
	*	Array of bytes that will allocate all header and data for original file
	**/
	protected byte[] bnbytes;
	/**
	*	File name that will be given to the copy of the original file
	**/
	protected String bnname;
        protected File imagen;
        protected BufferedImage img;
	

    public JPEGImageHandlerBN(File imagen) throws IOException{
        
        super(imagen.getName());  //
        this.imagen=imagen;
        this.bnname = "Bn-" + imagen.getName().substring(0, imagen.getName().length()-4) + ".jpg";
    }

    @Override    
    public void readFile() throws Exception {        
        
        img = ImageIO.read(imagen); 
        bnbytes=new byte[54+4*img.getWidth()*img.getHeight()];        
        System.out.println("Imagen leida: " + imagen.getAbsolutePath());     
        
        procesarImagen();
    }

    @Override
    public void generateFiles() throws Exception {
        
        FileOutputStream output = new FileOutputStream(imagen.getParent()+"\\temp\\"+bnname);
        output.write(bnbytes);
        output.close();
        System.out.println("Imagen generada: " + bnname);
    }    
    
    public void procesarImagen(){
        
        //Header 
        bnbytes[0]=0x42;
        bnbytes[1]=0x4D;
        
        for(int i=2;i<54;i++)
            bnbytes[i]=0x00;
              
        bnbytes[10]=0x36;
        bnbytes[14]=0x28;
        bnbytes[26]=0x01;
        bnbytes[28]=0x20; 
        
        //Tamaños        
        int ancho=img.getWidth();
        int alto=img.getHeight();   
        
        if(ancho>256)
            while(ancho>256){
                ancho-=256;
                bnbytes[19]=(byte)(bnbytes[19]+1);
            }
        bnbytes[18]=(byte) ancho;
        
        if(alto>256)
            while(alto>256){
                alto-=256;
                bnbytes[23]=(byte)(bnbytes[23]+1);
            }
        bnbytes[22]=(byte) alto;           
        
                        
        //Imagen
        for (int i=0;i<img.getHeight();i++){
            for (int j=0;j<img.getWidth();j++){                
                int rgb = img.getRGB(j,img.getHeight()-i-1);
                Color color = new Color(rgb, false);
                                
                int pos=54+4*i*img.getWidth()+4*j;
                
                bnbytes[pos]=(byte) (color.getRed()/3+color.getBlue()/3+color.getGreen()/3);  
                bnbytes[pos+1]=(byte) (color.getRed()/3+color.getBlue()/3+color.getGreen()/3);  
                bnbytes[pos+2]=(byte) (color.getRed()/3+color.getBlue()/3+color.getGreen()/3);  
                bnbytes[pos+3]=(byte) 0xff;       
            }
        }
    }
}
