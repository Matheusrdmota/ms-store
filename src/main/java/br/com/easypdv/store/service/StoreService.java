package br.com.easypdv.store.service;

import br.com.easypdv.store.dto.StoreCreationDTO;
import br.com.easypdv.store.entity.Store;
import br.com.easypdv.store.entity.StoreBuilder;
import br.com.easypdv.store.repository.StoreRepository;
import br.com.easypdv.store.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StoreService {
    private final StoreRepository repository;

    @Autowired
    public StoreService(StoreRepository repository){
        this.repository = repository;
    }

    public List<Store> fetchStores(){
        return this.repository.findAll();
    }

    public Store getStoreByCnpj(String cnpj){
        Store store = this.repository.findByCnpj(cnpj).orElseThrow(() -> new NoSuchElementException("The store with cnpj " + cnpj + " could not be found!"));
        return store;
    }

    public void createStore(StoreCreationDTO store){
        if(this.repository.findByCnpj(store.getCnpj()).isPresent())
            throw new IllegalArgumentException("There's already a store with this cnpj!");

        Store storeToBeCreated = new StoreBuilder()
                                    .withName(store.getName())
                                    .withCorporateName(store.getCorporateName())
                                    .withCnpj(store.getCnpj())
                                    .withEmail(store.getEmail())
                                    .withState(store.getState())
                                    .withCity(store.getCity())
                                    .withAddress(store.getAddress())
                                    .withZipCode(store.getZipCode())
                                    .withPhoneNumber(store.getPhoneNumber())
                                    .withBillingDate(store.getBillingDate())
                                    .withFeeValue(store.getFeeValue())
                                    .build();

        storeToBeCreated.setCreationDate(LocalDateTime.now());
        storeToBeCreated.setActive(false);

        if(!this.isValidData(storeToBeCreated))
            throw new IllegalArgumentException("Could not create the store. The data informed is invalid!");

        this.repository.save(storeToBeCreated);
    }

    public void updateStore(StoreCreationDTO store){
        Store storeToBeUpdated = this.getStoreByCnpj(store.getCnpj());

        storeToBeUpdated.setFeeValue(store.getFeeValue());
        storeToBeUpdated.setName(store.getName());
        storeToBeUpdated.setAddress(store.getAddress());
        storeToBeUpdated.setEmail(store.getEmail());
        storeToBeUpdated.setBillingDate(store.getBillingDate());
        storeToBeUpdated.setCity(store.getCity());
        storeToBeUpdated.setZipCode(store.getZipCode());
        storeToBeUpdated.setState(store.getState());
        storeToBeUpdated.setPhoneNumber(store.getPhoneNumber());

        if(!this.isValidData(storeToBeUpdated))
            throw new IllegalArgumentException("Could not update the store. The data informed is invalid!");

        this.repository.save(storeToBeUpdated);
    }

    public boolean isValidData(Store store){
        return (
                this.isCnpjValid(store.getCnpj())
                && this.validateRegex(Constants.PHONE_REGEX, store.getPhoneNumber())
                && this.validateRegex(Constants.ZIP_CODE_REGEX, store.getZipCode())
                && this.validateRegex(Constants.EMAIL_REGEX, store.getEmail())
                && store.getFeeValue().compareTo(new BigDecimal(0)) > 0
                );
    }

    public boolean validateRegex(String regex, String value){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        return matcher.find();
    }

    public boolean isCnpjValid(String cnpj){
        return !(!this.validateRegex(Constants.CNPJ_REGEX, cnpj)
                || this.checkVerificatorDigits(Arrays.asList(5,4,3,2,9,8,7,6,5,4,3,2), cnpj) != Integer.parseInt(String.valueOf(cnpj.charAt(12)))
                || this.checkVerificatorDigits(Arrays.asList(6,5,4,3,2,9,8,7,6,5,4,3,2), cnpj) != Integer.parseInt(String.valueOf(cnpj.charAt(13))));
    }

    private Integer checkVerificatorDigits(List<Integer> verificationList, String cnpj){
        int sum = 0;

        for(int i = 0; i < verificationList.size(); i++){
            sum += Integer.parseInt(String.valueOf(cnpj.charAt(i))) * verificationList.get(i);
        }

        int remainder = sum % 11;
        int value = remainder <= 1 ? 0 : 11 - remainder;
        return value;
    }
}
