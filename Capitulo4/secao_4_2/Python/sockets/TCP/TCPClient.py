import socket

def main():
    host = 'localhost'
    port = 7896

    try:
        # Cria o socket e conecta ao servidor
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
            s.connect((host, port))
            envio = "sistemas_distribuidos"
            print(f"Sent: {envio}")
            
            # Envia os dados codificados como UTF-8
            s.sendall(envio.encode('utf-8'))

            # Recebe a resposta do servidor (espera até 1024 bytes)
            data = s.recv(1024)
            print(f"Received: {data.decode('utf-8')}")

    except socket.gaierror as e:
        print("Socket error:", e)
    except ConnectionRefusedError:
        print("Connection refused. Certifique-se de que o servidor está em execução.")
    except Exception as e:
        print("Error:", e)

if __name__ == "__main__":
    main()
