package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkRequestBook;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestBookRepository {

    List<WorkRequestBook> getAll() throws DataAccessException;

    List<WorkRequestBook> getAllByRequestId(int id) throws DataAccessException;

    WorkRequestBook getOne(int id) throws DataAccessException;

    boolean updateStatusRequestByUserIdAndFindJobId(WorkRequestBook obj) throws DataAccessException;

    boolean rejectAllRequestByFindJobId(WorkRequestBook obj) throws DataAccessException;

    boolean save(WorkRequestBook obj) throws DataAccessException;
}
