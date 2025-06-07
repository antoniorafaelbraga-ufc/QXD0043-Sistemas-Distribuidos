import socket
import json
# Arquivo: client.py
# Implementação do cliente RPC
def call_remote_method(method, params):
    # Configura o socket do cliente
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect(('localhost', 5000))
    
    # Prepara a requisição
    request = {
        'method': method,
        'params': params
    }
    
    # Envia a requisição ao servidor
    print(f"Enviando requisição: {request}")
    client_socket.sendall(json.dumps(request).encode('utf-8'))
    
    # Recebe a resposta
    response_data = client_socket.recv(1024).decode('utf-8')
    client_socket.close()
    
    # Parseia e retorna a resposta
    response = json.loads(response_data)
    print(f"Resposta recebida: {response}")
    
    return response

if __name__ == "__main__":
    # Chama o método remoto
    result = call_remote_method('say_hello', ['Usuário'])
    
    if result.get('status') == 'success':
        print(f"Resultado: {result.get('result')}")
    else:
        print(f"Erro: {result.get('message')}")
