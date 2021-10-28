
#include "../include/user.h"
#include <string>

user::user(string userName) : _userName(userName), _receipt_Id(0) {}

void user::addToUserBooks(string genre, string bookName) { // todo : not completed
    Book newBookToAdd(genre, bookName, this->_userName);
    _allmyBooks.at(genre).push_back(newBookToAdd);
}

const string &user::getUserName() {
    return _userName;
}

map<string, vector<Book> > &user::getUserBooks() {
    return _allmyBooks;
}
