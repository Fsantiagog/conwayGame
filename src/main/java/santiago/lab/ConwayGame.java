package santiago.lab;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ConwayGame extends JPanel {
    private int boardSize;
    private int cellSize;
    private boolean[][] board;
    private boolean[][] nextBoard;
    AtomicInteger iterationCount = new AtomicInteger(0);
    public ConwayGame(int boardSize, int cellSize) {
        this.boardSize = boardSize;
        this.cellSize = cellSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j]) {
                    g.setColor(Color.BLACK); // Célula viva
                } else {
                    g.setColor(Color.WHITE); // Célula muerta
                }
                g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY); // Dibujar líneas de la cuadrícula
                g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
                g.drawString("iteraciones : " + iterationCount.get(), 0, 500);
            }
        }
    }

    public void createBoard() {
        //graficos
        JFrame frame = new JFrame("Conway's Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(boardSize * cellSize +20, boardSize * cellSize +40);
        frame.setVisible(true);
        //tableros
        board = new boolean[boardSize][boardSize];
        nextBoard = new boolean[boardSize][boardSize];
        inciarTablero();
    }

    /**
     * este metodo solo sirve para iniciar las posiciones de las celdas vivas de manera aleatoria
     */
    public void inciarTablero(){
        Random random = new Random();
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                board[x][y] = random.nextBoolean();
            }
        }
    }

    public void startGame(int times) {
        Timer timer = new Timer(times, e -> {
            if (iterationCount.incrementAndGet() >= times) {//mi condicion de paro
                ((Timer) e.getSource()).stop();
            }
            actualizarTablero();
            repaint(); // Redibujar el tablero
        });
        timer.start();
    }

    /**
     * aqui dibujamos una matriz imaginaria con la celda en el centro
     * |0|1|0|
     * |1|X|1|
     * |0|1|0|
     * donde X (x,y) es la celda que estamos contando
     * y los 0 y 1 son celdas vivas o muertas, usaremos 1 para vivas y 0 para muertas
     *
     * @param x
     * @param y
     * @return
     */
    public int contarVecinasVivas(int x, int y) {
        int vecinasVivas = 0;
        //recorreremos nuestra matriz imaginaria empenzando por el -1, luego el 0 y posteriormente el 1 para las columnas 'i'
        //y lo mismo para las filas 'j'
        //aprovecharemos dichos enteros para sumarlos a las coordenadas de la celda que estamos contando, siendo el valor i= 0 y j = 0 la posision de la celda a evaluar
        //dicha celda seria ignorada
        //-1, 0 y 1
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int xVecina = x + i;
                int yVecina = y + j;
                //if (xVecina >= 0 && xVecina < boardSize && yVecina >= 0 && yVecina < boardSize) {
                //esto nos ayuda a encontrar la vecindad de la celda pero hay que incluir el tablero
                //la operacion es sencilla solomanete sumamos el tamaño del tablero y se divide
                // entre el tamaño del  mismo tablero (quendandonos con la parte entera decimal!)
                //si en esas posiciones hay una celda viva, incrementamos el contador
                if (board[(xVecina + boardSize) % boardSize][(yVecina + boardSize) % boardSize]) {
                    vecinasVivas++;
                }
            }

        }
        return vecinasVivas;
    }

    /**
     * este metodo se encarga de actualizar el tablero,
     * recorriendo este y validando las reglas del juego de conway
     * 1.-<>Superviviencia</>
     * si la celda esta viva y cuenta con 2 o 3 vecinas vivas, se mantiene viva
     * 2.- <>Muerte por soledad y depresion :(</>
     * si la celda esta viva y cuenta con menos de 2 vecinas vivas entonces muere
     * 3.- <>Muerte por sobrepoblacion</>
     * si la celda esta viva y cuenta con mas de 3 vecinas vivas entonces muere
     * 4.- <>Nacimientos :)</>
     * si la celda esta muerta y cuenta con exactamente 3 vecinas vivas entonces nace
     *
     */
    public void actualizarTablero() {
        for (int x = 0; x < boardSize; x++){
            for (int y = 0; y < boardSize; y++){
                int vecinasVivas = contarVecinasVivas(x, y);
                // Aplicar las reglas
                //si la celda esta viva
                if (board[x][y]) {
                    //si cuenta con 2 o 3 vecinas vivas se mantiene viva
                    nextBoard[x][y] = (vecinasVivas == 2 || vecinasVivas == 3);//de lo contrario muere (soledad || sobrepoblacion)
                } else {//si la celda esta muerta
                    //si cuenta con exactamente 3 vecinas vivas nace
                    nextBoard[x][y] = (vecinasVivas == 3);
                }
            }
        }
        // Copiar el siguiente tablero al actual
        boolean[][] bkup = board;
        board = nextBoard;
        nextBoard = bkup;
    }
}
