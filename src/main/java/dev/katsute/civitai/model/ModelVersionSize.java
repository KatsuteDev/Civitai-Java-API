package dev.katsute.civitai.model;

public enum ModelVersionSize {

    Full("full"),
    Pruned("pruned");

    private final String value;

    ModelVersionSize(final String value){
        this.value = value;
    }

    @Override
    public final String toString() {
        return name();
    }

}
