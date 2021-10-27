package com.hola.holalandwork.service.impl;

import com.hola.holalandwork.entity.WorkPaymentMethod;
import com.hola.holalandwork.repository.WorkPaymentMethodRepository;
import com.hola.holalandwork.service.WorkPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkPaymentMethodServiceImpl implements WorkPaymentMethodService {

    private final WorkPaymentMethodRepository workPaymentMethodRepository;

    @Autowired
    public WorkPaymentMethodServiceImpl(WorkPaymentMethodRepository workPaymentMethodRepository) {
        this.workPaymentMethodRepository = workPaymentMethodRepository;
    }

    @Override
    public List<WorkPaymentMethod> getAll() throws DataAccessException {
        return workPaymentMethodRepository.getAll();
    }

    @Override
    public WorkPaymentMethod getOne(int id) throws DataAccessException {
        return workPaymentMethodRepository.getOne(id);
    }
}
