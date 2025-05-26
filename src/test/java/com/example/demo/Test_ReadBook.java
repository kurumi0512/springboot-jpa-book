package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Book;
import com.example.demo.repository.BookRepository;

@SpringBootTest
public class Test_ReadBook {

	@Autowired
	private BookRepository bookRepository;

	// 多對一原本就是用@ManyToOne，EAGER（立即抓取）
	// 不會影響太多效能,所以直接寫就好,不用在多加一個BookRepository的自訂方法!

	@Test
	public void read() {
		// 查詢書籍
		List<Book> books = bookRepository.findAll();
		books.forEach(book -> {
			System.out.printf("序號:%d 書名:%s%n", book.getId(), book.getName());
		});

		// 查詢書籍 + 作者
		books.forEach(book -> {
			System.out.printf("序號:%d 書名:%s 作者:%s%n", book.getId(), book.getName(), book.getAuthor().getName());
		});
	}

}
