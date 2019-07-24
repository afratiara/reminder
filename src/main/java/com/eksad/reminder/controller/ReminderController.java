package com.eksad.reminder.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.reminder.dao.ReminderDao;
import com.eksad.reminder.entity.Reminder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = "Reminder")
public class ReminderController {
	
	@Autowired
	ReminderDao reminderDao;
	
	@ApiOperation(
			value = "API to retrieve reminder's data",
			notes = "Return data with JSON format",
			tags = "Get Data API")
	@GetMapping("/reminder")
	public List<Reminder> getAll() {
		List<Reminder> result = new ArrayList<>();
		reminderDao.findAll().forEach(result::add);
		
		return result;
	}

// ------------------------------------------------
	
	@ApiOperation(
			value = "API to retrieve all reminder by id",
			notes = "Return data with JSON format",
			tags = "Get Data API")
	@GetMapping("/reminder/{id}")
	public Reminder getOne(@PathVariable Long id) {
		return reminderDao.findById(id).orElse(null);
	}
	
// ------------------------------------------------
	
	@ApiOperation(
			value = "Insert data reminder",
			notes = "Insert data reminder to database",
			tags = "Data Manipulation API")
	@PostMapping(value = "/reminder")
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
	
	@ApiOperation(
			value = "Update data reminder",
			notes = "Update data reminder to database",
			tags = "Data Manipulation API")
	@PutMapping(value = "reminder/{id}")
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
	
	@ApiOperation(
			value = "Delete data reminder",
			notes = "Delete data reminder from database",
			tags = "Data Manipulation API")
	@DeleteMapping(value = "reminder/{id}")
	public HashMap<String, Object> delete(@PathVariable Long id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		reminderDao.deleteById(id);
		result.put("message", "Data Berhasil Dihapus");
		return result;
	}

}
