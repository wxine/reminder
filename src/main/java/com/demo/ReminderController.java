package com.demo;

import java.util.List;
import java.util.UUID;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.domain.Reminder;
import com.demo.repository.ReminderRepository;

@Controller
public class ReminderController {

	@Autowired
	private ReminderRepository reminderRepository;

	@RequestMapping("/save")
	public String save(HttpServletRequest request) {
		String title = request.getParameter("title"); // 获取网页数据
		String content = request.getParameter("content");
		Reminder reminder = new Reminder();
		reminder.setId(UUID.randomUUID().toString().replace("-", "")); // 设置id为随机数
		reminder.setTitle(title);
		reminder.setContent(content);
		reminderRepository.save(reminder); // 保存
		return "redirect:/index"; // 重定向
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET) // 判断是否method为post
	public String Delete(@RequestParam String id) {
		Reminder reminder = reminderRepository.findOne(id); // 找id
		reminderRepository.delete(reminder); // 刪除
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String Find(Model m) {
		// 测试findAll, 查询所有记录
		List<Reminder> reminder = reminderRepository.findAll();
		m.addAttribute("reminder", reminder); // 遍歷
		return "index";
	}

	@RequestMapping(value = "/updateM", method = RequestMethod.GET)
	public String updateM(@RequestParam String id, Model model) {
		Reminder reminder = reminderRepository.findOne(id);
		model.addAttribute("rem", reminder); // 打包對象
		return "updatA";
	}

	@RequestMapping(value = "/updatA", method = RequestMethod.POST)
	public String updatA(Reminder reminder) {
		reminderRepository.save(reminder); // 重新保存进行替换 达到修改目的
		return "redirect:/index";
	}
}
