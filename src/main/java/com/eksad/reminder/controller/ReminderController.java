package com.eksad.reminder.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.reminder.dao.ReminderDao;
import com.eksad.reminder.entity.Reminder;

@RestController
@RequestMapping("reminder")
public class ReminderController {
	
	@Autowired
	ReminderDao reminderDao;
	
	@RequestMapping("getAll")
	public List<Reminder> getAll() {
		List<Reminder> result = new ArrayList<>();
		reminderDao.findAll().forEach(result::add);
		
		return result;
	}

// ------------------------------------------------
	
	@RequestMapping("getOne/{id}")
	public Reminder getOne(@PathVariable Long id) {
		return reminderDao.findById(id).orElse(null);
	}
	
// ------------------------------------------------
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Reminder save(@RequestBody Reminder reminder) {
		try {
			return reminderDao.save(reminder);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
// ------------------------------------------------
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public Reminder update(@RequestBody Reminder reminder, @PathVariable Long id) {
		
		Reminder reminderSelected = reminderDao.findById(id).orElse(null);
		if(reminderSelected != null) {
			reminderSelected.setName(reminder.getName());
			reminderSelected.setDescription(reminder.getDescription());
			reminderSelected.setDate(reminder.getDate());
			
			return reminderDao.save(reminderSelected);
		} else {
			return null;
		}
	}
	
// ------------------------------------------------
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public HashMap<String, Object> delete(@PathVariable Long id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		reminderDao.deleteById(id);
		result.put("message", "Data Berhasil Dihapus");
		return result;
	}

}
