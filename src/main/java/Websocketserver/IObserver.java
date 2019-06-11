package Websocketserver;

import java.io.IOException;

public interface IObserver {

    void update(Object o) throws IOException;

}

