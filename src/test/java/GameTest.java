import game.Coin;
import game.Field;
import game.Game;
import game.Player;
import websocketServer.ServerLogic;
import javafx.scene.paint.Color;
import websocketServer.Connection;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


class GameTest {

    private Game game;
    private Player player1,player2;

    @BeforeEach
    void setUp(){
        //Fresh game instance with mock players.
        Connection connection = new Connection(null,null);
        player1 = new Player("Kees",connection,1);
        player2 = new Player("Sjaak",connection,2);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        game = new Game(players, ServerLogic.getInstance(),1);

    }


    @Test
    void testTemplate(){
        //Arrange: Mock data

        //Act: Invoke test method

        //Assert the expectations
    }

    @Test
    void notNull_NoNullsPresent_ShouldBeTrue_True(){
        //Arrange
        boolean expected = true;
        Field field = game.getField();
        Coin c1 = new Coin(player1,game.decideColor(player1),new Point(0,0));
        Coin c2 = new Coin(player1,game.decideColor(player1),new Point(1,0));
        Coin c3 = new Coin(player1,game.decideColor(player1),new Point(2,0));
        Coin c4 = new Coin(player1,game.decideColor(player1),new Point(3,0));
        field.addCoin(c1);
        field.addCoin(c2);
        field.addCoin(c3);
        field.addCoin(c4);

        //Act
        boolean actual = game.notNull(c1,-3,3,false);

        //Assert
        Assert.assertEquals(expected,actual);
    }



    @Test
    void FourSameCoinsHorizontalShouldWin() throws IOException {
        //Arrange
        boolean expected = true;
        Field field = game.getField();
        Coin c1 = new Coin(player1,game.decideColor(player1),new Point(0,0));
        Coin c2 = new Coin(player1,game.decideColor(player1),new Point(1,0));
        Coin c3 = new Coin(player1,game.decideColor(player1),new Point(2,0));
        Coin c4 = new Coin(player1,game.decideColor(player1),new Point(3,0));
        field.addCoin(c1);
        field.addCoin(c2);
        field.addCoin(c3);
        field.addCoin(c4);

        //Act
        boolean actual = false;
        game.lastPlayed = c1;
        game.lastPlayed.player.setPlayernr(1);
        game.horizontalWin(c1);
        if(game.win){
            actual = true;
        }

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void FourNotSameCoinsHorizontalShouldntWin() throws IOException {
        //Arrange
        boolean expected = false;
        Field field = game.getField();
        Coin c1 = new Coin(player1,game.decideColor(player1),new Point(0,0));
        Coin c2 = new Coin(player1,game.decideColor(player1),new Point(1,0));
        Coin c3 = new Coin(player2,game.decideColor(player1),new Point(2,0));
        Coin c4 = new Coin(player1,game.decideColor(player1),new Point(3,0));
        field.addCoin(c1);
        field.addCoin(c2);
        field.addCoin(c3);
        field.addCoin(c4);

        //Act
        boolean actual = false;
        game.horizontalWin(c1);
        if(game.win){
            actual = true;
        }

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void playCoin() {
    }

    //Horizontal win check

    @Test
    void HoriPlace1FirstForLoopIterShouldBeOOB(){
        //Arrange
        boolean expected = true;

        //Act
        boolean actual = game.horiOutOfBounds(1,0);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void HoriPlace1FourthForLoopIterShouldntBeOOB(){
        //Arrange
        boolean expected = false;

        //Act
        boolean actual = game.horiOutOfBounds(1,3);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void HoriPlace6FirstForLoopIterShouldntBeOOB(){
        //Arrange
        boolean expected = false;

        //Act
        boolean actual = game.horiOutOfBounds(6,0);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void HoriPlace6SecondForLoopIterShouldBeOOB() {
        //Arrange
        boolean expected = true;

        //Act
        boolean actual = game.horiOutOfBounds(6, 1);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    //Vertical win check

    @Test
    void VertPlace1FirstForLoopIterShouldBeOOB(){
        //Arrange
        boolean expected = true;

        //Act
        boolean actual = game.vertOutOfBounds(1,0);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void VertPlace1FourthForLoopIterShouldntBeOOB(){
        //Arrange
        boolean expected = false;

        //Act
        boolean actual = game.vertOutOfBounds(1,3);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void VertPlace7FirstForLoopIterShouldntBeOOB(){
        //Arrange
        boolean expected = false;

        //Act
        boolean actual = game.vertOutOfBounds(7,0);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void VertPlace7SecondForLoopIterShouldBeOOB() {
        //Arrange
        boolean expected = true;

        //Act
        boolean actual = game.vertOutOfBounds(7, 1);

        //Assert
        Assert.assertEquals(expected,actual);
    }


    @Test
    void FirstPlayerGetsRed() {
        //Arrange
        boolean expected = true;

        //Act
        boolean actual;
        actual = game.decideColor(player1) == Color.RED;

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void SecondPlayerGetsYellow() {
        //Arrange
        boolean expected = true;

        //Act
        boolean actual;
        actual = game.decideColor(player2) == Color.YELLOW;

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void CoinIn6ShouldReturn5() {
        //Arrange
        int x = 1;
        Point expected = new Point(x,5);
        game.getField().addCoin(new Coin(player1, Color.RED,new Point(x,6)));

        //Act
        Point actual = game.whereToDrop(x);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void CoinIn2ShouldReturn1() {
        //Arrange
        int x = 1;
        Point expected = new Point(x,1);
        game.getField().addCoin(new Coin(player1, Color.RED,new Point(x,2)));

        //Act
        Point actual = game.whereToDrop(x);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void ShouldNotBeAvailable() {
        //Arrange
        game.getField().addCoin(new Coin(player1, Color.RED,new Point(1,1)));
        boolean expected = false;

        //Act
        boolean actual = game.Available(1);

        //Assert
        Assert.assertEquals(expected,actual);
    }



    @Test
    void ShouldBeAvailable() {
        //Arrange
        int x = 1;
        boolean expected = true;

        //Act
        boolean actual = game.Available(x);

        //Assert
        Assert.assertEquals(expected,actual);
    }
}
