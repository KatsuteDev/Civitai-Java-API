package dev.katsute.civitai.model;

public enum ModelVersionFormat {

    SafeTensor("SafeTensor"),
    PickleTensor("PickleTensor"),
    Other("Other");

    private final String value;

    ModelVersionFormat(final String value){
        this.value = value;
    }

    @Override
    public final String toString() {
        return name();
    }

}