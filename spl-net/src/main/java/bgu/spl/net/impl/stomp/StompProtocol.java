package bgu.spl.net.impl.stomp;

import bgu.spl.net.api.StompMessagingProtocol;
import bgu.spl.net.impl.stomp.Frames.FrameFormat;
import bgu.spl.net.impl.stomp.Frames.outFrames.ERROR;
import bgu.spl.net.srv.Connections;

public class StompProtocol implements StompMessagingProtocol<FrameFormat> {
    private int _id;
    private ConnectionsImpl<FrameFormat> _connections = null;
    private UsersSet _usersSet;
    private boolean terminate = false;

    @Override
    public void start(int connectionId, Connections<FrameFormat> connections) {
        _connections = (ConnectionsImpl<FrameFormat>) connections;
        _id = connectionId;
        _usersSet = _connections.getUsers();
    }

    @Override
    public void process(FrameFormat message) {
        if (message == null) {
            System.out.println("Error 54564");
            _connections.send(_id, new ERROR());
            return;
        }
        message.work(this);
    }

    @Override
    public boolean shouldTerminate() {
        return terminate;
    }

    public int get_id() {
        return _id;
    }

    public ConnectionsImpl<FrameFormat> getConnections() {
        return _connections;
    }

    public UsersSet getUsersSet() {
        return _usersSet;
    }

    public void terminate() {
        terminate = true;
    }
}
