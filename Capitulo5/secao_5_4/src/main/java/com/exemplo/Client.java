import java.rmi.Naming;
public class Client {
    public static void main(String[] args) {
        try {
            // Busca o serviço remoto
            Hello hello = (Hello) Naming.lookup("rmi://localhost:1099/HelloService");
            // Faz a chamada ao método remoto
            String response = hello.sayHello("Usuário");
            System.out.println("Resposta do servidor: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}