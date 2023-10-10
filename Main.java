import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Bienvenido al juego de 3 en raya!");
        System.out.println("Instrucciones: Ingresa la fila y la columna (0-2) separadas por espacio.");
        System.out.print("¿Quíen inicia el juego? Jugador o Maquina(j/m): ");
        char rpta = scanner.next().charAt(0);
        if(rpta=='m')
        	game.setCurrentPlayer('O');
        while (!game.isGameOver()) {
            game.printBoard();

            if (game.getCurrentPlayer() == 'X') {
                System.out.print("Turno del jugador (X): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (game.makeMove(row, col)) {
                    if (game.hasPlayerWon('X')) {
                        game.printBoard();
                        System.out.println("¡Felicidades! Jugador (X) gana.");
                        break;
                    }
                } else {
                    System.out.println("Movimiento inválido. Intenta de nuevo.");
                }
            } else {
                System.out.println("Turno de la máquina (O): ");

                int[] bestMove = Minimax.findBestMove(game);
                game.makeMove(bestMove[0], bestMove[1]);

                if (game.hasPlayerWon('O')) {
                    game.printBoard();
                    System.out.println("¡La máquina (O) gana!");
                    break;
                }
            }
        }

        if (!game.hasPlayerWon('X') && !game.hasPlayerWon('O')) {
            game.printBoard();
            System.out.println("¡Es un empate!");
        }

        scanner.close();
	}

}
