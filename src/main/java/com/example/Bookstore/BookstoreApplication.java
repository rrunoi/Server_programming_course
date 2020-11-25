package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookRepository;
import com.example.Bookstore.model.Category;
import com.example.Bookstore.model.CategoryRepository;
import com.example.Bookstore.model.User;
import com.example.Bookstore.model.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository,
			UserRepository urepository) {
		return (args) -> {
			log.info("save books");
			repository.deleteAll();
			crepository.deleteAll();
			urepository.deleteAll();
			crepository.save(new Category("CatOne"));
			crepository.save(new Category("CatTwo"));
			crepository.save(new Category("CatThree"));

			repository.save(new Book("First Title", "Author One", "book one isbn", 2020,
					crepository.findByName("CatOne").get(0)));
			repository.save(new Book("Second Title", "Author Two", "book two isbn", 1990,
					crepository.findByName("CatTwo").get(0)));
			
			// Test users user1(user1Password), user2(user2Password)
			User user1 = new User("user", "$2a$06$2FtjqzBuNoPOdVDmN.mep.1PeP8QZnKtk8FQGIvOVKqrO4M0nS2QC",
					"user1@email.com", "USER");
			User user2 = new User("admin", "$2a$06$RsWjb6Vvl5oI/7RvrnxfJuDxGC2aTF83UFypj5raWqBbKyAuSAhJ2",
					"user2@email.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("get books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
// 
}
