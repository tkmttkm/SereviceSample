package com.example.demo.form;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 画面より入力されることを想定したクラス
 * @author Takumi
 */
@Data
public class Form {

	@NotEmpty
	@Pattern(regexp = "\\d")
	private String id;
	@NotEmpty
	private String full_name;
	@Pattern(regexp = "\\d")
	private String insert_date;
	
	
	/**
	 * 現在日時をyyyyMMdd形式で取得
	 * @return yyyyMMdd
	 */
	public String getInsert_date() {
		LocalDateTime nowDate = LocalDateTime.now();
        DateTimeFormatter yyyyMMdd_formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String yyyyMMdd = yyyyMMdd_formatter.format(nowDate);
        return yyyyMMdd;
	}
}
