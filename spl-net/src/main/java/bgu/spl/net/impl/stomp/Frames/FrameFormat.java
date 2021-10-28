package bgu.spl.net.impl.stomp.Frames;


import bgu.spl.net.impl.stomp.StompProtocol;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class FrameFormat {
    private HashMap<String, String> _headers = new LinkedHashMap<>();

    public void addToHeader(String FrameName, String FrameValue) {
        _headers.put(FrameName, FrameValue);
    }

    public void addToHeader(String FrameName, double FrameValue) {
        _headers.put(FrameName, FrameValue + "");
    }

    public HashMap<String, String> getHeadersMap() {
        return _headers;
    }

    public String get(String header) {
        return _headers.get(header);
    }

    public String getCommandAndRemove() {
        return _headers.remove("command");
    }

    public abstract void work(StompProtocol userProtocol);

    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : _headers.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            strBuilder.append(key).append(":").append(val).append("\n");
        }
        String str1 = strBuilder.toString();
        str1 = str1.replace("command:", "");
        str1 = str1.replace("body:", "\n");
        str1 = str1.substring(0, str1.length() - 1);
        return str1;
    }
}
