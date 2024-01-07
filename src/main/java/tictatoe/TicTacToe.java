package tictatoe;

public class TicTacToe {
    private char[][] board;
    private char currentMark;

    public TicTacToe() {
        this.board = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        this.currentMark = 'O';
    }

    public boolean isValidMarking(int row, int col) {

        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') return true;

        return false;
    }

    public synchronized void changeMark(){
        currentMark = (currentMark == 'O') ? 'X' : 'O';
    }

    public String printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                sb.append("[").append(board[i][j]).append("] ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public char getMark() {
        return currentMark;
    }

    public synchronized boolean marking(int row, int col) {

        if (isValidMarking(row, col)) {
            board[row][col] = currentMark;
            return true;
        }
        return false;
    }

    public boolean checkWin() {

        for (int i = 0; i < board.length; i++) {

            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }

        for (int i = 0; i < board.length; i++) {

            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    public boolean isBoardFull() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}
