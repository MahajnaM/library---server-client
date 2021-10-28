package bgu.spl.net.impl.echo;

import bgu.spl.net.api.StompMessagingProtocol;
import bgu.spl.net.impl.stomp.ConnectionsImpl;
import bgu.spl.net.srv.Connections;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EchoProtocol implements StompMessagingProtocol<String> {

    private boolean shouldTerminate = false;
    private int id;
    private ConnectionsImpl<String> connections;

    @Override
    public void start(int connectionId, Connections<String> connections) {
        this.id = connectionId;
        this.connections = (ConnectionsImpl<String>) connections;
        System.out.println("id: " + id + " connected succesfully");
    }

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void process(String msg) {
        shouldTerminate = "bye".equals(msg);
        String answer = createEcho(msg);
        System.out.println("[" + LocalDateTime.now() + "] got: " + msg + " size: " + connections.size());
        connections.send(id, answer);
        if (shouldTerminate) connections.disconnect(id);
    }

    private String createEcho(String message) {
        String echoPart = message.substring(Math.max(message.length() - 2, 0));
        return message + " .. " + echoPart + " .. " + echoPart + " ..";
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }

}
