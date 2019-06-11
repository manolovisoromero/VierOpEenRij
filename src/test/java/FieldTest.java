import Game.Field;
import Game.Game;
import Game.Player;
import Game.Coin;
import Websocketserver.ServerLogic;
import javafx.scene.paint.Color;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.ArrayList;

class FieldTest {

    //I will not test adding 2 coins to one spot. It is not possible because the methods leading to the addCoin method won't allow that, so that situation can't ever be reached.

    public Game game;
    public Player player1,player2;
    public Field field;

    @BeforeEach
    void setUp(){
        //Fresh Game.Field instance with mock game parameter
        //////player1 = new Player("1");
        //player2 = new Player("2");
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        game = new Game(players, ServerLogic.getInstance(),1);
        field = new Field(game);
    }


    @Test
    void testTemplate(){
        //Arrange: Mock data

        //Act: Invoke test method

        //Assert the expectations
    }


    @Test
    void CoinAddsToRightPlace() {
        //Arrange
        boolean expected = true;
        Point p = new Point(1,1);
        Coin c = new Coin(player1, Color.RED,p);

        //Act
        boolean actual;
        field.addCoin(c);
        if(field.getField()[p.x][p.y] == c){
            actual = true;
        }else{
            actual = false;
        }

        //Assert
        Assert.assertEquals(expected,actual);

    }
}
