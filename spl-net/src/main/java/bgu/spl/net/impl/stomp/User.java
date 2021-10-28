package bgu.spl.net.impl.stomp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class User {
    private String _name;
    private int _id = -1;
    private String _password = "";
    private ConcurrentHashMap<Integer, String> _subscribedMap = new ConcurrentHashMap<>();

    public User(String name) {
        _name = name;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public String getUserName() {
        return _name;
    }

    public int getConnectionId() {
        return _id;
    }


    public String getUserPassword() {
        return _password;
    }

    public ConcurrentHashMap<Integer, String> getSubscribedTopics() {
        return _subscribedMap;
    }

    public boolean isLoggedIn() {
        return _id != -1;
    }

    public void logOut() {
        _id = -1;
    }

    public void logIn(int id) {
        _id = id;
    }

    public boolean subscribeTopic(int i, String topic) {
        if (!_subscribedMap.containsValue(topic)) {
            _subscribedMap.put(i, topic);
            return true;
        } else
            System.out.println("you already subscribe this topic");
        return false;
    }

    public int getSubIdByTopic(String topic) {
        for (Map.Entry<Integer, String> entry : _subscribedMap.entrySet()) {
            Integer subId = entry.getKey();
            String name = entry.getValue();
            if (name.equals(topic))
                return subId;
        }
        return -1;
    }

    public void discconect() {
        _subscribedMap.clear();
    }

    public String unSubscribeTopic(int _id) {
        String topicIs;
        if (_subscribedMap.containsKey(_id)) {
            topicIs = _subscribedMap.get(_id);
            _subscribedMap.remove(_id);
            return topicIs;
        } else
            System.out.println("did not found any subscription topic like this :((");
        return null;
    }

    public boolean isSubscribed(String topic) {
        return _subscribedMap.containsValue(topic);
    }
}

