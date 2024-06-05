package com.example.demo.form;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * <pre>
 * 画面より入力されることを想定したクラス
 * 型チェックも行う
 * </pre>
 * @author Takumi
 */
@Data
public class Form {

	@NotEmpty
	@Pattern(regexp = "\\d", message = "idは数値のみ入力可能です")
	private String id;
	@NotEmpty
	private String full_name;
	@Pattern(regexp = "\\d", message = "insert_dateは数値のみ入力可能です")
	private String insert_date;
	
	
	/**
	 * 現在日時をyyyyMMdd形式で取得
	 * @return yyyyMMdd
	 */
	public String getInsert_date() {
		if(!StringUtils.isBlank(this.insert_date)) {
			return this.insert_date;
		}
		
		LocalDateTime nowDate = LocalDateTime.now();
        DateTimeFormatter yyyyMMdd_formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String yyyyMMdd = yyyyMMdd_formatter.format(nowDate);
        return yyyyMMdd;
	}
}
