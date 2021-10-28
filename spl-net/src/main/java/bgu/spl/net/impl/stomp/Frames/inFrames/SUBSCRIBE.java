package bgu.spl.net.impl.stomp.Frames.inFrames;

import bgu.spl.net.impl.stomp.Frames.FrameFormat;
import bgu.spl.net.impl.stomp.Frames.outFrames.ERROR;
import bgu.spl.net.impl.stomp.Frames.outFrames.RECEIPT;
import bgu.spl.net.impl.stomp.StompProtocol;
import bgu.spl.net.impl.stomp.UsersSet;
import bgu.spl.net.srv.Connections;

public class SUBSCRIBE extends FrameFormat {
    @Override
    public void work(StompProtocol userProtocol) {
        int _id = userProtocol.get_id();
        Connections<FrameFormat> theConnections = userProtocol.getConnections();
        UsersSet allUsers = userProtocol.getUsersSet();
//---------------------------------------------------------------------------------------------
        String destination = get("destination");
        String SubscriptionId = get("id");
        String receipt = get("receipt");
        boolean isReceipted = receipt != null && !receipt.isEmpty();
//----------------------------------------------------------------------------------------------
        if (allUsers.isUserExists(_id)) {
            if (allUsers.getUserbyId(_id).subscribeTopic(Integer.parseInt(SubscriptionId), destination)) {
                theConnections.link(_id, destination);
                if (isReceipted) {
                    FrameFormat receipted = new RECEIPT();
                    receipted.addToHeader("RECEIPT", "");
                    receipted.addToHeader("receipt-id", receipt);
                    theConnections.send(_id, receipted);
                }
            }
        } else {
            ERROR error = new ERROR();
            if (isReceipted) {
                error.addToHeader("ERROR", "");
                error.addToHeader("message", "data failure");
                error.addToHeader("body", "no subscription id found for you");
                theConnections.send(_id, error);
            }
        }
    }
}
