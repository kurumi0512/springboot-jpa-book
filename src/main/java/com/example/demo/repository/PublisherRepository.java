package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Publisher;

//這個方法就是在做「斷開這個關聯」而不是刪除書或出版社本身
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

	@Modifying // 表示這個 @Query 是修改型操作（例如：INSERT、UPDATE、DELETE），不是查詢
	// 使用命名參數（:xxx）的 SQL，就必須搭配 @Param("xxx") 告訴 Spring 每個參數對應關係
	@Query(value = "delete from publisher_book where publisher_id = :publisherId and book_id = :bookId", nativeQuery = true)
	void deleteBookFromPublisher(@Param("publisherId") Integer publisherId, @Param("bookId") Integer bookId);
}