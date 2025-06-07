# Arquivo: server.py
# Implementação do servidor RPC

import socket
import json

from hello_service import HelloService

def start_server():
    # Cria o objeto de serviço
    service = HelloService()
    
    # Configura o socket do servidor
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(('localhost', 5000))
    server_socket.listen(5)
    
    print("Servidor pronto na porta 5000...")
    
    try:
        while True:
            # Aceita conexões de clientes
            client_socket, client_address = server_socket.accept()
            print(f"Cliente conectado: {client_address}")
            
            # Recebe os dados do cliente
            data = client_socket.recv(1024).decode('utf-8')
            if not data:
                continue
                
            print(f"Requisição recebida: {data}")
            
            # Parseia a requisição
            try:
                request = json.loads(data)
                method = request.get('method')
                params = request.get('params', [])
                
                # Processa a chamada ao método
                if method == 'say_hello' and len(params) > 0:
                    result = service.say_hello(params[0])
                    response = {'status': 'success', 'result': result}
                else:
                    response = {'status': 'error', 'message': 'Método não suportado ou parâmetros inválidos'}
                    
                # Envia a resposta de volta ao cliente
                client_socket.sendall(json.dumps(response).encode('utf-8'))
                
            except json.JSONDecodeError:
                error_response = {'status': 'error', 'message': 'Formato de requisição inválido'}
                client_socket.sendall(json.dumps(error_response).encode('utf-8'))
            except Exception as e:
                error_response = {'status': 'error', 'message': str(e)}
                client_socket.sendall(json.dumps(error_response).encode('utf-8'))
            finally:
                client_socket.close()
                
    except KeyboardInterrupt:
        print("\nServidor encerrado.")
    finally:
        server_socket.close()

if __name__ == "__main__":
    start_server()