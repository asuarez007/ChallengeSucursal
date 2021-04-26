package com.api.sucursal.service;

import com.api.sucursal.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public Object findAll() {
        return sucursalRepository.findAll();
    }

} 
