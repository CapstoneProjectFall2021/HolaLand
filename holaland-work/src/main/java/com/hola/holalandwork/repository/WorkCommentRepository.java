package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkComment;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkCommentRepository {

    List<WorkComment> getAll() throws DataAccessException;

    WorkComment getOne(int id) throws DataAccessException;
}
