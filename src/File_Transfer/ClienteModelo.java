
package File_Transfer;


public abstract class ClienteModelo {
    private String ipServidor;
    private int porta;
    
    public ClienteModelo(String ipServidor_, int porta_){
        this.ipServidor = ipServidor_;
        this.porta = porta_;
    }
    public String getIpServidor(){
        return this.ipServidor;
    }
    public int getPorta(){
        return this.porta;
    }
    public void setIpServidor(String ipServidor_){
        this.ipServidor = ipServidor_;
    }
    public void setPorta(int porta_){
        this.porta = porta_;
    }
    public abstract void enviaArquivo(String path);
}