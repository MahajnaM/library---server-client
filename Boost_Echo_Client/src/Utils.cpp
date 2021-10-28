//
// Created by shahafbe@wincs.cs.bgu.ac.il on 17/01/2020.
//

#include "Utils.h"

//copied: https://stackoverflow.com/questions/14265581/parse-split-a-string-in-c-using-string-delimiter-standard-c
vector<string> Utils::split(string str, string delimiter, size_t limit) {
    vector<string> tokens;

    if (limit == 1) {
        tokens.push_back(str);
        return tokens;
    }

    if (limit <= 0)
        limit = str.length() + 10;

    if (str.empty()) {
        tokens.emplace_back();
        return tokens;
    }
    size_t prev = 0, pos = 0;
    do {
        pos = str.find(delimiter, prev);
        if (limit == 1) pos = str.length();
        limit--;
        if (pos == string::npos) pos = str.length();
        string token = str.substr(prev, pos - prev);
        if (!token.empty()) tokens.push_back(token);
        prev = pos + 1;//plus " "(space) length
    } while (pos < str.length() && prev < str.length());

    while(limit>0){
        tokens.emplace_back("");
        limit--;
    }
    return tokens;
}

vector<string> Utils::split(string str, string delimiter) {
    return split(str, delimiter, 0);
}
