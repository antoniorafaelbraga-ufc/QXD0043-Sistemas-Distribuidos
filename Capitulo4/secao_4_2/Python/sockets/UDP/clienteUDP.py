import socket

def main():
    server_address = ('localhost', 9876)
    mensagem = "sistemas_distribuidos"

    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    try:
        print(f"Enviado: {mensagem}")
        sock.sendto(mensagem.encode(), server_address)
        data, _ = sock.recvfrom(1024)
        print(f"Recebido: {data.decode()}")
    finally:
        sock.close()

if __name__ == "__main__":
    main()
