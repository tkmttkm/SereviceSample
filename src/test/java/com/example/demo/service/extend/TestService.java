package com.example.demo.service.extend;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Entity;
import com.example.demo.form.Form;


@SpringBootTest
@Transactional
class TestService {

	@Autowired
	private Service service;


	@Test
	void testFindAll() {
		service.findAll();
		List<Entity> allData = service.getDataList();
		assertEquals(service.getDataListSize(),4);

		List<Entity> data_id1 = allData.stream().filter(d -> d.getId() == 1).collect(Collectors.toList());
		assertEquals(data_id1.size(), 1);

		assertEquals(data_id1.get(0).getFullName(), "エンティティ　ダンプティ");
		assertEquals(data_id1.get(0), 20240418);
	}

	@Test
	void testFindById() {
		Entity data = service.findById(2);

		assertEquals(data.getFullName(), "エンティティ　ティティ");
		assertEquals(data.getInsert_date(), 20240418);
	}

	@Test
	void testFindByUserName() {
		service.findByUserName("エンティティ　ダンプティ");

		Entity data = service.getDataList().get(0);
		assertEquals(service.getDataListSize(), 1);

		assertEquals(data.getId(), 1);
		assertEquals(data.getInsert_date(), 20240418);
	}

	@Test
	void testAddUser() {
		Form form = new Form();
		form.setId("10");
		form.setFull_name("テスト テストテストテスト");
		form.setInsert_date("20240603");

		service.addUser(form);

		assertEquals(service.getAddCount(), 1);
	}

	@Test
	void testDeleteUser() {
		service.deleteUser(1);
		Entity data = service.findById(1);
		service.findAll();
		assertNull(data);
		assertEquals(service.getDataListSize(), 3);
	}

	@Test
	void testEditUser() {
		Form form = new Form();
		form.setId("3");
		form.setFull_name("編集した　名前");
		form.setInsert_date("20240603");

		service.editUser(form);
		assertEquals(service.getEditCount(), 1);

		Entity data = service.findById(3);
		assertEquals(data.getFullName(), "編集した　名前");
		assertEquals(data.getInsert_date(), 20240603);
	}

}
