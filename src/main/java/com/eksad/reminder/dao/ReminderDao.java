package com.eksad.reminder.dao;

import org.springframework.data.repository.CrudRepository;

import com.eksad.reminder.entity.Reminder;

public interface ReminderDao extends CrudRepository<Reminder, Long>{
	
	

}
