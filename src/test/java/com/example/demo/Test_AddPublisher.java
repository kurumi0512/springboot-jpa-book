package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Publisher;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;

@SpringBootTest
public class Test_AddPublisher {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	@Test
	public void add() {
		// 從資料庫抓取 ID 為 1 和 2 的書籍
		Book book1 = bookRepository.findById(1).get();
		Book book2 = bookRepository.findById(2).get();

		// 代表「碁峰資訊」出版了兩本書
		Publisher publisher1 = new Publisher();
		publisher1.setName("碁峰資訊");
		publisher1.addBook(book1);
		publisher1.addBook(book2);

		// 代表「第三波資訊」出版了 book1
		Publisher publisher2 = new Publisher();
		publisher2.setName("第三波資訊");
		publisher2.addBook(book1);

		// 天下文化無出版書籍
		Publisher publisher3 = new Publisher();
		publisher3.setName("天下文化");

		publisherRepository.save(publisher1);
		publisherRepository.save(publisher2);
		publisherRepository.save(publisher3);

		// 這裡會儲存三筆 Publisher 資料，其中有兩筆會透過 @ManyToMany 關聯，把書籍一併加入中介表（如 publisher_book）。
	}

}