package br.com.easypdv.store.unitTests;

import br.com.easypdv.store.entity.Store;
import br.com.easypdv.store.service.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {
    @InjectMocks
    private StoreService storeService;

    @Test
    public void ifCnpjIsValidShouldReturnTrue(){
        String validCnpj = "43109324000175";
        assertTrue(this.storeService.isCnpjValid(validCnpj));
    }

    @Test
    public void ifCnpjIsInvalidShouldReturnFalse(){
        String validCnpj = "43109324000174";
        assertFalse(this.storeService.isCnpjValid(validCnpj));
    }

    @Test
    public void ifCnpjHasLengthLessThanFourteenShouldReturnFalse(){
        String validCnpj = "4310932400015";
        assertFalse(this.storeService.isCnpjValid(validCnpj));
    }

    @Test
    public void ifCnpjHasLengthGreaterThanFourteenShouldReturnFalse(){
        String validCnpj = "431093240001555";
        assertFalse(this.storeService.isCnpjValid(validCnpj));
    }

    @Test
    public void ifAllDataIsValidShouldReturnTrue(){
        Store store = new Store(1, "as", "asss", "43109324000175", "matheusrdmota@gmail.com",
                                "CE", "Fortaleza", "rua a", "60410368", "85988053748", LocalDateTime.now(),
                                new BigDecimal(2), LocalDateTime.now(), true);
        assertTrue(this.storeService.isValidData(store));
    }

    @Test
    public void ifStorePhoneIsInvalidShouldReturnFalse(){
        Store store = new Store(1, "as", "asss", "43109324000175", "matheusrdmota@gmail.com",
                "CE", "Fortaleza", "rua a", "60410368", "888053748", LocalDateTime.now(),
                new BigDecimal(2), LocalDateTime.now(), true);
        assertFalse(this.storeService.isValidData(store));
    }

    @Test
    public void ifStoreEmailIsInvalidShouldReturnFalse(){
        Store store = new Store(1, "as", "asss", "43109324000175", "@gmail.com",
                "CE", "Fortaleza", "rua a", "60410368", "85988053748", LocalDateTime.now(),
                new BigDecimal(2), LocalDateTime.now(), true);
        assertFalse(this.storeService.isValidData(store));
    }

    @Test
    public void ifStoreZipCodeIsInvalidShouldReturnFalse(){
        Store store = new Store(1, "as", "asss", "43109324000175", "matheusrdmota@gmail.com",
                "CE", "Fortaleza", "rua a", "6041368", "85988053748", LocalDateTime.now(),
                new BigDecimal(2), LocalDateTime.now(), true);
        assertFalse(this.storeService.isValidData(store));
    }

    @Test
    public void ifStoreFeeValueIsInvalidShouldReturnFalse(){
        Store store = new Store(1, "as", "asss", "43109324000175", "matheusrdmota@gmail.com",
                "CE", "Fortaleza", "rua a", "60410368", "85988053748", LocalDateTime.now(),
                new BigDecimal(-2), LocalDateTime.now(), true);
        assertFalse(this.storeService.isValidData(store));
    }

}
