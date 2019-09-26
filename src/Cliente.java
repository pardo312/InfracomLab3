import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import java.io.*;
 
class Cliente{
	
	public static void main (String[] args) throws UnknownHostException, IOException, NoSuchAlgorithmException{
	
	 byte[] b = new byte[8192];
	 Socket socket = new Socket("localhost", 8080);
	 
	 OutputStream output = socket.getOutputStream(); 
	 DataOutputStream salida = new DataOutputStream(output);
	 InputStream is = socket.getInputStream();
	 salida.writeUTF("Hola");
	 
	 
	 
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
}