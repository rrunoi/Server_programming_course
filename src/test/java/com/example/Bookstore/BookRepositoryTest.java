package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookRepository;
import com.example.Bookstore.model.Category;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
	private BookRepository repository;

	@Test
	public void findBookByTitle() {
		List<Book> books = repository.findByTitle("First Title");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Author One");
	}
	
	@Test 
	public void createNewBook() { 
		Book book = new Book("Third Title", "Author three", "book three isbn", 1991, new Category("CatThree"));
		repository.save(book); 
		assertThat(book.getId()).isNotNull(); 
	  }
	 
	@Test
	public void deleteBook() {
		
	}
	
}
