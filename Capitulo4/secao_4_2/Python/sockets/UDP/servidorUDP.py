import socket

def main():
    host = '0.0.0.0'
    port = 9876
    buffer_size = 1024

    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.bind((host, port))
    print("Servidor iniciado e aguardando mensagens...")

    while True:
        data, address = sock.recvfrom(buffer_size)
        print(f"Recebido de {address}: {data.decode()}")
        sock.sendto(data.upper(), address)

if __name__ == "__main__":
    main()
