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
    public List<UserAddress> getAllAddressByUserId(int id) throws DataAccessException {
        return userAddressRepository.getAllAddressByUserId(id);
    }

    @Override
    public UserAddress getOneByUserId(int id) throws DataAccessException {
        return userAddressRepository.getOneByUserId(id);
    }

    @Override
    public boolean save(UserAddress obj) throws DataAccessException {
        List<UserAddress> userAddresses = getAllAddressByUserId(obj.getUserId());
        if(userAddresses.size() == 0) {
            obj.setUserAddressDefault(true);
        } else {
            obj.setUserAddressDefault(false);
        }
        return userAddressRepository.save(obj);
    }

    @Override
    public boolean update(UserAddress obj) throws DataAccessException {
        return userAddressRepository.update(obj);
    }

    @Override
    public boolean updateDefaultAddress(int id, int userId) throws DataAccessException {
        // set all address default = 0
        userAddressRepository.deleteDefaultAddressByUserId(userId);
        return userAddressRepository.updateDefaultAddress(id);
    }

    @Override
    public boolean delete(int id) throws DataAccessException {
        return userAddressRepository.delete(id);
    }

    @Override
    public boolean deleteDefaultAddressByUserId(int userId) throws DataAccessException {
        return userAddressRepository.deleteDefaultAddressByUserId(userId);
    }
}
