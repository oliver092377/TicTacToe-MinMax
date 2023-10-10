
public class Minimax {
    public static int[] findBestMove(TicTacToe game) {
        int[] bestMove = {-1, -1};
        int bestScore = Integer.MIN_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (game.makeMove(row, col)) {
                    int score = minimax(game, 0, false);
                    game.undoMove(row, col);

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }

        return bestMove;
    }

    private static int minimax(TicTacToe game, int depth, boolean isMaximizing) {
        // Caso base: Verificar si el juego ha terminado
        if (game.isGameOver()) {
            if (game.hasPlayerWon('X')) {
                return -1; // El jugador 'X' ha ganado, puntuación mínima
            } else if (game.hasPlayerWon('O')) {
                return 1;  // El jugador 'O' ha ganado, puntuación máxima
            } else {
                return 0;  // Empate, puntuación neutral
            }
        }

        if (isMaximizing) {
            // Es el turno de maximizar (máquina 'O')
            int bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (game.makeMove(row, col)) {
                        int score = minimax(game, depth + 1, false);
                        game.undoMove(row, col);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            // Es el turno de minimizar (jugador humano 'X')
            int bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (game.makeMove(row, col)) {
                        int score = minimax(game, depth + 1, true);
                        game.undoMove(row, col);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
    
}