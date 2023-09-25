package sockets.TCP;

import java.net.*;
import java.io.*;

public class TCPClient {
	public static void main(String args[]) {
		// arguments supply message and hostname
		Socket s = null;
		try {
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			String envio = "sistemas_distribuidos";
			System.out.println("Sent: "+envio);
			out.writeUTF(envio); // UTF is a string encoding see Sn. 4.4
			String data = in.readUTF(); // read a line of data from the stream
			System.out.println("Received: " + data);
		} catch (UnknownHostException e) {
			System.out.println("Socket:" + e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("readline:" + e.getMessage());
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
		}
	}
}