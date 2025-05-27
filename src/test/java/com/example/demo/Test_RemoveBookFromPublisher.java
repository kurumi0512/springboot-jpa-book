package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Publisher;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class Test_RemoveBookFromPublisher {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	@Test
	@Transactional // 預設在測試中會自動回滾（rollback）資料。
	@Commit // 在測試環境中使用 @Transactional 要配合使用 @Commit 讓事務提交
	// commit 表示真的要提交資料庫變更
	public void remove() {
		// 從資料庫找出 ID=1 的書籍與出版社
		Book book1 = bookRepository.findById(1).get();
		Publisher publisher = publisherRepository.findById(1).get();
		// 使用原生 SQL 刪除 publisher_book 關聯表中的一筆資料（多對多關聯中介表）
		publisherRepository.deleteBookFromPublisher(publisher.getId(), book1.getId());

	}

}