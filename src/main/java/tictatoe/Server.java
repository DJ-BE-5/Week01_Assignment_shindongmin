package tictatoe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {

    private static int participant = 0;
    private static TicTacToe ticTacToe = new TicTacToe();

    public static void main(String[] args) {

        List<EchoServer> echoServerList = new LinkedList<>();

        try (ServerSocket serverSocket = new ServerSocket(12345)) {

            while (!serverSocket.isClosed()) {

                System.out.println("Waiting Player");
                Socket socket = serverSocket.accept();
                participant++;
                System.out.println("2명이 있어야 게임이 시작되며 현재 인원은 : " + participant + " 명입니다.");

                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                EchoServer echoServer = new EchoServer(br, bw, ticTacToe, 'O', echoServerList);
                echoServerList.add(echoServer);

                if (participant == 2) {

                    System.out.println("게임이 시작되었습니다.");

                    for (EchoServer echo : echoServerList) {
                        echo.start();
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("입출력 예외");
        }
    }
}
