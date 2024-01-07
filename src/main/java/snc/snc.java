package snc;

public class snc {


    public static void main(String[] args) {

        if (args.length < 2 || args.length > 3) {
            System.err.println("Usage: snc [option] [hostname] [port]");
            System.exit(1);
        }

        // 서버 모드
        if ("-l".equals(args[0])) {
            int serverPort = Integer.parseInt(args[1]);
            Server server = new Server(serverPort);
            server.start();
        } else {
            // 클라이언트 모드
            String serverHost = args[1];
            int serverPort = Integer.parseInt(args[2]);
            Client client = new Client(serverHost, serverPort);
            client.start();
        }
    }
}
