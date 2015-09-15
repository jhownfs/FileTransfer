
package File_Transfer;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*; 
import java.io.File;

// Classe Janela Principal

public class Janela extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	JLabel  rtlServidor, rtlCliente, rtlPorta, rtlIp, rtlNomeArquivo;
	JTextField txtPorta, txtNomeArquivo, txtIp;
	JButton btAplicarPorta, btBrowse, btEnviar, btSair;
	
	

    Janela() {
	    	setTitle ("File Transfer - CC5P33 - V01 ");
	        setBounds (100,100,430,400);
	        
	        rtlServidor = new JLabel("SERVIDOR");
	        rtlServidor.setBounds(10, 5, 200, 40);
	               
	        rtlPorta = new JLabel("Porta: ");
	        rtlPorta.setBounds(10, 50, 100, 20);
	
	        txtPorta = new JTextField ("8080",5);
	        txtPorta.setBounds (70,50,50,20);
	       
	        btAplicarPorta = new JButton("Aplicar");
	        btAplicarPorta.setBounds(150,50,120,20);
	       
	        rtlCliente = new JLabel("CLIENTE");
	        rtlCliente.setBounds(10, 90, 200, 40);
	
	             
	        rtlIp = new JLabel("IP Destino");
	        rtlIp.setBounds(10, 130, 100, 20);
	        
	        txtIp = new JTextField ("",16);
	        txtIp.setBounds (100,130,120,20);
	
	        
	        rtlNomeArquivo = new JLabel("Arquivo");
	        rtlNomeArquivo.setBounds(10, 170, 100, 20);
	        
	        txtNomeArquivo = new JTextField ("",16);
	        txtNomeArquivo.setBounds (100,170,120,20);
	        
	        btBrowse = new JButton("Browse");
	        btBrowse.setBounds(250, 170, 120, 20);
	        
	        btEnviar = new JButton("Enviar");
	        btEnviar.setBounds(40, 280, 320, 20);
	        
	        btSair = new JButton("Fechar");
	        btSair.setBounds(40, 310, 320, 20);
	        
	        getContentPane().setLayout(null); 
	        getContentPane().add(rtlServidor);
	        getContentPane().add(rtlPorta); 
	        getContentPane().add(txtPorta);
	        getContentPane().add(btAplicarPorta);
	        getContentPane().add(rtlCliente);
	        getContentPane().add(rtlIp);
	        getContentPane().add(txtIp);
	        getContentPane().add(rtlNomeArquivo);
	        getContentPane().add(txtNomeArquivo);
	        getContentPane().add(btBrowse);
	        getContentPane().add(btEnviar);
	        getContentPane().add(btSair);
	        btAplicarPorta.addActionListener(this);
	        btBrowse.addActionListener(this);
	        btEnviar.addActionListener(this);
	        btSair.addActionListener(this);
        }
  //Método Abrir Arquivo
    
    private void abreArquivo() {
        JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile();
            txtNomeArquivo.setText(arquivo.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(null, "Você não selecionou o arquivo!");
        }
    }
// Método Aplicar Porta
    
    public void actionPerformed(ActionEvent e) {
		        if ((e.getSource() == btAplicarPorta) && (!txtPorta.getText().equals(""))) {
		            try {
		                new Servidor(Integer.parseInt(txtPorta.getText()));
		            } catch (Exception e1) {
		                JOptionPane.showMessageDialog(null, "Algum erro ocorreu...");
		                e1.printStackTrace();
		            }
		        }
		        if ((e.getSource() == btBrowse)) {
		            try {
		                this.abreArquivo();
		            } catch (Exception e1) {
		                JOptionPane.showMessageDialog(null, "Erro ao abrir Aquivo...");
		            }
		        }
		
		        if (e.getSource() == btEnviar) {
		            String ip;
		            int porta;
		            ip = ((txtIp.getText()));
		            porta = Integer.parseInt((txtPorta.getText()));
		            Cliente cliente = new Cliente(ip, porta);
		            cliente.enviaArquivo(txtNomeArquivo.getText());
		        	}
  
		//Método Fechar Aplicação       
    
				    if (e.getSource() == btSair) {
				        System.out.println("Servidor encerrado...");
				        System.exit(0);
				    }
    		}
 
}