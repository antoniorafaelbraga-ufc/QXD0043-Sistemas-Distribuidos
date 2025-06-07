# Implementação de RPC (Remote Procedure Call) com sockets em Python

# Arquivo: hello_service.py
# Define a interface e implementação do serviço
class HelloService:
    def say_hello(self, name):
        return f"Olá, {name}! Este é um exemplo de RPC com sockets em Python."