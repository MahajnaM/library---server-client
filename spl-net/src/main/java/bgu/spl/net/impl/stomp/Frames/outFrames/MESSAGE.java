package bgu.spl.net.impl.stomp.Frames.outFrames;

import bgu.spl.net.impl.stomp.Frames.FrameFormat;
import bgu.spl.net.impl.stomp.StompProtocol;
import bgu.spl.net.srv.Connections;

import java.util.concurrent.atomic.AtomicInteger;

public class MESSAGE extends FrameFormat {

    private static AtomicInteger idGenerator = new AtomicInteger(0);
    private int id = idGenerator.incrementAndGet();


    @Override
    public void work(StompProtocol userProtocol) {

    }

    public int getId() {
        return id;
    }
}
