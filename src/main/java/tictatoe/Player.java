package tictatoe;

import com.sun.source.tree.TryTree;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

public class Player extends Thread {

    @Override
    public void run() {


        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader playerInput = new BufferedReader(new InputStreamReader(System.in))) {

            String serverMessage;
            while ((serverMessage = input.readLine()) != null) {
                System.out.println(serverMessage);
            }

            System.out.println("행과 열을 입력해주세요 ex) 11, 21, 32");
            String message = playerInput.readLine();
            output.write(message + "\n");
            output.flush();


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
