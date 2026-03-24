package dev.katsute.civitai.model;

public enum CommercialUse {

    None    ("None"),
    Image   ("Image"),
    Rent    ("Rent"),
    Sell    ("Sell");

    private final String value;

    CommercialUse(final String value){
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
