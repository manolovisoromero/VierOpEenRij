package game;

import websocketServer.IObserver;

import java.io.IOException;

public interface ISubject {

    void attach(IObserver o);
    void detach(IObserver o);
    void Notify() throws IOException;
}
