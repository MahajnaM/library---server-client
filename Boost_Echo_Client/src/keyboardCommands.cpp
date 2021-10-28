
#include <Frame.h>
#include <Utils.h>
#include "keyboardCommands.h"

void keyboardCommands::start(string s) {
    bool _isLogged = true;
    while (_isLogged) {
        string commandInput;
        getline(std::cin, commandInput);
        vector<string> theCommand = Utils::split(s, " ");
        if (theCommand[0] == "login") {
            string sendCommand = "CONNECT";
            string hostAndServer = theCommand[1];
            string host = hostAndServer.substr(); // todo : must compelete
            string port = hostAndServer.substr();///todo : must compelete
            string name = theCommand[2];
            string passcode = theCommand[3];
            Frame frameToSend;
            frameToSend.addToHeader("command", sendCommand);
            frameToSend.addToHeader("accept-version", "1.2");
            frameToSend.addToHeader("host", host);
            frameToSend.addToHeader("login", name);
            frameToSend.addToHeader("passcode", passcode);
            string frameToSen = frameToSend.toString();
            _connectionHandler.sendLine(frameToSen);
            //todo : must complete !@!@!@!@1
        }
// ---------------------------------------------------------------------------
        else if (theCommand[0] == "join") {
            string sendCommand = "SUBSCRIBE";
            string genreToSubscribe = theCommand[1];
            Frame frameToSend;
            frameToSend.addToHeader("command", sendCommand);
            frameToSend.addToHeader("destination", genreToSubscribe);
            frameToSend.addToHeader("id", "");
            frameToSend.addToHeader("receipt", "");
            string frameToSen = frameToSend.toString();
            _connectionHandler.sendLine(frameToSen);
        }
            // ---------------------------------------------------------------------------
        else if (theCommand[0] == "exit") {
            string sendCommand = "UNSUBSCRIBE";
            string genreToUnsubscribe = theCommand[1];
            Frame frameToSend;
            frameToSend.addToHeader("command", sendCommand);
            frameToSend.addToHeader("destination", genreToUnsubscribe);
            frameToSend.addToHeader("id", "");
            frameToSend.addToHeader("receipt", "");
            frameToSend.addToHeader("\n\n", "");
            string frameToSen = frameToSend.toString();
            _connectionHandler.sendLine(frameToSen);

        }
// ---------------------------------------------------------------------------
        else if (theCommand[0] == "add") {
            string sendCommand = "SEND";
            string genreBook = theCommand[1];
            string bookName = theCommand[2];
            Frame frameToSend;
            frameToSend.addToHeader("command", sendCommand);
            frameToSend.addToHeader("destination", genreBook);
            frameToSend.addToHeader("body:", user.getUserName() + "has added the book" + genreBook);
            frameToSend.addToHeader("id", "");
            frameToSend.addToHeader("receipt", "");
            user.addToUserBooks(genreBook, bookName); //add the book to map myBook
            string frameToSen = frameToSend.toString();
            _connectionHandler.sendLine(frameToSen);
        }
// ---------------------------------------------------------------------------
        else if (theCommand[0] == "borrow") {
            string sendCommand = "SEND";
            string genreBook = theCommand[1];
            string bookName = theCommand[2];
            Frame frameToSend;
            frameToSend.addToHeader("command", sendCommand);
            frameToSend.addToHeader("destination", genreBook);
            frameToSend.addToHeader("receipt", "");
            frameToSend.addToHeader("body:", user.getUserName() + "wish to borrow" + bookName);

            string frameToSen = frameToSend.toString();
            _connectionHandler.sendLine(frameToSen);
        }
// ---------------------------------------------------------------------------
        else if (theCommand[0] == "return") {
            string sendCommand = "SEND";
            string genreBook = theCommand[1];
            string bookName = theCommand[2];
            Frame frameToSend;
            frameToSend.addToHeader("command", sendCommand);
            frameToSend.addToHeader("destination", genreBook);
            frameToSend.addToHeader("id", " ");
            frameToSend.addToHeader("receipt", "");
            frameToSend.addToHeader("body", "returning " + bookName + " to" + "");
            string frameToSen = frameToSend.toString();
            _connectionHandler.sendLine(frameToSen);
        }
// ---------------------------------------------------------------------------
        else if (theCommand[0] == "status") {
            string sendCommand = "SEND";
            string genreType = theCommand[1];
            Frame frameToSend;
            frameToSend.addToHeader("destination", genreType);
            frameToSend.addToHeader("body", "book status");
            string frameToSen = frameToSend.toString();
            _connectionHandler.sendLine(frameToSen);
        }
// ---------------------------------------------------------------------------8
        else if (theCommand[0] == "logout") {
            _isLogged = false;
            string sendCommand = "DISCONNECT";
            Frame frameToSend;
            frameToSend.addToHeader("command", sendCommand);
            string frameToSen = frameToSend.toString();
            _connectionHandler.sendLine(frameToSen);
        }

    }

}

