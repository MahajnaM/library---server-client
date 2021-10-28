package bgu.spl.net.impl.stomp;

import java.util.HashSet;
import java.util.Set;

public class UsersSet { // activeusers
    private Set<User> _usersSet = new HashSet<>();

    public User getUserbyName(String name) {
        if (name == null)
            return null;
        for (User theUser : _usersSet) {
            if (theUser.getUserName().equals(name))
                return theUser;
        }
        return null;
    }

    public User getUserbyId(int id) {
        for (User theUser : _usersSet) {
            if (theUser.getConnectionId() == id)
                return theUser;
        }
        return null;
    }

    public boolean isUserExists(int id) {
        return getUserbyId(id) != null;
    }

    public boolean addUser(String name, String password) {
        if (getUserbyName(name) != null) {
            System.out.println("the nickname is already logged");
            return false;
        } else {
            User newUser = new User(name);
            newUser.setPassword(password);
            _usersSet.add(newUser);
            return true;
        }
    }

    public boolean addUserJustByNameAndNoPassword(String name) {
        return addUser(name, "");
    }

    public boolean removeUser(String name) {
        try {
            _usersSet.remove(name);
            return true;
        } catch (Exception e) {
            System.out.println("the user does not exists !");
            return false;
        }
    }

    public Set<User> getUsersByTopic(String topic) {
        Set<User> result = new HashSet<>();
        _usersSet.forEach(user -> {
            if (user.isSubscribed(topic))
                result.add(user);
        });
        return result;
    }

    public int howManyUsers() {
        return _usersSet.size();
    }
}
