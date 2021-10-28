//
// Created by מחמוד on 16-01-2020.
//

#ifndef UNTITLED2_FRAME_H
#define UNTITLED2_FRAME_H

#include <unordered_map>
#include <string>
#include <vector>

using namespace std;

class Frame {
private:
    vector<pair<string, string>> _headers;

public:
    void addToHeader(string FrameName, string FrameValue);

    vector<pair<string, string>> getHeaders();

    string toString();

};

#endif //UNTITLED2_FRAME_H
