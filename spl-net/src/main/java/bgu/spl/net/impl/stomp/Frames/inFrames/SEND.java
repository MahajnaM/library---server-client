package bgu.spl.net.impl.stomp.Frames.inFrames;

import bgu.spl.net.impl.stomp.ConnectionsImpl;
import bgu.spl.net.impl.stomp.Frames.FrameFormat;
import bgu.spl.net.impl.stomp.Frames.outFrames.MESSAGE;
import bgu.spl.net.impl.stomp.StompProtocol;
import bgu.spl.net.impl.stomp.UsersSet;

public class SEND extends FrameFormat {

    @Override
    public void work(StompProtocol userProtocol) {
        ConnectionsImpl<FrameFormat> theConnections = userProtocol.getConnections();
//---------------------------------------------------------------------------------------------
        String body = get("body");
        String destination = get("destination");

        theConnections.send(destination, connectionId -> {
                    MESSAGE msg = new MESSAGE();
                    msg.addToHeader("subscription", theConnections.getSubIdByConId(connectionId, destination));
                    msg.addToHeader("Message-id", msg.getId());
                    msg.addToHeader("destination", destination);
                    msg.addToHeader("body", body);
                    return msg;
                }
        );
    }
}
