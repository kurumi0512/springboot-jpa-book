package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Author;

@Repository // 標註這個介面為資料存取層（DAO）的一部分
public interface AuthorRepository extends JpaRepository<Author, Integer> {
	// 如果只要查作者的話,就用原先的寫法
	// 如果要查作者和著作數量就用底下這個方法

	// 作者查到誰的book就向left過來
	@Query("select a from Author a left join fetch a.books")
	List<Author> findAllWithBooks();
}
