package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Reminder;

@Transactional // 提供一种控制事务管理的快捷手段
public interface ReminderRepository extends JpaRepository<Reminder, String> {//自定義接口 繼承與JpaRepository 使用JpaRepository里自帶方法

	// Reminder findAll(String id, String title, String content);

}
