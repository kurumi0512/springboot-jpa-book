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

	@ManyToMany(mappedBy = "books")
	private List<Publisher> publishers;

	// 查到書可以知道作者是誰
	// Book是主控

	@ManyToOne
	@JoinColumn(name = "author_id") // 可以自己自訂名字 ,資料表的關聯欄位
	private Author author;
}
