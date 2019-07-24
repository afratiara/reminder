package com.eksad.reminder.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "reminder")
public class Reminder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "reminder_name")
	private String name;
	
	@Column(name = "reminder_description")
	private String description;
	
	@Column(name = "reminder_date")
	private Date date;

}
