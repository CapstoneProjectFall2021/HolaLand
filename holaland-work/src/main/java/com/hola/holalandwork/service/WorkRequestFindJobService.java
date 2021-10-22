package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestFindJob;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkRequestFindJobService {

    List<WorkRequestFindJob> getAll() throws DataAccessException;

    WorkRequestFindJob getOne(int id) throws DataAccessException;

    List<WorkRequestFindJob> getAllByType(int typeId) throws DataAccessException;

    List<WorkRequestFindJob> getAllSaveDraftByUserId(int id) throws DataAccessException;

    List<WorkRequestFindJob> getAllPostedByUserId(int id) throws DataAccessException;
}
