//
// Created by shahafbe@wincs.cs.bgu.ac.il on 15/01/2020.
//

#ifndef UNTITLED2_USER_H
#define UNTITLED2_USER_H

#include <string>
#include <vector>
#include <map>
#include "../include/Book.h"

using namespace std;

class user {
private:
    map<string, vector<Book>> _allmyBooks;
    const string _userName;
    size_t _receipt_Id;
public:
    user(string userName);

    const string &getUserName();

    map<string, vector<Book >> &getUserBooks();

    void addToUserBooks(string genre, string bookName);


};


#endif //UNTITLED2_USER_H
