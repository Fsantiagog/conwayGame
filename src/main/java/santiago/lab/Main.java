package santiago.lab;


public class Main {
    public static void main(String[] args) {
        ConwayGame game = new ConwayGame(50, 10);
        game.createBoard();
        game.startGame(50);

    }
}