package com.transformedge.apps.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transformedge.apps.entity.Blog;
import com.transformedge.apps.model.BlogModel;
import com.transformedge.apps.repository.BlogRepo;
import com.transformedge.apps.service.BlogService;

@Service
public class BlogServiceIMPL implements BlogService{

	@Autowired
	BlogRepo blogRepo;
	
	@Override
	public Blog saveBlog(BlogModel model) {
		Blog blog = new Blog();
		blog.setBlogTitle(model.getBlogTitle());
		blog.setBlogDescription(model.getBlogDescription());
		return blogRepo.save(blog);
	}

}
