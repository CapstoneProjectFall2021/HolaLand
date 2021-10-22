package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkComment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkCommentService {

    List<WorkComment> getAll() throws DataAccessException;
}
