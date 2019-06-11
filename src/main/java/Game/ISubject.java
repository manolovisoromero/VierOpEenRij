package Game;

import Websocketserver.IObserver;

import java.io.IOException;

public interface ISubject {

    void Attach(IObserver o);
    void Detach(IObserver o);
    void Notify() throws IOException;
}
