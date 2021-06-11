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
public class JPEGImageHandlerColors extends ImageHandler{

	/**
	*	Array of bytes that will allocate all header and data for original file
	**/
	protected byte[] redbytes,greenbytes,bluebytes,sepiabytes;
	/**
	*	File name that will be given to the copy of the original file
	**/
	protected String redname,greenname,bluename,sepianame;
        protected File imagen;
        protected BufferedImage img;
	

    public JPEGImageHandlerColors(File imagen) throws IOException{
        
        super(imagen.getName());  //
        this.imagen=imagen;
        this.redname = "Red-" + imagen.getName().substring(0, imagen.getName().length()-4) + ".jpg";
        this.greenname = "Green-" + imagen.getName().substring(0, imagen.getName().length()-4) + ".jpg";
        this.bluename = "Blue-" + imagen.getName().substring(0, imagen.getName().length()-4) + ".jpg";
        this.sepianame = "Sepia-" + imagen.getName().substring(0, imagen.getName().length()-4) + ".jpg";
    }

    @Override
    public void readFile() throws Exception {
        
        img = ImageIO.read(this.imagen);   
        redbytes=new byte[54+4*img.getWidth()*img.getHeight()];
        greenbytes=new byte[54+4*img.getWidth()*img.getHeight()];
        bluebytes=new byte[54+4*img.getWidth()*img.getHeight()];
        sepiabytes=new byte[54+4*img.getWidth()*img.getHeight()];
        System.out.println("Imagen leida: " + imagen.getAbsolutePath());
        
        procesarImagen();
    }

    @Override
    public void generateFiles() throws Exception {
        
        FileOutputStream outputR = new FileOutputStream(imagen.getParent()+"\\temp\\"+redname);
        outputR.write(redbytes);
        outputR.close();
        System.out.println("Imagen generada: " + redname);
        
        FileOutputStream outputG = new FileOutputStream(imagen.getParent()+"\\temp\\"+greenname);
        outputG.write(greenbytes);
        outputG.close();
        System.out.println("Imagen generada: " + greenname);
        
        FileOutputStream outputB = new FileOutputStream(imagen.getParent()+"\\temp\\"+bluename);
        outputB.write(bluebytes);
        outputB.close();
        System.out.println("Imagen generada: " + bluename);
        
        FileOutputStream outputS = new FileOutputStream(imagen.getParent()+"\\temp\\"+sepianame);
        outputS.write(sepiabytes);
        outputS.close();
        System.out.println("Imagen generada: " + sepianame);
    }    
    
    public void procesarImagen(){
        
        //Header         
        redbytes[0]=0x42;
        redbytes[1]=0x4D;
        greenbytes[0]=0x42;
        greenbytes[1]=0x4D;
        bluebytes[0]=0x42;
        bluebytes[1]=0x4D;
        sepiabytes[0]=0x42;
        sepiabytes[1]=0x4D;
        for(int i=2;i<54;i++){
            redbytes[i]=0x00;
            greenbytes[i]=0x00;
            bluebytes[i]=0x00;  
            sepiabytes[i]=0x00;  
        }                
        redbytes[10]=0x36;
        redbytes[14]=0x28;
        redbytes[26]=0x01;
        redbytes[28]=0x20;
        greenbytes[10]=0x36;
        greenbytes[14]=0x28;
        greenbytes[26]=0x01;
        greenbytes[28]=0x20;
        bluebytes[10]=0x36;
        bluebytes[14]=0x28;
        bluebytes[26]=0x01;
        bluebytes[28]=0x20;   
        sepiabytes[10]=0x36;
        sepiabytes[14]=0x28;
        sepiabytes[26]=0x01;
        sepiabytes[28]=0x20;        
        
        //Tamaños
        int ancho=img.getWidth();
        int alto=img.getHeight();        
        int tamaño=ancho*alto;   
        
        if(ancho>256)
            while(ancho>256){
                ancho-=256;
                redbytes[19]=(byte)(redbytes[19]+1);
                greenbytes[19]=(byte)(greenbytes[19]+1);
                bluebytes[19]=(byte)(bluebytes[19]+1);
                sepiabytes[19]=(byte)(sepiabytes[19]+1);
            }
        redbytes[18]=(byte) ancho;
        greenbytes[18]=(byte) ancho;
        bluebytes[18]=(byte) ancho;
        sepiabytes[18]=(byte) ancho;
        
        if(alto>256)
            while(alto>256){
                alto-=256;
                redbytes[23]=(byte)(redbytes[23]+1);
                greenbytes[23]=(byte)(greenbytes[23]+1);
                bluebytes[23]=(byte)(bluebytes[23]+1);
                sepiabytes[23]=(byte)(sepiabytes[23]+1);
            }
        redbytes[22]=(byte) alto;
        greenbytes[22]=(byte) alto;
        bluebytes[22]=(byte) alto;
        sepiabytes[22]=(byte) alto;  
                   
        //Imagen
        for (int i=0;i<img.getHeight();i++){
            for (int j=0;j<img.getWidth();j++){
                
                int rgb = img.getRGB(j,img.getHeight()-i-1);
                Color color = new Color(rgb, false);                                
                int pos=54+4*i*img.getWidth()+4*j;    
                
                redbytes[pos]=(byte) 0x00;          
                redbytes[pos+1]=(byte) 0x00;    
                redbytes[pos+2]=(byte)color.getRed();  
                redbytes[pos+3]=(byte) 0xff;    
                
                greenbytes[pos]=(byte) 0x00;              
                greenbytes[pos+1]=(byte)color.getGreen();                
                greenbytes[pos+2]=(byte) 0x00; 
                greenbytes[pos+3]=(byte) 0xff; 
                
                bluebytes[pos]=(byte)color.getBlue();                
                bluebytes[pos+1]=(byte) 0x00;      
                bluebytes[pos+2]=(byte) 0x00;       
                bluebytes[pos+3]=(byte) 0xff;   
                
                sepiabytes[pos]=(byte) (color.getRed()/5+color.getBlue()/5+color.getGreen()/5);                
                sepiabytes[pos+1]=(byte) (color.getRed()/3+color.getBlue()/3+color.getGreen()/3);     
                sepiabytes[pos+2]=(byte) (color.getRed()/3+color.getBlue()/3+color.getGreen()/3); 
                sepiabytes[pos+3]=(byte) 0xff;       
            }
        }
    }
}
