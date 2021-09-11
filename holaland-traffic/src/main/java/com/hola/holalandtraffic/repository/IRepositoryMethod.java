package com.hola.holalandtraffic.repository;

import org.springframework.dao.DataAccessException;

import java.util.List;

public interface IRepositoryMethod<E> {

    List<E> getAll() throws DataAccessException;

    E getOne(int id) throws DataAccessException;

    int save(E obj) throws DataAccessException;

    boolean update(E obj) throws DataAccessException;

    boolean delete(int id) throws DataAccessException;
}
