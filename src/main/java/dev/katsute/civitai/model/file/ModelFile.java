package dev.katsute.civitai.model.file;

import java.util.Date;

import dev.katsute.civitai.model.ModelVersionFormat;

public abstract class ModelFile {

    private ModelFile(){};

    public abstract int getSizeKB();

    public abstract VirusScanResult getVirusScanResult();

    public abstract PickleScanResult getPickleScanResult();

    public abstract Date getScannedAt();

    public abstract boolean isPrimary();

    public abstract FloatingPoint getFP();

    public abstract ModelSize getSize();

    public abstract ModelVersionFormat getFormat();

}
