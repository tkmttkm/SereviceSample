package com.example.demo.service.extend;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
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

	@BeforeEach
	void setUp() {
		service.setAddCount(0);
		service.setDeleteCount(0);
		service.setEditCount(0);
	}


	@Test
	void testFindAll() {
		service.findAll();
		List<Entity> allData = service.getDataList();
		assertEquals(service.getDataListSize(),4);

		List<Entity> data_id1 = allData.stream().filter(d -> d.getId() == 1).collect(Collectors.toList());
		assertEquals(data_id1.size(), 1);

		assertEquals(data_id1.get(0).getFullName(), "エンティティ　ダンプティ");
		assertEquals(data_id1.get(0).getInsert_date(), 20240418);
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
		Form addform = new Form();
		addform.setId("10");
		addform.setFull_name("テスト テストテストテスト");
		addform.setInsert_date("20240603");
		service.addUser(addform);

		Form existform = new Form();
		existform.setId("1");
		existform.setFull_name("テスト テストテストテスト");
		existform.setInsert_date("20240603");
		service.addUser(existform);

		assertEquals(service.getAddCount(), 1);

		Entity data10 = service.findById(10);
		assertEquals(data10.getFullName(), "テスト テストテストテスト");
		assertEquals(data10.getInsert_date(), 20240603);
	}

	@Test
	void testAddUsers() {
		List<Form> addList = new ArrayList<>();

		Form addform1 = new Form();
		addform1.setId("10");
		addform1.setFull_name("テスト テストテストテスト");
		addform1.setInsert_date("20240603");
		addList.add(addform1);

		Form addform2 = new Form();
		addform2.setId("11");
		addform2.setFull_name("テスト テストテスト");
		addform2.setInsert_date("20240607");
		addList.add(addform2);

		service.addUser(addList);

		Form existform = new Form();
		existform.setId("1");
		existform.setFull_name("テスト テストテストテスト");
		existform.setInsert_date("20240603");
		service.addUser(existform);

		assertEquals(service.getAddCount(), 2);

		Entity data10 = service.findById(10);
		assertEquals(data10.getFullName(), "テスト テストテストテスト");
		assertEquals(data10.getInsert_date(), 20240603);

		Entity data11 = service.findById(11);
		assertEquals(data11.getFullName(), "テスト テストテスト");
		assertEquals(data11.getInsert_date(), 20240607);
	}

	@Test
	void testDeleteUser() {
		service.deleteUser(1);
		service.deleteUser(10);
		Entity data = service.findById(1);
		service.findAll();
		assertNull(data);
		assertEquals(service.getDeleteCount(), 1);
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

	@Test
	void testEditUsers() {
		List<Form> addList = new ArrayList<>();

		Form editform1 = new Form();
		editform1.setId("1");
		editform1.setFull_name("テスト テストテストテスト");
		editform1.setInsert_date("20240603");
		addList.add(editform1);

		Form editform2 = new Form();
		editform2.setId("2");
		editform2.setFull_name("テスト テストテスト");
		editform2.setInsert_date("20240607");
		addList.add(editform2);

		service.editUser(addList);

		Form notExistform = new Form();
		notExistform.setId("10");
		notExistform.setFull_name("テスト テストテストテスト");
		notExistform.setInsert_date("20240603");
		service.editUser(notExistform);

		assertEquals(service.getEditCount(), 2);

		Entity data1 = service.findById(1);
		assertEquals(data1.getFullName(), "テスト テストテストテスト");
		assertEquals(data1.getInsert_date(), 20240603);

		Entity data2 = service.findById(2);
		assertEquals(data2.getFullName(), "テスト テストテスト");
		assertEquals(data2.getInsert_date(), 20240607);

	}

}
