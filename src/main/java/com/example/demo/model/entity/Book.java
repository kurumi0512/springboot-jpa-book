package com.example.demo.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100, nullable = false)
	private String name;

	// 這段關聯由 Publisher 實體中的 books 屬性來維護（也就是 Publisher 是主控端）。
	@ManyToMany(mappedBy = "books")
	private List<Publisher> publishers;

	// 查到書可以知道作者是誰
	// Book是主控

	@ManyToOne // 多對一(Author)
	@JoinColumn(name = "author_id") // 可以自己自訂名字 ,資料表的關聯欄位
	private Author author;
}
