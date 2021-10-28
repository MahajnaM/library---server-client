//
// Created by shahafbe@wincs.cs.bgu.ac.il on 18/01/2020.
//

#include "ServerResponse.h"
#include <Utils.h>

using namespace std;

void ServerResponse::start(string response) {
    bool _proccess = true;
    while (_proccess) {
        vector<string> theResponse = Utils::split(response, " ");
        if (theResponse[0] == "CONNECTED") {
            cout << "Login successful" << endl;
        } else
            //-------------------------------------------------------------------------------------
        if (theResponse[0] == "RECEIPT") {
            //todo:should complete
        }
//-----------------------------------------------------------------------------------------
        else if (theResponse[0] == "MESSAGE") {
            //todo : should complete
        } else if (theResponse[0] == "ERROR") {
            //todo : should complete
        }
    }
}
