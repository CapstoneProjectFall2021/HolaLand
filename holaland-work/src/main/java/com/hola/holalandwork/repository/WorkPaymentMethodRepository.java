package com.hola.holalandwork.repository;

import com.hola.holalandwork.entity.WorkPaymentMethod;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkPaymentMethodRepository {

    List<WorkPaymentMethod> getAll() throws DataAccessException;

    WorkPaymentMethod getOne(int id) throws DataAccessException;
}
