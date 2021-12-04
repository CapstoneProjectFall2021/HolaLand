package com.hola.holalandcore.service.impl;

import com.hola.holalandcore.entity.UserAddress;
import com.hola.holalandcore.repository.UserAddressRepository;
import com.hola.holalandcore.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressRepository userAddressRepository;

    @Autowired
    public UserAddressServiceImpl(UserAddressRepository userAddressRepository) {
        this.userAddressRepository = userAddressRepository;
    }

    @Override
    public List<UserAddress> getAllAddressByUserDetailId(int id) throws DataAccessException {
        return userAddressRepository.getAllAddressByUserDetailId(id);
    }

    @Override
    public List<UserAddress> getAllAddressByUserId(int id) throws DataAccessException {
        return userAddressRepository.getAllAddressByUserId(id);
    }

    @Override
    public List<UserAddress> getCurrentDefaultAddressByUserId(int userId) throws DataAccessException {
        return userAddressRepository.getCurrentDefaultAddressByUserId(userId);
    }

    @Override
    public boolean save(UserAddress obj) throws DataAccessException {
        return userAddressRepository.save(obj);
    }

    @Override
    public boolean update(UserAddress obj) throws DataAccessException {
        return userAddressRepository.update(obj);
    }

    @Override
    public boolean updateDefaultAddress(boolean isDefault, int id) throws DataAccessException {
        // set all address default = 0
        return userAddressRepository.updateDefaultAddress(isDefault, id);
    }

    @Override
    public boolean delete(int id) throws DataAccessException {
        return userAddressRepository.delete(id);
    }
}
