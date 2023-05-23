import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server(64,9999);
        server.addHandler("GET", "/messages", ((request, responseStream) -> {
            try {
                server.responseWithoutContent(responseStream,"404", "Not found");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

        server.addHandler("POST", "/messages", (request, responseStream) ->
                server.responseWithoutContent(responseStream, "404", "Not found"));
        server.addHandler("GET", "/", ((request, responseStream) -> server.defaultHandler(responseStream, "spring.png")));
        server.start();
    }
}