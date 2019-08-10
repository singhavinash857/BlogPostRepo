package com.transformedge.apps.service;

import com.transformedge.apps.entity.Blog;
import com.transformedge.apps.model.BlogModel;

public interface BlogService {
	Blog saveBlog(BlogModel model);	
}
