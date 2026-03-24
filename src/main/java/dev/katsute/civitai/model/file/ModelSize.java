package dev.katsute.civitai.model.file;

public enum ModelSize {

    Full("full"),
    Pruned("pruned");

    private final String value;

    ModelSize(final String value){
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
