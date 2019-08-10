package com.transformedge.apps.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="blog")
public class Blog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="blog_id")
	private long id;
	
	
	private String blogTitle;
	private String blogDescription;
}
