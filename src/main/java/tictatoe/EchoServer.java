package tictatoe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EchoServer extends Thread {

    private BufferedReader br;
    private BufferedWriter bw;
    private TicTacToe ticTacToe;
    private char mark;
    private List<EchoServer> echoServerList;

    public EchoServer(BufferedReader br, BufferedWriter bw, TicTacToe ticTacToe, char mark, List<EchoServer> echoServerList) {
        this.br = br;
        this.bw = bw;
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.echoServerList = echoServerList;

    }

    private void write(String message) throws IOException {
        for (EchoServer echoServer : echoServerList) {
            echoServer.bw.write(message);
            echoServer.bw.flush();
        }
    }

    @Override
    public void run() {


        try {
            while (!ticTacToe.checkWin() && !ticTacToe.isBoardFull()) {

                write(ticTacToe.printBoard());

                if (ticTacToe.getMark() == mark) {

                    write(mark + " 차례입니다.");

                    char[] marking = br.readLine().toCharArray();
                    int row = marking[0] - 1;
                    int col = marking[1] - 1;

                    if (ticTacToe.marking(row, col)) {

                        if (ticTacToe.checkWin()) {

                            write(mark + " 플레이어가 승리했습니다");
                            break;
                        } else if (ticTacToe.isBoardFull()) {

                            write("무승부 입니다.");
                            break;
                        } else {
                            ticTacToe.changeMark();
                            ticTacToe.notify();
                        }

                    } else {
                        write("올바른 위치를 지정해주세요");
                    }
                } else {
                    ticTacToe.wait();
                }
            }
        } catch (IOException e) {
            System.err.println("입출력 오류");
        } catch (InterruptedException e) {
            System.err.println("인터럽트 발생");
        }
    }
}
