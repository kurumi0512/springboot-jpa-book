package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Biography;

@Repository
public interface BiographyRepository extends JpaRepository<Biography, Integer> {

	// @Query("select b from Biography b inner join fetch b.author")
	// Biography 與Author 同時都有,c會出不來,因為c沒有對應的作者
	// A 1
	// B 2
	// D 4

	// 適用在 author 是 LAZY 的情況，可以避免 N+1 問題（重複查很多次 DB）
	@Query("select b from Biography b left join fetch b.author") // 若沒有查到 Author 則該欄位顯示 null
	// 往左靠,用author拼進去
	// A 1
	// B 2
	// C null
	// D 4
	List<Biography> findAllWithAuthor();
}