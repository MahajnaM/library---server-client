package bgu.spl.net.impl.stomp.Frames.inFrames;

import bgu.spl.net.impl.stomp.Frames.FrameFormat;
import bgu.spl.net.impl.stomp.Frames.outFrames.CONNECTED;
import bgu.spl.net.impl.stomp.Frames.outFrames.ERROR;
import bgu.spl.net.impl.stomp.StompProtocol;
import bgu.spl.net.impl.stomp.User;
import bgu.spl.net.impl.stomp.UsersSet;
import bgu.spl.net.srv.Connections;

public class CONNECT extends FrameFormat {
    @Override

    public void work(StompProtocol userProtocol) {
        int _id = userProtocol.get_id();
        UsersSet allUsers = userProtocol.getUsersSet();
        Connections<FrameFormat> theConnections = userProtocol.getConnections();
//----------------------------------------------------------------------------------------------------------------------
        try {
            FrameFormat finalResult = new ERROR();
            finalResult.addToHeader("ERROR", "");
            String userName = get("login");
            String userPassword = get("passcode");
            User theUser;
            if (allUsers.addUser(userName, userPassword)) {
                theUser = allUsers.getUserbyName(userName);
                theUser.logIn(_id);
                finalResult = new CONNECTED();
                finalResult.addToHeader("CONNECTED", "");
                finalResult.addToHeader("version", get("accept-version"));
            } else {
                if (!allUsers.getUserbyId(_id).isLoggedIn()) {
                    finalResult.addToHeader("ERROR", "");
                    finalResult.addToHeader("message", "failed in logged in");
                    finalResult.addToHeader("body", "the user is already logged in");
                } else {
                    if (!allUsers.getUserbyName(userName).getUserPassword().equals(userPassword)) {
                        finalResult.addToHeader("ERROR", "");
                        finalResult.addToHeader("message", "failed in logged in");
                        finalResult.addToHeader("body", "“Wrong password");
                    } else {
                        if (!theConnections.getAlltheClients().get(_id).isClosed()) {
                            finalResult.addToHeader("ERROR", "");
                            finalResult.addToHeader("message", "failed in logged in");
                            finalResult.addToHeader("body", "the connection is active"); //
                            theUser = allUsers.getUserbyName(userName);
                            theUser.logIn(_id);
                            finalResult = new CONNECTED();
                            finalResult.addToHeader("CONNECTED", "");
                            finalResult.addToHeader("version", get("accept-version"));
                        }
                    }
                }
            }
            theConnections.send(_id, finalResult);
        } catch (Exception ex) {
            System.out.println("Exception Error");
        }
    }

}