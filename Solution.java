import java.util.LinkedList;

public class Solution {

    public char[][] board;
    public boolean[][] visited;
    public int[][] moves = {
        {-1, 0},//up
        {1, 0},//down
        {0, -1},//left
        {0, 1}//right
    };

    public void solve(char[][] board) {
        this.board = board;
        visited = new boolean[board.length][board[0].length];
        checkForRegionsOnBorder();
        markSurroundedRegions();
    }

    public void markSurroundedRegions() {
        for (int r = 1; r < board.length - 1; r++) {
            for (int c = 1; c < board[0].length - 1; c++) {
                if (board[r][c] == 'O' && !visited[r][c]) {
                    board[r][c] = 'X';
                }
            }
        }
    }

    public void checkForRegionsOnBorder() {

        for (int i = 0; i < board.length; i++) {

            if (board[i][0] == 'O' && !visited[i][0]) {
                breadthFirstSearch(i, 0);
            }

            if (board[i][board[0].length - 1] == 'O' && !visited[i][board[0].length - 1]) {
                breadthFirstSearch(i, board[0].length - 1);
            }
        }

        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O' && !visited[0][i]) {
                breadthFirstSearch(0, i);
            }

            if (board[board.length - 1][i] == 'O' && !visited[board.length - 1][i]) {
                breadthFirstSearch(board.length - 1, i);
            }
        }

    }

    public void breadthFirstSearch(int row, int column) {
        LinkedList<Point> queue = new LinkedList<>();
        queue.add(new Point(row, column));
        visited[row][column] = true;

        while (!queue.isEmpty()) {
            Point p = queue.removeFirst();

            for (int i = 0; i < moves.length; i++) {
                int new_row = p.row + moves[i][0];
                int new_column = p.column + moves[i][1];

                if (isInBoard(new_row, new_column) && board[new_row][new_column] == 'O' && !visited[new_row][new_column]) {
                    queue.add(new Point(new_row, new_column));
                    visited[new_row][new_column] = true;
                }
            }
        }
    }

    public boolean isInBoard(int row, int column) {
        return row < board.length && row >= 0 && column < board[0].length && column >= 0;
    }
}

class Point {

    int row;
    int column;

    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
