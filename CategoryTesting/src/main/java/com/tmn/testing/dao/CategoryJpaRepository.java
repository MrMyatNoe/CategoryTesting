package com.tmn.testing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tmn.testing.entity.Category;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Long>{

	@Query(value = "SELECT * FROM Category "
			+"WHERE name LIKE ?1%",nativeQuery=true)
	Category findByNameLike(@Param("name")String name);
	@Query(value = "SELECT * FROM Category "
			+"WHERE name = ?1",nativeQuery=true)
	Category categoryExists(String name);
}
