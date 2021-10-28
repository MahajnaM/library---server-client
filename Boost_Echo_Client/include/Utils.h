//
// Created by shahafbe@wincs.cs.bgu.ac.il on 17/01/2020.
//

#ifndef UNTITLED2_UTILS_H
#define UNTITLED2_UTILS_H

#include <string>
#include <vector>

using namespace std;

class Utils {
public:
    static vector<string> split(string str, string delimiter, size_t limit);
    static vector<string> split(string str, string delimiter);
};


#endif //UNTITLED2_UTILS_H
