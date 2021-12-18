package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestBook;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestBookService {

    List<WorkRequestBook> getAll() throws DataAccessException;

    List<WorkRequestBook> getAllByRequestId(int id) throws DataAccessException;

    WorkRequestBook getOne(int id) throws DataAccessException;

    boolean userAcceptRecruiterBooked(WorkRequestBook obj) throws DataAccessException;

    boolean userRejectRecruiterBooked(WorkRequestBook obj) throws DataAccessException;

    boolean save(WorkRequestBook obj) throws DataAccessException;
}
