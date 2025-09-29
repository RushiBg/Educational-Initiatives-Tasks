package com.designpatterns.structural.decorator;

import com.designpatterns.utils.AppLogger;

public class CompressionDecorator extends DataSourceDecorator{
    public CompressionDecorator(DataSource dataSource) {
        super(dataSource);
    }
    @Override
    public void writeData(String data) {
        String compressedData = compress(data);
        AppLogger.getInstance().info("Compressing data.");
        super.writeData(compressedData);
    }

    @Override
    public String readData() {
        String compressedData = super.readData();
        AppLogger.getInstance().info("Decompressing data.");
        return decompress(compressedData);
    }

    private String compress(String data) {
        return data + "[COMPRESSED]";
    }

    private String decompress(String data) {
        return data.replace("[COMPRESSED]", "");
    }
}
