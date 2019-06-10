/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbot;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saloni
 */
public class Filereciever{
    Socket socket;
    File file;
    FileOutputStream fos;
    int filesize = 2022386;
    int bytesRead;
    int currentTot = 0;
    
    public Filereciever(File f){
        try{
            socket = new Socket("127.0.0.1",1201);
            file = f;
        }catch(IOException ex){
            
        }
    }
    
    public void download()
    {
     try{
         byte[]bytearray = new byte[filesize];
         InputStream is = socket.getInputStream();
         FileOutputStream f = new FileOutputStream("C:\\projects\\javafile.txt");
           try (BufferedOutputStream bos = new BufferedOutputStream(f)) {
                bytesRead = is.read(bytearray,0,bytearray.length);
                currentTot = bytesRead;
                do {
                    bytesRead = is.read(bytearray, currentTot, (bytearray.length-currentTot));
                    if(bytesRead >= 0) currentTot += bytesRead;
                } while(bytesRead > -1);
                bos.write(bytearray, 0 , currentTot);
                bos.flush();
            }
            socket.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Filereciever.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Filereciever.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

 }
         
