package bgu.spl.net.impl.stomp;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.impl.stomp.Frames.FrameFormat;

import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class MsgEncoderDecoderImp implements MessageEncoderDecoder<FrameFormat> {

    LinkedList<Byte> list1 = new LinkedList<>();
    int i = 0;

    @Override
    public FrameFormat decodeNextByte(byte nextByte) {
        FrameFormat frameFormat = null;
        if (nextByte == '\0')
            frameFormat = FrameIt();
        else
            list1.add(nextByte);
        return frameFormat;
    }

    @SuppressWarnings("unchecked")
    private FrameFormat FrameIt() {
        FrameFormat format = null;
        try {
            byte[] bytes = new byte[list1.size()];
            list1.forEach(ByteToBe -> bytes[i++] = ByteToBe);

            String msg_string = new String(bytes, StandardCharsets.UTF_8);

            String command = "bgu.spl.net.impl.stomp.Frames.inFrames." + msg_string.split("\n")[0];

            msg_string = "command:" + msg_string;
            msg_string = msg_string.replace("\n\n", "\nbody:");
            String[] Lines = msg_string.split("\n");

            Class<FrameFormat> myClass = (Class<FrameFormat>) Class.forName(command);
            Constructor<FrameFormat> constructor = myClass.getConstructor();
            format = constructor.newInstance();

            for (String Line : Lines) {
                String[] Headerpair = Line.split(":", 2);
                format.addToHeader(Headerpair[0], Headerpair[1]);
            }
        } catch (Exception failedException) {
            format = null;
        } finally {
            i = 0;
            list1.clear();
        }

        return format;
    }

    @Override
    public byte[] encode(FrameFormat message) {
        return (message + "\0").getBytes();
    }
}
