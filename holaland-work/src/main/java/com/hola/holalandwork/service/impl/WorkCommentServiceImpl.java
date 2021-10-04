package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkComment;
import com.hola.holalandwork.repository.WorkCommentRepository;
import com.hola.holalandwork.service.WorkCommentService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkCommentServiceImpl implements WorkCommentService {

    private final WorkCommentRepository workCommentRepository;

    public WorkCommentServiceImpl(WorkCommentRepository workCommentRepository) {
        this.workCommentRepository = workCommentRepository;
    }

    @Override
    public List<WorkComment> getAll() throws DataAccessException {
        return workCommentRepository.getAll();
    }
}
