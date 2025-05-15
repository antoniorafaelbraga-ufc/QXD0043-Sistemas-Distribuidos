// tcp_client.c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>         // read(), write(), close()
#include <arpa/inet.h>      // sockaddr_in, inet_pton()

#define PORT 7896
#define BUFFER_SIZE 1024

int main() {
    int sock;
    struct sockaddr_in server_addr;
    char buffer[BUFFER_SIZE];
    char *mensagem = "sistemas_distribuidos";

    sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock < 0) {
        perror("Erro ao criar socket");
        exit(1);
    }

    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(PORT);

    if (inet_pton(AF_INET, "127.0.0.1", &server_addr.sin_addr) <= 0) {
        perror("Endereço inválido");
        exit(1);
    }

    if (connect(sock, (struct sockaddr*) &server_addr, sizeof(server_addr)) < 0) {
        perror("Erro na conexão");
        exit(1);
    }

    printf("Enviado: %s\n", mensagem);
    write(sock, mensagem, strlen(mensagem));

    memset(buffer, 0, BUFFER_SIZE);
    read(sock, buffer, BUFFER_SIZE);
    printf("Recebido: %s\n", buffer);

    close(sock);
    return 0;
}
