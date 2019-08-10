package com.transformedge.apps.repository;

import org.springframework.data.repository.CrudRepository;

import com.transformedge.apps.entity.Blog;

public interface BlogRepo extends CrudRepository<Blog, Long>{
}
