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
public class JPEGImageHandlerRotator extends ImageHandler {

  
	/**
	*	Array of bytes that will allocate all header and data for original file
	**/
	protected byte[] hrotationbytes,vrotationbytes;
	/**
	*	File name that will be given to the copy of the original file
	**/
	protected String hrotationname,vrotationname;
        protected File imagen;
        protected BufferedImage img;
	

    public JPEGImageHandlerRotator(File imagen) throws IOException{
        
        super(imagen.getName());
        this.imagen=imagen;
        this.hrotationname = "Hrotation-" + imagen.getName().substring(0, imagen.getName().length()-4) + ".jpg";
        this.vrotationname = "Vrotation-" + imagen.getName().substring(0, imagen.getName().length()-4) + ".jpg";
    }

    @Override
    public void readFile() throws Exception {
        
        img = ImageIO.read(this.imagen); 
        hrotationbytes=new byte[54+4*img.getWidth()*img.getHeight()];
        vrotationbytes=new byte[54+4*img.getWidth()*img.getHeight()];
        System.out.println("Imagen leida: " + imagen.getAbsolutePath());
        
        procesarImagen();                      
    }

    @Override
    public void generateFiles() throws Exception {
        
        FileOutputStream outputH = new FileOutputStream(imagen.getParent()+"\\temp\\"+hrotationname);
        outputH.write(hrotationbytes);
        outputH.close();
        System.out.println("Imagen generada: " + hrotationname);
        
        FileOutputStream outputV = new FileOutputStream(imagen.getParent()+"\\temp\\"+vrotationname);
        outputV.write(vrotationbytes);
        outputV.close();
        System.out.println("Imagen generada: " + vrotationname);
    }
    
    public void procesarImagen(){
        
        //Header         
        hrotationbytes[0]=0x42;
        hrotationbytes[1]=0x4D;
        vrotationbytes[0]=0x42;
        vrotationbytes[1]=0x4D;
        for(int i=2;i<54;i++){
            hrotationbytes[i]=0x00;
            vrotationbytes[i]=0x00;
        }                
        hrotationbytes[10]=0x36;
        hrotationbytes[14]=0x28;
        hrotationbytes[26]=0x01;
        hrotationbytes[28]=0x20;
        vrotationbytes[10]=0x36;
        vrotationbytes[14]=0x28;
        vrotationbytes[26]=0x01;
        vrotationbytes[28]=0x20;     
        
        //Tamaños
        int ancho=img.getWidth();
        int alto=img.getHeight();  
        
        if(ancho>256)
            while(ancho>256){
                ancho-=256;
                hrotationbytes[19]=(byte)(hrotationbytes[19]+1);
                vrotationbytes[19]=(byte)(vrotationbytes[19]+1);
            }
        hrotationbytes[18]=(byte) ancho;
        vrotationbytes[18]=(byte) ancho;
        
        if(alto>256)
            while(alto>256){
                alto-=256;
                hrotationbytes[23]=(byte)(hrotationbytes[23]+1);
                vrotationbytes[23]=(byte)(vrotationbytes[23]+1);
            }
        hrotationbytes[22]=(byte) alto;
        vrotationbytes[22]=(byte) alto; 
                   
        //Imagen
        for (int i=0;i<img.getHeight();i++){
            for (int j=0;j<img.getWidth();j++){
                
                int rgbh = img.getRGB(img.getWidth()-j-1,img.getHeight()-i-1);
                Color colorh = new Color(rgbh, false);  
                
                int rgbv = img.getRGB(j,i);
                Color colorv = new Color(rgbv, false);  
                
                int pos=54+4*i*img.getWidth()+4*j;   
                
                
                hrotationbytes[pos]=(byte) colorh.getBlue();
                hrotationbytes[pos+1]=(byte) colorh.getGreen();
                hrotationbytes[pos+2]=(byte) colorh.getRed();  
                hrotationbytes[pos+3]=(byte) 0xff;    
                
                vrotationbytes[pos]=(byte) colorv.getBlue();
                vrotationbytes[pos+1]=(byte) colorv.getGreen();                
                vrotationbytes[pos+2]=(byte) colorv.getRed();
                vrotationbytes[pos+3]=(byte) 0xff;     
            }
        }
    }
}
