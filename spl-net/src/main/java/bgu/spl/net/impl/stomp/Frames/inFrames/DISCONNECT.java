package bgu.spl.net.impl.stomp.Frames.inFrames;

import bgu.spl.net.impl.stomp.Frames.FrameFormat;
import bgu.spl.net.impl.stomp.Frames.outFrames.RECEIPT;
import bgu.spl.net.impl.stomp.StompProtocol;
import bgu.spl.net.impl.stomp.UsersSet;
import bgu.spl.net.srv.Connections;

public class DISCONNECT extends FrameFormat {
    @Override
    public void work(StompProtocol userProtocol) {
        int _id = userProtocol.get_id();
        Connections<FrameFormat> theConnections = userProtocol.getConnections();
        UsersSet loggedInUsers = userProtocol.getUsersSet();
//---------------------------------------------------------------------------------------------
        String receipt = get("receipt");
//---------------------------------------------------------------------------------------------
        if (loggedInUsers.getUserbyId(_id) != null) {
            loggedInUsers.getUserbyId(_id).discconect();
            FrameFormat receipted = new RECEIPT();
            receipted.addToHeader("RECEIPT", "");
            receipted.addToHeader("receipt-id", receipt);
            theConnections.send(_id, receipted);
            userProtocol.terminate();
            theConnections.disconnect(_id);
        }
    }
}