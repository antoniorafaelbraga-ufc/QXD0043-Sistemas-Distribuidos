package secao_4_2.sockets.UDP;

import java.net.*;

class clienteUDP {
	public static void main(String args[]) throws Exception {

		//BufferedReader doUsuario = new BufferedReader(new InputStreamReader(System.in));

		DatagramSocket socketCliente = new DatagramSocket();

		InetAddress IPAddress = InetAddress.getByName("localhost");
		System.out.println("IP local: "+IPAddress.getHostAddress());
		
		byte[] dadosEnvio = new byte[1024];
		byte[] dadosRecebidos = new byte[1024];

		//String frase = doUsuario.readLine();
		//dadosEnvio = frase.getBytes();
		
		dadosEnvio = "sistemas distribuidos".getBytes();
		System.out.println("Mensagem original: "+new String(dadosEnvio));
		
		DatagramPacket pacoteEnviado = new DatagramPacket(dadosEnvio,
				dadosEnvio.length, IPAddress, 6789);

		socketCliente.send(pacoteEnviado);

		DatagramPacket pacoteRecebido = new DatagramPacket(
				dadosRecebidos, dadosRecebidos.length);

		socketCliente.receive(pacoteRecebido);

		String fraseModificada = new String(pacoteRecebido.getData());

		System.out.println("Do Servidor: " + fraseModificada);
		socketCliente.close();
	}
}