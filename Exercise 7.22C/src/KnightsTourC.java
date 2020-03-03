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
public class KnightsTourC {
    int[][] board;
    int[][] accessibility = {{2, 3, 4, 4, 4, 4, 3, 2},
            {3, 4, 6, 6, 6, 6, 4, 3},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {3, 4, 6, 6, 6, 6, 4, 3},
            {2, 3, 4, 4, 4, 4, 3, 2}};
    int testColumn, testRow, moveType, invalid;
    int currentColumn, currentRow, moveNumber, leastAccessible, tempMove;
    int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
    boolean done, validMove;

    public KnightsTourC() {
        initializeBoard();
    }

    public void initializeBoard() {
        board = new int[8][8];
        currentColumn = (int) (Math.random() * 8);
        currentRow = (int) (Math.random() * 8);
        board[currentRow][currentColumn] = ++moveNumber;
        accessibility[currentRow][currentColumn] = 0;
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

    public void updateAccessibility() {
        int moveType = 0;
        int testColumn, testRow;
        leastAccessible = -1;

        while(moveType <= 7) {
            testColumn = currentColumn + horizontal[moveType];
            testRow = currentRow + vertical[moveType];
            validMove = isValidMove(testRow, testColumn);
            if(validMove) {
                accessibility[testRow][testColumn]--;
                if((leastAccessible >= accessibility[testRow][testColumn]) || leastAccessible == -1) {
                    leastAccessible = accessibility[testRow][testColumn];
                    tempMove = moveType;
                }
            }
            moveType++;
        }
    }

    public void nextMove() {
        while(!done) {
            updateAccessibility();
            moveType = 0;
            int i;
            testColumn = currentColumn + horizontal[moveType];
            testRow = currentRow + vertical[moveType];
            validMove = isValidMove(testRow, testColumn);
                for(i = 0; i <= 7; ++i) {
                    moveType = ++moveType % 8;
                    testRow = currentRow + vertical[moveType];
                    testColumn = currentColumn + horizontal[moveType];
                    validMove = isValidMove(testRow, testColumn);
                    if(validMove && moveType == tempMove) {
                        currentRow = testRow;
                        currentColumn = testColumn;
                        board[currentRow][currentColumn] = ++moveNumber;
                        accessibility[currentRow][currentColumn] = 0;
                        break;
                    }
                }
            if(moveNumber == 64 || i == 8) {
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

        for(int[] column : accessibility) {
            for(int num : column) {
                System.out.printf("%-5d", num);
            }
            System.out.println();
        }
        System.out.println();
    }
}
