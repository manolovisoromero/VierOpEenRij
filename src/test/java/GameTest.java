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
        boolean actual = game.notNull(c1,-3,3,true);

        //Assert
        Assert.assertEquals(expected,actual);
    }



    @Test
    void hotizontalWin_4SameInRow_ShouldBeTrue_True() throws IOException {
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
    void hotizontalWin_4NotSameInRow_ShouldBeFalse_True() throws IOException {
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
    void verticalWin_4SameInRow_ShouldBeTrue_True() throws IOException {
        //Arrange
        boolean expected = true;
        Field field = game.getField();
        Coin c1 = new Coin(player2,game.decideColor(player1),new Point(3,2));
        Coin c2 = new Coin(player2,game.decideColor(player1),new Point(3,3));
        Coin c3 = new Coin(player2,game.decideColor(player1),new Point(3,4));
        Coin c4 = new Coin(player2,game.decideColor(player1),new Point(3,5));
        field.addCoin(c1);
        field.addCoin(c2);
        field.addCoin(c3);
        field.addCoin(c4);

        //Act
        boolean actual = false;
        game.lastPlayed = c1;
        game.lastPlayed.player.setPlayernr(1);
        game.verticalWin(c4);
        if(game.win){
            actual = true;
        }

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void verticalWin_4NotSameInRow_ShouldBeFalse_True() throws IOException {
        //Arrange
        boolean expected = false;
        Field field = game.getField();
        Coin c1 = new Coin(player1,game.decideColor(player1),new Point(0,0));
        Coin c2 = new Coin(player1,game.decideColor(player1),new Point(0,1));
        Coin c3 = new Coin(player2,game.decideColor(player1),new Point(0,2));
        Coin c4 = new Coin(player1,game.decideColor(player1),new Point(0,3));
        field.addCoin(c1);
        field.addCoin(c2);
        field.addCoin(c3);
        field.addCoin(c4);

        //Act
        boolean actual = false;
        game.verticalWin(c1);
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
    void horiOOB_1IsOutOfBoundsOnFirstIter_ShouldBeTrue_True(){
        //Arrange
        boolean expected = true;

        //Act
        boolean actual = game.horiOutOfBounds(1,0);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void horiOOB_1IsNotOutOfBoundsOnFourthIter_ShouldBeFalse_True(){
        //Arrange
        boolean expected = false;

        //Act
        boolean actual = game.horiOutOfBounds(1,3);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void horiOOB_6IsNotOutOfBoundsOnFirstIter_ShouldBeFalse_True(){
        //Arrange
        boolean expected = false;

        //Act
        boolean actual = game.horiOutOfBounds(6,0);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void horiOOB_IsOutOfBoundsOnSecondIter_ShouldBeTrue_True() {
        //Arrange
        boolean expected = true;

        //Act
        boolean actual = game.horiOutOfBounds(6, 1);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    //Vertical win check

    @Test
    void vertOOB_1IsOutOfBoundsOnFirstIter_ShouldBeTrue_True(){
        //Arrange
        boolean expected = true;

        //Act
        boolean actual = game.vertOutOfBounds(1,0);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void vertOOB_1IsNotOutOfBoundsOnFourthIter_ShouldBeFalse_True(){
        //Arrange
        boolean expected = false;

        //Act
        boolean actual = game.vertOutOfBounds(1,3);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void vertOOB_5IsNotOutOfBoundsOnFirstIter_ShouldBeFalse_True(){
        //Arrange
        boolean expected = false;

        //Act
        boolean actual = game.vertOutOfBounds(5,0);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void vertOOB_5IsOutOfBoundsOnSecondIter_ShouldBeTrue_True() {
        //Arrange
        boolean expected = true;

        //Act
        boolean actual = game.vertOutOfBounds(5, 1);

        //Assert
        Assert.assertEquals(expected,actual);
    }


    @Test
    void decideColor_FirstGetsRed_ShouldBeTrue_True() {
        //Arrange
        boolean expected = true;

        //Act
        boolean actual;
        actual = game.decideColor(player1) == Color.RED;

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void decideColor_SecondGetsYellow_ShouldBeTrue_True() {
        //Arrange
        boolean expected = true;

        //Act
        boolean actual;
        actual = game.decideColor(player2) == Color.YELLOW;

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void whereToDrop_CoinDropsFrom6ToLowestAvailable5_ShouldBeTrue_True() {
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
    void whereToDrop_CoinDropsTo2LowestAvailable1_ShouldBeTrue_True() {
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
    void available_NoSpot_ShouldBeFalse_True() {
        //Arrange
        game.getField().addCoin(new Coin(player1, Color.RED,new Point(1,1)));
        boolean expected = false;

        //Act
        boolean actual = game.Available(1);

        //Assert
        Assert.assertEquals(expected,actual);
    }



    @Test
    void available_FreeSpot_ShouldBeTrue_True() {
        //Arrange
        int x = 1;
        boolean expected = true;

        //Act
        boolean actual = game.Available(x);

        //Assert
        Assert.assertEquals(expected,actual);
    }
}
