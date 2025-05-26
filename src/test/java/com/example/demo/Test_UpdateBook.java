package com.example.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

@SpringBootTest
public class Test_UpdateBook {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	// 變更書籍作者
	// 不要用Author來修改,比較麻煩
	// 這是一個 JUnit 測試方法，用來執行「變更某本書的作者」
	@Test
	public void updateBookName() {
		// 「我要從資料庫找出 ID 為 2 的作者。」
		// 也就是說，你明確指定要將書的作者換成「ID = 2 的那位作者」。
		// （例如：「把《Java入門》改為由 ID=2 的作者來寫」）
		Optional<Author> optAuthor = authorRepository.findById(2);
		if (optAuthor.isEmpty()) {
			System.out.println("查無作者");
			return;
		}

		Optional<Book> optBook = bookRepository.findById(1);
		if (optBook.isEmpty()) {
			System.out.println("查無書籍");
			return;
		}

		Author author = optAuthor.get();
		Book book = optBook.get();

		// 更新設定
		// 「把這本書的作者，改成剛剛找到的那位 Author」
		book.setAuthor(author);

		// 儲存更新(save 就是將最新物件狀態寫回給資料表)
		// save() 方法：如果是新的資料 → 執行 INSERT
		// 如果是已有 ID 的資料 → 執行 UPDATE
		bookRepository.save(book);
	}
}