package dev.katsute.civitai.model;

public enum ModelVersionFP {

    FP16("fp16"),
    FP32("fp32");

    private final String value;

    ModelVersionFP(final String value){
        this.value = value;
    }

    @Override
    public final String toString() {
        return name();
    }

}
