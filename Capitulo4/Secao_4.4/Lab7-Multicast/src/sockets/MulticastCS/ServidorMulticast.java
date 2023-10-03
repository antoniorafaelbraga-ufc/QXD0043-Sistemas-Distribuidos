package sockets.MulticastCS;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorMulticast {

	public static void main(String[] args) {
		try {
			InetAddress addr = InetAddress.getByName("239.0.0.1");
			DatagramSocket ds = new DatagramSocket();
			
			byte[] b = "Ola Mundo SD 2022.2 de novo".getBytes();
			DatagramPacket pkg = new DatagramPacket(b, b.length, addr, 12347);
			
			ds.send(pkg);
			ds.close();
			
		} catch (Exception e) {
			System.out.println("Nao foi possivel enviar a mensagem");
		}
	}
}