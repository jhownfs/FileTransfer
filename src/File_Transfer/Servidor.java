
package File_Transfer;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;


public class Servidor implements Runnable{
    ServerSocket serv;
    private int porta;
   
    public Servidor(int porta_) throws Exception{
        this.porta = porta_;
        serv = new ServerSocket(porta_);
        new Thread(this).start();
        JOptionPane.showMessageDialog(null, "Servidor aguardando conexão na porta: " + porta_);
        System.out.println("Servidor aguardando conexão na porta: " + porta_);
    }
    public int getPorta(){
        return this.porta;
    }
    public void setPorta(int porta_){
        this.porta = porta_;
    }
    
    public void run(){
        try{
            while(true){
                
                new TrataCliente(serv.accept()).start();
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    class TrataCliente extends Thread{
        private Socket client;
        
        public TrataCliente(Socket s){
            System.out.println("...");
            client = s;
            this.rum();
        }
        
        public void rum(){
            try{
                System.out.println("Conectado ao Cliente...");
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                String fileName = in.readUTF();
                long size = in.readLong();
                System.out.println("Processando arquivo: " + fileName + " . " + size + " bytes.");
                
                FileOutputStream fos = new FileOutputStream(fileName);
                byte[] buf = new byte[4096];
                while(true){
                    
                    int len = in.read(buf);
                    if(len == -1) break;
                    
                    fos.write(buf, 0, len);
                }
                fos.flush();
                fos.close();
                System.out.println("Transferência concluida com sucesso!");
                
                client.close();
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Algum erro com ocorreu...");
                //e.printStarkTrace();
                System.exit(1);
            } 
        }
    }
}
