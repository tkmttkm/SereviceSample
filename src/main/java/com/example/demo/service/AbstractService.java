/**
 * 
 */
package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 継承先でリポジトリクラスはセットする。
 * @author Takumi
 */
@Data
public abstract class AbstractService<T> implements IService {

	/** 取得したデータ */
	protected List<T> dataList = new ArrayList<T>();
	
	/** 取得したデータ数 */
	protected int dataListSize;
	
	/** 追加したデータ数 */
	protected int addCount;
	
	/** 削除したデータ数 */
	protected int deleteCount;
	
	/** 編集したデータ数 */
	protected int editCount;
	
	public int getDataListSize() {
		return dataList.size();
	}
	
}
