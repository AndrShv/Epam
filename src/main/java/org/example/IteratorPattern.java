package org.example;

import java.util.Iterator;
import java.util.List;

class BookCollection {
    List<String> books = List.of("Книга 1", "Книга 2");

    public Iterator<String> iterator() {
        return books.iterator();
    }
}

public class IteratorPattern {
    public static void main(String[] args) {
        BookCollection collection = new BookCollection();
        for (String book : collection.books) {
            System.out.println(book);
            //  Книга 1
            //  Книга 2
        }
    }
}

