package com.example.demo.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50, nullable = false)
	private String name;

	// mappedBy是被控端,被動
	// 寫fetch = FetchType.EAGER,會把作者的全部書籍都寫出來,會比較耗能
	@OneToMany(mappedBy = "author")
	// @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	// 如果用Eager的話,即使想要查作者而已也會跑書籍出來
	private List<Book> books;
}
