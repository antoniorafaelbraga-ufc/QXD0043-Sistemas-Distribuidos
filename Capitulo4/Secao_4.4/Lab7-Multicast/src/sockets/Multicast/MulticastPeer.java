package sockets.Multicast;

import java.net.*;
import java.io.*;

public class MulticastPeer {
	public static void main(String args[]) {
		// args give message contents and destination multicast group (e.g. "239.0.0.1")
		MulticastSocket s = null;
		try {
			InetAddress group = InetAddress.getByName("239.0.0.1");
			s = new MulticastSocket(6789);
			s.joinGroup(group);
			
			byte[] m = "Ola mundo SD 2021.1".getBytes();
			DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6789);
			s.send(messageOut);
			
			byte[] buffer = new byte[1000];
			for (int i = 0; i < 3; i++) { // get messages from others in group
				DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
				s.receive(messageIn);
				System.out.println("Received:" + new String(messageIn.getData()));
			}
			s.leaveGroup(group);
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (s != null)
				s.close();
		}
	}
}