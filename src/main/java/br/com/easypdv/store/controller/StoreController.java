package br.com.easypdv.store.controller;

import br.com.easypdv.store.dto.StoreCreationDTO;
import br.com.easypdv.store.entity.Store;
import br.com.easypdv.store.service.StoreService;
import br.com.easypdv.store.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<Store>> fetchStores(){
        try{
            return ResponseEntity.ok(this.storeService.fetchStores());
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_MSG, e);
        }
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<Store> getStore(@PathVariable String cnpj){
        try{
            return ResponseEntity.ok(this.storeService.getStoreByCnpj(cnpj));
        }catch(NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_MSG, e);
        }
    }

    @PostMapping
    public void createStore(@RequestBody StoreCreationDTO store){
        try {
            this.storeService.createStore(store);
        }catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_MSG, e);
        }
    }

    @PutMapping
    public void updateStore(@RequestBody StoreCreationDTO store) {
        try{
            this.storeService.updateStore(store);
        }catch(NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_MSG, e);
        }
    }
}
