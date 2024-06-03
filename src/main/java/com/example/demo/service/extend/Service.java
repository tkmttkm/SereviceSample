/**
 * 
 */
package com.example.demo.service.extend;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Entity;
import com.example.demo.form.Form;
import com.example.demo.repository.Repository;
import com.example.demo.service.AbstractService;


/**
 * @author Takumi
 *
 */
@org.springframework.stereotype.Service
public class Service extends AbstractService<Entity> {

	@Autowired
	private Repository repository;
	/** 処理対象のデータを格納 */
	private List<Entity> entityList;
	
	@Override
	public void findAll() {
		List<Entity> list = repository.findAll();
		setDataList(list);
	}
	
	@Override
	public Entity findById(int userId) {
		Optional<Entity> dataOpt = repository.findById(Integer.valueOf(userId));
		//データがない場合はnullを返す
		return dataOpt.isPresent() ? dataOpt.get() : null;
	}

	@Override
	public void findByUserName(String userName) {
		List<Entity> list = repository.findByFullName(userName);
		setDataList(list);
	}

	@Override
	public void addUser(Form form) {
		save(form);
		setAddCount(getAddCount() + 1);
	}

	@Override
	public void addUser(List<Form> formList) {
		List<Entity> savedList = saveAll(formList);
		setAddCount(getAddCount() + savedList.size());
	}

	@Override
	public void deleteUser(int userId) {
		Integer id = Integer.valueOf(userId);
		repository.deleteById(id);
		setDeleteCount(getDeleteCount() + 1);

	}

	@Override
	public void editUser(Form form) {
		save(form);
		setEditCount(getEditCount() + 1);
	}


	@Override
	public void editUser(List<Form> formList) {
		List<Entity> savedList = saveAll(formList);
		setEditCount(getEditCount() + savedList.size());
	}
	
	
	
	/**
	 * {@link Form}クラスを{@link Entity}クラスへ変換する
	 * @param form
	 * @return 変換後の{@link Entity}クラス
	 */
	private Entity setEntity(Form form) {
		Entity entity = new Entity();
		
		entity.setId(Integer.parseInt(form.getId()));
		entity.setFullName(form.getFull_name());
		entity.setInsert_date(Integer.parseInt(form.getInsert_date()));
		
		return entity;
	}
	
	/**
	 * INSERTもしくはUPDATEを実行
	 * @param form 更新、挿入したいデータ
	 * @return 更新、挿入したデータの{@link Entity}クラス
	 */
	private Entity save(Form form) {
		Entity entity = setEntity(form);
		return repository.save(entity);
	}
	
	/**
	 * 複数のINSERTもしくはUPDATEを実施
	 * @param formList 更新、挿入したいデータ
	 * @return 更新、挿入したいデータ
	 */
	private List<Entity> saveAll(List<Form> formList) {
		setEntityListByFromList(formList);
		return repository.saveAll(entityList);
	}
	
	/**
	 * {@link this#entityList}を{@link Form}クラスからセットする
	 * @param formList
	 */
	private void setEntityListByFromList(List<Form> formList) {
		List<Entity> entityList = new ArrayList<>();
		
		for(Form form : formList) {
			entityList.add(setEntity(form));
		}
		
		setEntityList(entityList);
	}

	public List<Entity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}

}
