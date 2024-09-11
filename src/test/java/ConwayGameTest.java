import org.junit.Test;
import santiago.lab.ConwayGame;

import static org.junit.Assert.*;

public class ConwayGameTest {

    @Test
    public void canCreateGame() {
        ConwayGame game = new ConwayGame(100, 10);
        assertNotNull(game);
    }

    @Test
    public void canSeeBoard(){
        ConwayGame game = new ConwayGame(50, 10);
        assertNotNull(game);
        game.createBoard();
        assertTrue(game.isShowing());
    }

    @Test
    public void canSeeBoardOver100Times(){
        ConwayGame game = new ConwayGame(50, 10);
        assertNotNull(game);
        game.createBoard();
        game.startGame(100);
        assertTrue(game.isShowing());
    }

    @Test
    public void canCountLiveNeighbors(){
        ConwayGame game = new ConwayGame(50, 10);
        assertNotNull(game);
        game.createBoard();
        game.startGame(100);
        assertTrue(game.isShowing());
        int vecinasVivas = game.contarVecinasVivas(1, 1);
        assertTrue(vecinasVivas >= 0);
    }

    @Test
    public void canUpdateBoard(){
        ConwayGame game = new ConwayGame(50, 10);
        assertNotNull(game);
        game.createBoard();
        game.startGame(100);
        assertTrue(game.isShowing());
    }
}
