/**
 *
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Entity;

/**
 * Spring Data JPAを使用
 * @author Takumi
 */
public interface Repository extends JpaRepository<Entity,Integer>{

	public List<Entity> findByFullName(String fullName);

}