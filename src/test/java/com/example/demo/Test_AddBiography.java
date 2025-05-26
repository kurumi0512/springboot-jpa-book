package com.example.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Biography;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BiographyRepository;

@SpringBootTest
public class Test_AddBiography {
	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BiographyRepository biographyRepository;

	@Test
	public void add() {
		Optional<Author> optAuthor = authorRepository.findById(2);
		if (optAuthor.isEmpty()) {
			System.out.println("查無作者");
			return;
		}

		Author author = optAuthor.get();

		Biography biography = new Biography();
		biography.setDetails("我是一個java技術的 IT 作者...");
		biography.setAuthor(author); // 建立與作者之間的關聯

		biographyRepository.save(biography); // 自動處理與作者之間的關聯

	}
}
