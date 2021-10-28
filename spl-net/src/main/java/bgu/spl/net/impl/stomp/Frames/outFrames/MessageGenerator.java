package bgu.spl.net.impl.stomp.Frames.outFrames;

public interface MessageGenerator<T> {
    public T generate(Integer connectionId);
}
