package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.categories;

public interface categoriesRepository extends JpaRepository<categories, Long> {

//	static List<categories> findByCnameContaining(String cname) {
//		return null;
//	}

	List<categories> findByCname(String cname);
}
