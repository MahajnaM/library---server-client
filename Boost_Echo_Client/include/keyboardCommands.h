//
// Created by shahafbe@wincs.cs.bgu.ac.il on 15/01/2020.
//

#ifndef UNTITLED2_KEYBOARDCOMMANDS_H
#define UNTITLED2_KEYBOARDCOMMANDS_H

#include "../include/connectionHandler.h"
#include "../include/user.h"

using namespace std;

class keyboardCommands {
private:
    user &user;
    ConnectionHandler &_connectionHandler;

public:
    void start(string);
};


#endif //UNTITLED2_KEYBOARDCOMMANDS_H
