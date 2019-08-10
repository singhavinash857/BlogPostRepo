package com.transformedge.apps.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BlogModel {

	@NotBlank(message = "{blog.title.notNull}")
	private String blogTitle;

	@NotBlank(message = "{blog.description.notNull}")
	private String blogDescription;
}
