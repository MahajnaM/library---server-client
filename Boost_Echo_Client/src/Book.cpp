//
// Created by shahafbe@wincs.cs.bgu.ac.il on 15/01/2020.
//
#include <string>
#include "../include/Book.h"

using namespace std;

Book::Book(string genre, string name, string bookOwner) : _bookGenre(genre), _bookName(name),
                                                          _bookOwnerName(bookOwner), _isAvialble(true) {}

string Book::getBookName() {
    return _bookName;
}

string Book::getBookGenre() {
    return _bookGenre;
}

string Book::getBookOwnerName() {
    return _bookOwnerName;
}

bool Book::isEqual(Book check) {
    return _bookName == check.getBookName();

}

void Book::makeItAvialble() {
    _isAvialble = true;
}

void Book::makeItUnAvialble() {
    _isAvialble = false;
}