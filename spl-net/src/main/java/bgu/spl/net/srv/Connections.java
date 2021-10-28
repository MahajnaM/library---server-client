package bgu.spl.net.srv;

import bgu.spl.net.impl.stomp.Frames.outFrames.MessageGenerator;
import bgu.spl.net.impl.stomp.UsersSet;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public interface
Connections<T> {

    boolean send(int connectionId, T msg);

    void send(String topic, MessageGenerator<T> msgFactory) ;

    void disconnect(int connectionId);

    int add(ConnectionHandler<T> newConnection);

    void link(int connectionId, String topic);

    void unLink(int connectionId, String topic);

    void disconnectAll();

    UsersSet getUsers();

    ConcurrentHashMap<Integer, ConnectionHandler<T>> getAlltheClients();
}
