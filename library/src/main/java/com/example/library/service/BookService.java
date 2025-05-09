package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class BookService {
	
	private Long id;
    private String title;
    private String author;
	
    private final Map<Long, Book> books = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public Book addBook(Book book) {
        book.setId(counter.getAndIncrement());
        books.put(book.getId(), book);
        return book;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public List<Book> searchBooks(String query) {
        return books.values().stream()
            .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                            book.getAuthor().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());
    }

    public Book updateBook(Long id, Book updated) {
        if (!books.containsKey(id)) throw new BookNotFoundException(id);
        updated.setId(id);
        books.put(id, updated);
        return updated;
    }

    public void deleteBook(Long id) {
        if (!books.containsKey(id)) throw new BookNotFoundException(id);
        books.remove(id);
    }
}
