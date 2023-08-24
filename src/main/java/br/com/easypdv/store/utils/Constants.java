package br.com.easypdv.store.utils;

public class Constants {
    public static final String CNPJ_REGEX = "^[0-9]{14}$";
    public static final String PHONE_REGEX = "^[0-9]{10,11}$";
    public static final String ZIP_CODE_REGEX = "^[0-9]{8}$";
    public static final String EMAIL_REGEX = "^.{1,}@.{1,}(.com|.com.br)$";
    public static final String SERVER_ERROR_MSG = "An internal error ocurred! Please, try again later!";
}
