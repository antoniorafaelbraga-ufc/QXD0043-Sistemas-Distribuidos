// tcp_server.c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>         // close()
#include <arpa/inet.h>      // sockaddr_in, inet_ntoa()
#include <pthread.h>

#define PORT 7896
#define BUFFER_SIZE 1024

void* handle_client(void* client_socket_ptr) {
    int client_socket = *(int*)client_socket_ptr;
    free(client_socket_ptr);
    
    char buffer[BUFFER_SIZE];
    memset(buffer, 0, BUFFER_SIZE);

    int bytes_read = read(client_socket, buffer, BUFFER_SIZE);
    if (bytes_read > 0) {
        printf("Recebido: %s\n", buffer);

        // Converte para maiúsculas
        for (int i = 0; buffer[i]; i++) {
            if (buffer[i] >= 'a' && buffer[i] <= 'z') {
                buffer[i] = buffer[i] - 32;
            }
        }

        write(client_socket, buffer, strlen(buffer));
    }

    close(client_socket);
    return NULL;
}

int main() {
    int server_socket, client_socket;
    struct sockaddr_in server_addr, client_addr;
    socklen_t client_len = sizeof(client_addr);

    printf("Servidor iniciado\n");

    server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket < 0) {
        perror("Erro ao criar socket");
        exit(1);
    }

    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(PORT);
    server_addr.sin_addr.s_addr = INADDR_ANY;

    if (bind(server_socket, (struct sockaddr*) &server_addr, sizeof(server_addr)) < 0) {
        perror("Erro no bind");
        exit(1);
    }

    listen(server_socket, 5);

    while (1) {
        client_socket = accept(server_socket, (struct sockaddr*) &client_addr, &client_len);
        if (client_socket < 0) {
            perror("Erro no accept");
            continue;
        }

        printf("Conexão estabelecida com %s\n", inet_ntoa(client_addr.sin_addr));

        pthread_t thread;
        int* client_ptr = malloc(sizeof(int));
        *client_ptr = client_socket;
        pthread_create(&thread, NULL, handle_client, client_ptr);
        pthread_detach(thread);
    }

    close(server_socket);
    return 0;
}
