package com.designpatterns.structural.decorator;


import com.designpatterns.utils.AppLogger;

import java.util.Base64;

public class EncryptionDecorator extends DataSourceDecorator{
    public EncryptionDecorator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void writeData(String data) {
        String encryptedData = encode(data);
        AppLogger.getInstance().info("Encrypting data.");
        super.writeData(encryptedData);
    }

    @Override
    public String readData() {
        String encryptedData = super.readData();
        AppLogger.getInstance().info("Decrypting data.");
        return decode(encryptedData);
    }

    private String encode(String data) {
        byte[] encryptedData = data.getBytes();
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    private String decode(String encryptedData) {
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        return new String(decodedData);
    }
}
