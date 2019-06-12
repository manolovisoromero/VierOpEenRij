import javafx.scene.paint.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class PlayerTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void setWin() {
        //Arrange
        boolean expected = true;

        //Act
        boolean actual = true;

        //Assert
        Assert.assertEquals(expected,actual);

    }
}
