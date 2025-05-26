package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

@SpringBootTest
public class Test_ReadAuthor {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	@Test
	// @Transactional ->全部查詢
	public void read() {
		// 查詢作者(含自傳)
		List<Author> authors = authorRepository.findAll();
		authors.forEach(author -> {
			System.out.printf("序號:%d 姓名:%s 自傳:%s%n", author.getId(), author.getName(),
					author.getBiography().getDetails());

		});

		// 查詢作者+書籍 就要用我們自訂的findAllWithBooks()
		List<Author> authors2 = authorRepository.findAllWithBooks();
		authors2.forEach(author -> {
			System.out.printf("序號:%d 姓名:%s 自傳:%s 著作數量:%d%n", author.getId(), author.getName(),
					author.getBiography().getDetails(), author.getBooks().size());

		});

	}

}
