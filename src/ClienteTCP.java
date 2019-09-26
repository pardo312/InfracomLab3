import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import java.io.*;
 
class ClienteTCP{
	
	public static void main (String[] args) throws UnknownHostException, IOException, NoSuchAlgorithmException{
	
	 byte[] b = new byte[8192];
	 Socket socket = new Socket("localhost", 8080);

     
             try {
            	 InputStream is = socket.getInputStream();
            	 DataInputStream entrada = new DataInputStream(is);
                 OutputStream output = socket.getOutputStream(); 
                 DataOutputStream salida = new DataOutputStream(output);
            	 
                 salida.writeUTF("Hola");
                 String mensajeRecibido = entrada.readUTF();
                 
                 if(mensajeRecibido.equals("Hola"))
                 {  	 
                	 salida.writeUTF("Listo para recibir datos");
                 }
            	 
            	 mensajeRecibido = entrada.readUTF();
            	 System.out.println("Cliente nmo: "+mensajeRecibido);
            	 if(Integer.parseInt(mensajeRecibido) == 1)
            	 {

                     System.out.println("-----Menu Opciones------");

            		 System.out.println("1. Descargar archivo 100 MiB");
                     System.out.println("2. Descargar archivo 250 MiB");
                     String fileSelection = new BufferedReader(new InputStreamReader(System.in)).readLine();
                     System.out.println("A cuantos usuarios desea enviar el archivo?");
                     String numClientes = new BufferedReader(new InputStreamReader(System.in)).readLine();
                     salida.writeUTF(numClientes+","+fileSelection);   
            	 }
                                 
                          	 
            	 
            	 FileOutputStream fr = new FileOutputStream("./data/ArchivoCliente" + (int)(Math.random()*100) +".txt");
         		
            	 is.read(b, 0, b.length);
            	 fr.write(b, 0, b.length);
            	 
            	 
            	  byte[] hash = new byte[32];
            	  is.read(hash, 0, hash.length);
            	  
            	  MessageDigest md = MessageDigest.getInstance("MD5");
            	    md.update(b);
            	    byte[] digest = md.digest();
            	    String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            	    byte[] h = myHash.getBytes();
            	   
            	   
            	   if( Arrays.equals(h, hash))
            	   {
            		  
            		   salida.writeUTF("Archivo entregado e integridad del archivo verificada");
            	   }
 	      		           
             	
             }
             catch (IOException | NoSuchAlgorithmException e) {
             }
         
         
     
	 
	}
}