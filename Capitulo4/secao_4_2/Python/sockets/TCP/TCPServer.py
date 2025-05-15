import socket
import threading

class Connection(threading.Thread):
    def __init__(self, client_socket, client_address):
        threading.Thread.__init__(self)
        self.client_socket = client_socket
        self.client_address = client_address
        self.start()

    def run(self):
        try:
            data = self.client_socket.recv(1024).decode('utf-8')
            print(f"Received from {self.client_address}: {data}")
            self.client_socket.sendall(data.upper().encode('utf-8'))
        except Exception as e:
            print(f"Error with client {self.client_address}: {e}")
        finally:
            self.client_socket.close()

def main():
    host = '0.0.0.0'  # Escuta em todas as interfaces
    port = 7896

    try:
        print("Servidor iniciado")
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
            server_socket.bind((host, port))
            server_socket.listen()

            while True:
                client_socket, client_address = server_socket.accept()
                print(f"Conexão estabelecida com {client_address}")
                Connection(client_socket, client_address)

    except Exception as e:
        print("Listen socket error:", e)

if __name__ == "__main__":
    main()
