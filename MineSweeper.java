import java.util.Scanner;

public class MineSweeper {
    String[][] game;
    String[][] minedMap;
    int mineNumber;
    int row;
    int column;

    public MineSweeper(int row, int column) {
        this.row = row;
        this.column = column;
        this.game = new String[row][column];
        this.minedMap = new String[row][column];
        this.mineNumber = row * column / 4;
    }

    public void gameMap() {

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.game[i][j] = "-";
            }
        }
    }

    public void minedMap() {
        int placedMine = 0;

        while (placedMine != mineNumber) {
            int mineRow = (int) (Math.random() * this.row);
            int mineColumn = (int) (Math.random() * this.column);

            if (this.minedMap[mineRow][mineColumn] != ("*")) {
                minedMap[mineRow][mineColumn] = "*";
                placedMine++;
            }
        }

        for (int j = 0; j < this.row; j++) {
            for (int k = 0; k < this.column; k++) {
                if (this.minedMap[j][k] != ("*")) {
                    minedMap[j][k] = "-";
                }
            }
        }
    }

    public void showingGameMap() {

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                System.out.print(this.game[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void showingMinedMap() {

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                System.out.print(this.minedMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int neighboursMineNumber(int rowInput, int columnInput) {
        int sum = 0;

        int rowMin;
        int columnMin;
        int rowMax;
        int columnMax;

        if (rowInput == 0) {
            rowMin = 0;
            rowMax = rowInput + 1;

            if (columnInput == 0) {
                columnMin = 0;
                columnMax = 1;
            } else if (columnInput == (column - 1)) {
                columnMin = columnInput - 1;
                columnMax = columnInput;
            } else {
                columnMin = columnInput - 1;
                columnMax = columnInput + 1;
            }
        } else if (rowInput == (row - 1)) {
            rowMin = rowInput - 1;
            rowMax = rowInput;

            if (columnInput == 0) {
                columnMin = 0;
                columnMax = 1;
            } else if (columnInput == (column - 1)) {
                columnMin = columnInput - 1;
                columnMax = columnInput;
            } else {
                columnMin = columnInput - 1;
                columnMax = columnInput + 1;
            }
        } else {
            rowMin = rowInput - 1;
            rowMax = rowInput + 1;

            if (columnInput == 0) {
                columnMin = 0;
                columnMax = 1;
            } else if (columnInput == (column - 1)) {
                columnMin = columnInput - 1;
                columnMax = columnInput;
            } else {
                columnMin = columnInput - 1;
                columnMax = columnInput + 1;
            }
        }

        for (int i = rowMin; i <= rowMax; i++) {
            for (int j = columnMin; j <= columnMax; j++) {
                if (this.minedMap[i][j] == "*") {
                    sum++;
                }
            }
        }


        return sum;
    }

    public void completeGameMap() {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (this.game[i][j] == "-") {
                    this.game[i][j] = "*";
                }
            }
        }
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        int totalMove = (this.row * this.column) - mineNumber;

        System.out.println("==== Welcome to Minesweeper Game ====");
        gameMap();
        minedMap();
        //showingMinedMap();

        while (totalMove != 0) {
            System.out.println("=============================");
            showingGameMap();
            System.out.print("Please enter a row number: ");
            int rowInput = input.nextInt();
            System.out.print("Please enter a column number: ");
            int columnInput = input.nextInt();

            if (rowInput <= 0 || rowInput > this.row || columnInput <= 0 || columnInput > this.column) {
                System.out.println("\nYou have entered invalid number. Please choose within limits!!!");
            } else {
                if (this.minedMap[rowInput - 1][columnInput - 1].equals("*")) {
                    System.out.println("\nYou stepped on a mine.\nGame over...");
                    showingMinedMap();
                    break;
                } else {
                    this.game[rowInput - 1][columnInput - 1] = String.valueOf(neighboursMineNumber(rowInput - 1, columnInput - 1));
                    totalMove--;
                }
            }
        }

        if (totalMove == 0) {
            System.out.println("\nCongratulations. You won the game!!");
            completeGameMap();
            showingGameMap();
        }
    }
}
