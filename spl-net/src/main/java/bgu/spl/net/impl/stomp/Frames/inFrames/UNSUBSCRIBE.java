package bgu.spl.net.impl.stomp.Frames.inFrames;

import bgu.spl.net.impl.stomp.Frames.FrameFormat;
import bgu.spl.net.impl.stomp.Frames.outFrames.ERROR;
import bgu.spl.net.impl.stomp.Frames.outFrames.RECEIPT;
import bgu.spl.net.impl.stomp.StompProtocol;
import bgu.spl.net.impl.stomp.UsersSet;
import bgu.spl.net.srv.Connections;

public class UNSUBSCRIBE extends FrameFormat {
    @Override
    public void work(StompProtocol userProtocol) {
        int _id = userProtocol.get_id();
        Connections<FrameFormat> theConnections = userProtocol.getConnections();
        UsersSet allUsers = userProtocol.getUsersSet();
//---------------------------------------------------------------------------------------------
        String SubscriptionId = get("id");
        String receipt = get("receipt");
        boolean isRec = receipt != null && !receipt.isEmpty();
//-------------------------------------------------------------------------------------------------
        if (allUsers.getUserbyId(_id) != null) {
            String toRemove = allUsers.getUserbyId(_id).unSubscribeTopic(Integer.parseInt(SubscriptionId));
            theConnections.unLink(_id, toRemove);
            if (isRec) {
                FrameFormat receipted = new RECEIPT();
                receipted.addToHeader("RECEIPT", "");
                receipted.addToHeader("receipt-id", receipt);
                theConnections.send(_id, receipted);
            }
        } else {
            ERROR error = new ERROR();
            if (isRec)
                error.addToHeader("RECEIPT", receipt);
            error.addToHeader("MESSAGE", "data failure");
            error.addToHeader("body", "no subscription id found for you");
        }
    }
}
