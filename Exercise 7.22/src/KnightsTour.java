/* Java Program By Joel Trejo for OOP2 Spring 2020
 *
 *              @@@@@@@@@    @@
 *	         @@       @@@ @@@
 *	       @@@         @@@@@
 *	      @@
 *	     @@   @@@@@@@
 *	    @@   @@     @@
 *	   @@   @@       @@
 *	   @   @@         @@
 *	   @   @    @@@@   @@
 *	   @   @   @@  @@   @@
 *	   @   @@  @@   @@   @@
 *	   @   @@@      @@   @@
 *	   @@   @@@   @@@    @@
 *	   @@@   @@@ @@@    @@@
 *	   @ @@   @@@@@    @@@
 *	   @  @@          @@@
 *	   @   @@        @@@
 *	   @@@@@@@@@@@@@@@@
 *
 */
public class KnightsTour {
    int[][] board;
    int testColumn, testRow, moveType;
    int currentColumn, currentRow, moveNumber;
    int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
    boolean done, validMove;

    public KnightsTour() {
        initializeBoard();
    }

    public void initializeBoard() {
         board = new int[8][8];
         currentColumn = (int) (Math.random() * 8);
         currentRow = (int) (Math.random() * 8);
         board[currentRow][currentColumn] = ++moveNumber;
         done = false;
    }

    public boolean isValidMove(int row, int column) {
        if((row >= 0 && row <= 7) && (column >= 0 && column <= 7) && board[row][column] == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void nextMove() {
        while(!done) {
            moveType = 0;
            testColumn = currentColumn + horizontal[moveType];
            testRow = currentRow + vertical[moveType];
            validMove = isValidMove(testRow, testColumn);
            if(validMove) {
                currentRow = testRow;
                currentColumn = testColumn;
                board[currentRow][currentColumn] = ++moveNumber;
            }
            else {
                for(int i = 0; i < 7 && !validMove; ++i) {
                    moveType = ++moveType % 8;
                    testRow = currentRow + vertical[moveType];
                    testColumn = currentColumn + horizontal[moveType];
                    validMove = isValidMove(testRow, testColumn);
                    if(validMove) {
                        currentRow = testRow;
                        currentColumn = testColumn;
                        board[currentRow][currentColumn] = ++moveNumber;
                    }
                }
                if(!validMove) {
                    done = true;
                }
            }
            if(moveNumber == 64) {
                done = true;
            }
        }
    }

    public void printBoard() {
        for(int[] column : board) {
            for(int num : column) {
                System.out.printf("%-5d", num);
            }
            System.out.println();
        }
        System.out.println();
    }
}
