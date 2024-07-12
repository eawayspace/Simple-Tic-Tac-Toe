package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static char[][] setInitialStateFieldGrid() {
        char[][] gridField = new char[3][3];

        for (int i = 0; i < gridField.length; i++) {
            for (int j = 0; j < gridField[i].length; j++) {
                gridField[i][j] = ' ';
            }
        }

        return gridField;
    }

    static char[][] setTheGameMove(char[][] gridField, int coordinateX, int coordinateY, char player) {
        gridField[coordinateX - 1][coordinateY - 1] = player;
        return gridField;
    }

    static void printDashes(char[][] gridField) {
        for (int d = 0; d < gridField.length * gridField.length; d++) {
            System.out.print("-");
            if (d == gridField.length * gridField.length - 1) {
                System.out.println();
            }
        }
    }

    static void printFieldGrid(char[][] gridField) {

        printDashes(gridField);

        for (int i = 0; i < gridField.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < gridField[i].length; j++) {
                System.out.print(gridField[i][j] + " ");
            }
            System.out.print("|\n");
        }

        printDashes(gridField);

    }

    static boolean checkAndPrintResult(char[][] gridField) {
        int countX = 0;
        int countO = 0;
        int countEmpty = 0;
        boolean xWins = false;
        boolean oWins = false;
        boolean impossible = false;
        boolean movesAreOver = false;

        for (int i = 0; i < gridField.length; i++) {
            for (int j = 0; j < gridField[i].length; j++) {
                if (gridField[i][j] == 'X') {
                    countX++;
                } else if (gridField[i][j] == 'O') {
                    countO++;
                } else if (gridField[i][j] == ' ') {
                    countEmpty++;
                }
            }
        }

        // Checking rows
        for (int i = 0; i < gridField.length; i++) {
            int countTempX = 0;
            int countTempO = 0;
            for (int j = 0; j < gridField[i].length; j++) {
                if (gridField[i][j] == 'X') {
                    countTempX++;
                    if (countTempX == gridField[i].length) {
                        xWins = true;
                    }
                }
                if (gridField[i][j] == 'O') {
                    countTempO++;
                    if (countTempO == gridField[i].length) {
                        oWins = true;
                    }
                }
            }
        }

        // Checking columns
        for (int i = 0; i < gridField.length; i++) {
            int countTempX = 0;
            int countTempO = 0;
            for (int j = 0; j < gridField[i].length; j++) {
                if (gridField[j][i] == 'X') {
                    countTempX++;
                    if (countTempX == gridField[i].length) {
                        xWins = true;
                    }
                }
                if (gridField[j][i] == 'O') {
                    countTempO++;
                    if (countTempO == gridField[i].length) {
                        oWins = true;
                    }
                }
            }
        }

        // Checking by diagonals
        for (int i = 0; i < gridField.length; i++) {
            int countTempX = 0;
            int countTempO = 0;
            for (int j = 0; j < gridField[i].length; j++) {
                if (gridField[j][j] == 'X') {
                    countTempX++;
                    if (countTempX == gridField[i].length) {
                        xWins = true;
                    }
                }
                if (gridField[j][j] == 'O') {
                    countTempO++;
                    if (countTempO == gridField[i].length) {
                        oWins = true;
                    }
                }
            }
        }

        for (int i = 0; i < gridField.length; i++) {
            int countTempX = 0;
            int countTempO = 0;
            for (int j = 0; j < gridField[i].length; j++) {
                if (gridField[j][(gridField[i].length - 1) - j] == 'X') {
                    countTempX++;
                    if (countTempX == gridField[i].length) {
                        xWins = true;
                    }
                }
                if (gridField[j][(gridField[i].length - 1) - j] == 'O') {
                    countTempO++;
                    if (countTempO == gridField[i].length) {
                        oWins = true;
                    }
                }

            }
        }

        int differenceXO = Math.abs(countX - countO);

        if (xWins == true && oWins == false) {
            System.out.println("X Wins! End of the game.");
        } else if (oWins == true && xWins == false) {
            System.out.println("O Wins! End of the game.");
        } else if (xWins == true && oWins == true || differenceXO > 1) {
            System.out.println("Impossible... Game over.");
            impossible = true;
        } else if (xWins == false && oWins == false && differenceXO != 0 && countEmpty == 0) {
            System.out.println("Draw! End of the game.");
            movesAreOver = true;
        }

        return xWins || oWins || impossible || movesAreOver;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] initialStateFieldGrid = setInitialStateFieldGrid();
        printFieldGrid(initialStateFieldGrid);
        int moveCounter = 0;
        boolean validation = false;

        while (!validation) {
            try {
                char player = moveCounter % 2 == 0 ? 'X' : 'O';
                System.out.print(player + " : ");
                int coordinateX = scanner.nextInt();
                int coordinateY = scanner.nextInt();
                if (coordinateX < 1 || coordinateX > 3 || coordinateY < 1 || coordinateY > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    scanner.nextLine();
                } else {
                    if (initialStateFieldGrid[coordinateX - 1][coordinateY - 1] == ' ') {
                        char[][] fieldGrid = setTheGameMove(initialStateFieldGrid, coordinateX, coordinateY, player);
                        moveCounter++;
                        printFieldGrid(fieldGrid);
                        validation = checkAndPrintResult(fieldGrid);
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        scanner.nextLine();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }
    }
}
