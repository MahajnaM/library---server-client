//
// Created by מחמוד on 16-01-2020.
//

#include "../include/Frame.h"
#include <string>
#include <Utils.h>

using namespace std;

void Frame::addToHeader(string FrameName, string FrameValue) {
    _headers.emplace_back(FrameName, FrameValue);
}

vector<pair<string, string>> Frame::getHeaders() {
    return _headers;
}


string Frame::toString() {
    string returnedString;
    for (pair<string, string> entry : _headers) {
        string key = entry.first;
        string value = entry.second;
        returnedString.append(key).append(":").append(value).append("\n");
    }

    const string cmd = "command:";
    returnedString = returnedString.substr(cmd.size());

    vector<string> parts = Utils::split(returnedString, "body:", 2);
    returnedString = parts[0] + "\n" + parts[1];

    returnedString.append("\0");
    return returnedString;
}



