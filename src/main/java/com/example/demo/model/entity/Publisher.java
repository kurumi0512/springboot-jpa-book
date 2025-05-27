package com.example.demo.model.entity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Publisher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 主鍵自動生成策略，IDENTITY 表示由資料庫自己幫你產生（如 AUTO_INCREMENT）
	private Integer id;

	@Column(length = 50, nullable = false)
	private String name;

	@ManyToMany // 對book來說出版社是主控端,由出版社來維護跟書之間的關聯
	@JoinTable(name = "publisher_book", joinColumns = @JoinColumn(name = "publisher_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<Book> books;

	// 自建一個新增書籍的方法
	public void addBook(Book book) {
		if (books == null) {
			books = new CopyOnWriteArrayList<>(); // 支援多執行緒並發安全
		}
		books.add(book);
	}
}