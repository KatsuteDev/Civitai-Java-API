package dev.katsute.civitai.model;

public enum ModelMode {

    Archived("Archived"),
    TakenDown("TakenDown");

    private final String value;

    ModelMode(final String value){
        this.value = value;
    }

    public final String value(){
        return value;
    }

    @Override
    public final String toString() {
        return name();
    }

}
