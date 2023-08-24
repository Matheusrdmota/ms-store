package br.com.easypdv.store.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StoreBuilder {
    private Store store = new Store();

    public StoreBuilder withName(String name){
        this.store.setName(name);
        return this;
    }

    public StoreBuilder withCorporateName(String corporateName){
        this.store.setCorporateName(corporateName);
        return this;
    }

    public StoreBuilder withCnpj(String cnpj){
        this.store.setCnpj(cnpj);
        return this;
    }

    public StoreBuilder withEmail(String email){
        this.store.setEmail(email);
        return this;
    }

    public StoreBuilder withState(String state){
        this.store.setState(state);
        return this;
    }

    public StoreBuilder withCity(String city){
        this.store.setCity(city);
        return this;
    }

    public StoreBuilder withAddress(String address){
        this.store.setAddress(address);
        return this;
    }

    public StoreBuilder withZipCode(String zipCode){
        this.store.setZipCode(zipCode);
        return this;
    }

    public StoreBuilder withPhoneNumber(String phoneNumber){
        this.store.setPhoneNumber(phoneNumber);
        return this;
    }

    public StoreBuilder withBillingDate(LocalDateTime billingDate){
        this.store.setBillingDate(billingDate);
        return this;
    }

    public StoreBuilder withFeeValue(BigDecimal feeValue){
        this.store.setFeeValue(feeValue);
        return this;
    }

    public Store build(){
        return this.store;
    }
}
