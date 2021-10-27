package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkPaymentMethod;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WorkPaymentMethodService {

    List<WorkPaymentMethod> getAll() throws DataAccessException;

    WorkPaymentMethod getOne(int id) throws DataAccessException;
}
