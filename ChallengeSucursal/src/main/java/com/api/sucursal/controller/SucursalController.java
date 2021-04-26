package com.api.sucursal.controller;

import com.api.sucursal.exception.ResouceNotFoundException;
import com.api.sucursal.repository.SucursalRepository;
import com.api.sucursal.model.Sucursal;
import com.api.sucursal.model.Distancia;
import com.api.sucursal.util.CalculadorDistancia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SucursalController {

    private final Logger logger = LoggerFactory.getLogger(SucursalController.class);


    @Autowired
    private SucursalRepository sucursalRepository;

    @PostMapping("/sucursales")
    public Sucursal addSubsidiary(@RequestBody Sucursal sucursal) {
        logger.info("Create Subsidiary");
        return sucursalRepository.save(sucursal);
    }

    @GetMapping("/sucursales")
    public ResponseEntity<List<Sucursal>> getAllSubsidiary() {
        logger.info("Find All subsidiary : {}-{}");
        return ResponseEntity.ok(sucursalRepository.findAll());
    }

    @GetMapping("sucursales/{id}")
    public ResponseEntity<Sucursal> findSubsidiaryById(@PathVariable(value = "id") Integer sucursalId) {
        logger.info("Find subsiadiary by id: {}-{}", sucursalId);
        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new ResouceNotFoundException("Subsidiary not found" + sucursalId));
        return ResponseEntity.ok().body(sucursal);
    }


    @GetMapping("/sucursalCercana/{latitud}/{longuitud}")
    public Distancia getSubsidiaryclosestDistance(@PathVariable("latitud") double latitud, @PathVariable("longuitud") double longuitud) {
        logger.info("Find Subsidiary by closest Distance : {} in -{}", latitud, longuitud);
        List<Sucursal> listaSucursales = sucursalRepository.findAll();
        Distancia distancia = CalculadorDistancia.calcularDistancia(latitud, longuitud, listaSucursales);
        return distancia;
    }


}
