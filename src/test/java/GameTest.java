import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    public Game game;
    public Player player1,player2;

    @BeforeEach
    void setUp(){
        //Fresh game instance with mock players.
        player1 = new Player("1");
        player2 = new Player("2");
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        game = new Game(players);

    }


    @Test
    void testTemplate(){
        //Arrange: Mock data

        //Act: Invoke test method

        //Assert the expectations
    }

    @Test
    void PlacedCoins4ShouldGiveTrue(){
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
        boolean actual = game.notNull(c1,-3,3);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void player(){
        boolean expected = false;
        boolean actual;
        if(player1 == player2){actual = true;
            System.out.println("nee");}else{
            actual = false;
        }

    }


    @Test
    void FourSameCoinsHorizontalShouldWin(){
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
        game.HorizontalWin(c1);
        if(game.win){
            actual = true;
        }

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void FourNotSameCoinsHorizontalShouldntWin(){
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
        game.HorizontalWin(c1);
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
        boolean actual = game.HoriOutOfBounds(1,0);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void HoriPlace1FourthForLoopIterShouldntBeOOB(){
        //Arrange
        boolean expected = false;

        //Act
        boolean actual = game.HoriOutOfBounds(1,3);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void HoriPlace6FirstForLoopIterShouldntBeOOB(){
        //Arrange
        boolean expected = false;

        //Act
        boolean actual = game.HoriOutOfBounds(6,0);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void HoriPlace6SecondForLoopIterShouldBeOOB() {
        //Arrange
        boolean expected = true;

        //Act
        boolean actual = game.HoriOutOfBounds(6, 1);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    //Vertical win check

    @Test
    void VertPlace1FirstForLoopIterShouldBeOOB(){
        //Arrange
        boolean expected = true;

        //Act
        boolean actual = game.VertOutOfBounds(1,0);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void VertPlace1FourthForLoopIterShouldntBeOOB(){
        //Arrange
        boolean expected = false;

        //Act
        boolean actual = game.VertOutOfBounds(1,3);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void VertPlace7FirstForLoopIterShouldntBeOOB(){
        //Arrange
        boolean expected = false;

        //Act
        boolean actual = game.VertOutOfBounds(7,0);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void VertPlace7SecondForLoopIterShouldBeOOB() {
        //Arrange
        boolean expected = true;

        //Act
        boolean actual = game.VertOutOfBounds(7, 1);

        //Assert
        Assert.assertEquals(expected,actual);
    }


    @Test
    void FirstPlayerGetsRed() {
        //Arrange
        boolean expected = true;

        //Act
        boolean actual;
        if(game.decideColor(player1) == Color.RED){
            actual = true;
        }else{ actual = false;}

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void SecondPlayerGetsYellow() {
        //Arrange
        boolean expected = true;

        //Act
        boolean actual;
        if(game.decideColor(player2) == Color.YELLOW){
            actual = true;
        }else{ actual = false;}

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void CoinIn6ShouldReturn5() {
        //Arrange
        int x = 1;
        Point expected = new Point(x,5);
        game.getField().addCoin(new Coin(player1,Color.RED,new Point(x,6)));

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
        game.getField().addCoin(new Coin(player1,Color.RED,new Point(x,2)));

        //Act
        Point actual = game.whereToDrop(x);

        //Assert
        Assert.assertEquals(expected,actual);
    }

    @Test
    void ShouldNotBeAvailable() {
        //Arrange
        game.getField().addCoin(new Coin(player1,Color.RED,new Point(1,1)));
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
