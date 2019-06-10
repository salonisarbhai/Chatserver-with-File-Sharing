/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbot;

/**
 *
 * @author Saloni
 */
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
public class sendfile {
    
    Socket socket;
    File file;
    
    public sendfile(File f) throws FileNotFoundException{
        file = f;
        try{
            socket = new Socket("127.0.0.1",1201);
        }catch(IOException ex){
            Logger.getLogger(sendfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
            public void upload(){
            try{
                File transferFile = new File(file.getAbsolutePath());
                byte[]bytearray = new byte[(int)transferFile.length()];
                FileInputStream fin = new FileInputStream(transferFile);
                BufferedInputStream bin = new BufferedInputStream(fin);
                bin.read(bytearray,0,bytearray.length);
                OutputStream os = socket.getOutputStream();
                os.write(bytearray,0,bytearray.length);
                os.flush();
                socket.close();
                
            }catch(IOException ex){
                Logger.getLogger(sendfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }

