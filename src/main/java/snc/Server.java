package snc;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private int port;

    private boolean running = false;

    public Server(int port){
        this.port = port;
    }

    @Override
    public void run() {

        running = true;
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket socket = serverSocket.accept();
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("ServerSocket Connected");

            while (running) {

                // 클라이언트가 출력한 메시지 입력 받기
                String inputLine;
                while ((inputLine = input.readLine()) != null){
                    System.out.println("Receive Message : " + inputLine);
                }

                // 클라이언트에게 입력할 메시지 출력하기
                String outputLine;
                while ((outputLine = serverInput.readLine()) != null){
                    output.write(outputLine + "\n");
                    output.flush();
                }
            }
        } catch (IOException e) {
            running = false;
            System.err.println("입출력 오류");
        }
    }
}

