package bgu.spl.net.impl.rci;

import bgu.spl.net.api.StompMessagingProtocol;
import bgu.spl.net.srv.Connections;

import java.io.Serializable;

public class RemoteCommandInvocationProtocol<T> implements StompMessagingProtocol<Serializable> {
    Connections<Serializable> _connections;
    int _id;
    private T _arg;

    public RemoteCommandInvocationProtocol(T arg) {
        _arg = arg;

    }

    @Override
    public void start(int connectionId, Connections<Serializable> connections) {
        _connections = connections;
        _id = connectionId;
    }

    public void process(Serializable msg) {
       // Serializable _serializable = ((Command) msg).execute(_arg);
        _connections.send(_id, ((Command) msg).execute(_arg));
    }

    @Override
    public boolean shouldTerminate() {
        return false;
    }

}
