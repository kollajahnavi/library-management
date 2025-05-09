package com.example.library;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookService service;

    @BeforeEach
    void setUp() {
        service = new BookService();
    }

    @Test
    void testAddAndGetBook() {
        Book b = new Book();
        b.setTitle("Java");
        b.setAuthor("Author");
        b.setIsbn("1234567890123");
        b.setPublishedYear(2020);

        Book saved = service.addBook(b);
        assertEquals("Java", saved.getTitle());
        assertEquals(1, service.getAllBooks().size());
    }

    @Test
    void testSearchBooks() {
        Book b = new Book();
        b.setTitle("Spring Boot");
        b.setAuthor("Josh");
        b.setIsbn("1234567890123");
        b.setPublishedYear(2019);
        service.addBook(b);

        List<Book> result = service.searchBooks("spring");
        assertFalse(result.isEmpty());
    }
}