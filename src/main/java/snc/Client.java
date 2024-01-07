package snc;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {
    private String host;
    private int port;

    private boolean running = false;
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {

        try (Socket socket = new Socket(host, port);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Client Connected : " + socket.getInetAddress() + ":" + socket.getPort());

            running = true;

            while (running) {

                String clientInputLine;
                System.out.println("Send Message");
                // 서버로 입력하기
                while ((clientInputLine = clientInput.readLine()) != null) {
                    output.write(clientInputLine + "\n");
                    output.flush();
                }

                // 서버에서 출력한 값 받기
                String receiveMessage;
                while ((receiveMessage = input.readLine()) != null) {
                    System.out.println("Receive Message : " + receiveMessage);
                }
            }
        } catch (UnknownHostException e) {
            running = false;
            System.err.println("호스트 에러");
        } catch (IOException e) {
            running = false;
            System.err.println("입출력 에러");
        }

    }
}
