import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

public class Server {
	
	
	static List<Socket> clients = new ArrayList<Socket>();  
	private static int numClientes = 0;
	static BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
	  
	
	public static void main(String[] args) throws IOException {
	 	
	        	ServerSocket serverSocket = new ServerSocket(8080);
	            System.out.println("Server is listening on port " + 8080);
	 
	            
	            new Thread(new Runnable() {
	                public void run() {
	                    try {
	                    	while (true) {
	                    		
	        	                Socket socket = serverSocket.accept();  
	        	                DataInputStream entrada = new DataInputStream(socket.getInputStream());
	        	                OutputStream output = socket.getOutputStream(); 
	        	                DataOutputStream salida = new DataOutputStream(output);
	        	                
	        	                String mensajeRecibido = entrada.readUTF();
	        	                if(mensajeRecibido.equals("Hola"))
	        	                {
	        	                	salida.writeUTF("Hola");
	        	      		    clients.add(socket);
	        	      		    salida.writeUTF(""+clients.size());
	        	      		    
	        	      		    
	        	      		  System.out.println("");
	        	      		    System.out.println("-----------------------");
	        	      		    System.out.println("Nuevo Cliente conectado ");
	        	      		    System.out.println("Clientes conectados: " +  clients.size());
	        	      		  
	                      		System.out.println(mensajeRecibido);
	        	      		    System.out.println("-----------------------");
	        	      		  System.out.println("");
	        	      		    System.out.println("-----Menu Opciones------");
	        	               
	        	               System.out.println("1. Descargar archivo 100 MiB");
	        	               System.out.println("2. Descargar archivo 250 MiB");
	        	                };
	                    	}
	                    }
	                    catch (IOException e) {
	                    }
	                }
	            }).start();
	            	  
		            	  
		        
		            	  
	        }

	 
	   
	  
//	  public static void menu() throws IOException
//	  {
//		  
//			  
//		  
//		  
//           
//          for(int x = 0; x < Integer.parseInt(numClientes);x++)
//          {
//        	  OutputStream output = clients.get(x).getOutputStream(); 
//        	  DataInputStream entrada = new DataInputStream(clients.get(x).getInputStream());
//        	  new Thread(new Runnable() {
//	                public void run() {
//	                    try {
//	                    	
//	                    	if(Integer.parseInt(numClientes) > clients.size())
//	                  		{
//	                  			System.out.println("Numero de clientes menor al especificado.");
//	                  		}
//	                  		else if(Integer.parseInt(fileSelection) == 1)
//	                      	{
//
//	                      		FileInputStream fr = new FileInputStream("./data/100MiB.txt");
//	                      		byte b[] = new 	byte[8192];
//	                      		fr.read(b, 0, b.length);
//	                      		long startTime = System.currentTimeMillis();
//	                      		output.write(b, 0, b.length);
//	                      		output.write(hasher(b));
//	                      		
//	                      		//System.out.println("Archivo enviado exitosamente.");
//	                      		String mensajeRecibido = entrada.readUTF();
//	                      		System.out.println(mensajeRecibido);
//	                      		long endTime = System.currentTimeMillis();
//	                      		System.out.println("Tiempo transcurrido: " + (endTime - startTime) + " ms.");
//
//	                      	}
//	                      	else if(Integer.parseInt(fileSelection) == 2 )
//	                      	{
//	                      		//InputStream input = clients.get(x).getInputStream();  	                   
//	           	                
//	                      		FileInputStream fr = new FileInputStream("./data/250MiB.txt");
//	                      		byte b[] = new byte[8192];
//	                      		fr.read(b, 0, b.length);
//	                      		output.write(b, 0, b.length);
//	                      		output.write(hasher(b));
//	                      		//En vez de esto que el cliente mande el mensaje
//	                      		//System.out.println("Archivo enviado exitosamente.");
//	                      		String mensajeRecibido = entrada.readUTF();
//	                      		System.out.println(mensajeRecibido);
//	                      	}
//	                      	else
//	                      	{
//	                      		System.out.println("Opcion incorrecta");
//	                      	}
//	                    	
//	                    }
//	                    catch (IOException | NoSuchAlgorithmException e) {
//	                    }
//	                }
//	            }).start();
//          }
//          
//          
//		
//	  }
//	  
//	  static byte[] hasher(byte[] b) throws NoSuchAlgorithmException
//	    {
//
//
//	        MessageDigest md = MessageDigest.getInstance("MD5");
//		    md.update(b);
//		    byte[] digest = md.digest();
//		    String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
//	        
//		    byte[] z = myHash.getBytes();
//		    return z;
//	    }
//}
//
}