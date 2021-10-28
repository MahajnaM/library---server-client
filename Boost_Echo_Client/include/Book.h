//
// Created by shahafbe@wincs.cs.bgu.ac.il on 15/01/2020.
//

#ifndef UNTITLED2_BOOK_H
#define UNTITLED2_BOOK_H

#include <string>

using namespace std;

class Book {
private:
    const string _bookName;
    string _bookGenre;
    string _bookOwnerName;
    bool _isAvialble;

public:
    Book(string genre, string name, string bookOwner);

    string getBookName();

    string getBookGenre();

    string getBookOwnerName();

    bool isEqual(Book);

    void makeItAvialble();

    void makeItUnAvialble();
};

#endif //UNTITLED2_BOOK_H
