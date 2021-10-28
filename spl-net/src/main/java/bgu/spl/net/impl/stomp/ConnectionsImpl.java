package bgu.spl.net.impl.stomp;

import bgu.spl.net.impl.stomp.Frames.outFrames.MessageGenerator;
import bgu.spl.net.srv.ConnectionHandler;
import bgu.spl.net.srv.Connections;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class ConnectionsImpl<T> implements Connections<T> {

    private ConcurrentHashMap<Integer, ConnectionHandler<T>> _alltheClients = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, ConcurrentLinkedQueue<Integer>> _channelSubscribers = new ConcurrentHashMap<>();
    private final AtomicInteger _id = new AtomicInteger(0);
    private final UsersSet _users = new UsersSet(); // todo : must check ! 1

    @Override
    public boolean send(int connectionId, T msg) {  // todo ; done !!
        try {
            ConnectionHandler<T> handler = _alltheClients.get(connectionId);
            if (handler != null)
                System.out.println("null Handler");
            synchronized (handler) {
                handler.send(msg);
            }
            return true;
        } catch (Exception e) {
            System.out.println("exception");
            return false;
        }
    }


    public int add(ConnectionHandler<T> newConnection) { // todo : done !
        try {
            int id = _id.getAndIncrement();
            _alltheClients.put(id, newConnection);
            return id;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public void link(int connectionId, String topic) { // subscribe
        _channelSubscribers.putIfAbsent(topic, new ConcurrentLinkedQueue<>());
        if (!_channelSubscribers.get(topic).contains(connectionId)) {
            _channelSubscribers.get(topic).add(connectionId);
        }
    }

    @Override
    public void unLink(int connectionId, String topic) {
        _channelSubscribers.get(topic).remove(connectionId);
    }

    @Override
    public void send(String topic, MessageGenerator<T> msgFactory) {
        try {
            for (int connectionId : _channelSubscribers.get(topic)) {
                send(connectionId, msgFactory.generate(connectionId));
            }
        } catch (Exception e) {
            System.out.println("There is no such channel !");
        }
    }

    @Override
    public void disconnect(int connectionId) {
        try {
            for (ConcurrentLinkedQueue<Integer> x : _channelSubscribers.values()) {
                x.remove(connectionId);
            }
            _alltheClients.get(connectionId).close(); // todo : not sure !!! ! ! ! !
            _alltheClients.remove(connectionId);

        } catch (Exception e) {
            System.out.println("The user in not exist ! ");
        }
    }

    public void disconnectAll() {
        for (Integer x : _alltheClients.keySet()) {
            try {
                _alltheClients.get(x).close();
            } catch (IOException e) {
            }
        }
        _alltheClients.clear();
    }

    public int getSubIdByConId(int connectionId, String topic) {
        return _users.getUserbyId(connectionId).getSubIdByTopic(topic);
    }

    public int size() {
        return _alltheClients.size();
    }

    public UsersSet getUsers() {
        return _users;
    }

    public ConcurrentHashMap<Integer, ConnectionHandler<T>> getAlltheClients() {
        return _alltheClients;
    }
}