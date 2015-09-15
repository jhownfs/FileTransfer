
package File_Transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import javax.swing.JOptionPane;


public class Cliente extends ClienteModelo{
    
    public Cliente(String ipServidor, int porta){
        super(ipServidor, porta);
    }
    
    public void enviaArquivo(String path){
        File f = new File(path);
        try{
            Socket socket = new Socket(this.getIpServidor(), this.getPorta());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            JOptionPane.showMessageDialog(null, "Transferindo o arquivo: " + f.getName());
            System.out.println("Tranferindo o arquivo: " + f.getName());
            JOptionPane.showMessageDialog(null, "Aguarde um momento...");
            out.writeUTF(f.getName());
            out.writeLong(f.length());
            FileInputStream in = new FileInputStream(f);
            byte[] buf = new byte[4096];
            
            while(true){
                int len = in.read(buf);
                if(len == -1) break;
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            socket.close();
            System.out.println("Trenferência concluida!");
            JOptionPane.showMessageDialog(null, "Transferência concluida!");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
