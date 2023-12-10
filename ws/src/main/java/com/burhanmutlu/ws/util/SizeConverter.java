package com.burhanmutlu.ws.util;

import org.springframework.context.annotation.Bean;

import java.text.DecimalFormat;

public class SizeConverter {

    private String sizeType;

    private double result;

    private long byteVal;

    private final int MIN_KB = 1024;
    private final int MIN_MB = 1024*1024;
    private final int MIN_GB = 1024*1024*1024;

    public SizeConverter() {
        sizeType = "";
        result = 0.0;
        byteVal = 0L;
    }

    public double convertToKB(long val) {
        sizeType = "KB";
        result = (double) val/MIN_KB;
        byteVal = val;
        return result;
    }

    public double convertToMB(long val) {
        sizeType = "MB";
        result = (double) val/MIN_MB;
        byteVal = val;
        return result;
    }

    public double convertToGB(long val) {
        sizeType = "GB";
        result = (double) val/MIN_GB;
        byteVal = val;
        return result;
    }

    public double autoConvert(long val) {
        if(val >= 0 && val <= MIN_MB) {
            return convertToKB(val);
        } else if(val >= MIN_MB && val <= MIN_GB) {
            return convertToMB(val);
        } else if(val >= MIN_GB) {
            return convertToGB(val);
        } else {
            return 0.0;
        }
    }

    public String getSizeString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String strResult = decimalFormat.format(result);
        return strResult + " " + sizeType + " (" + byteVal + " bayt)";
    }

}
