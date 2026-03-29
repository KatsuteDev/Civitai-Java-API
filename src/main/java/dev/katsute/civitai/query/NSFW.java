package dev.katsute.civitai.query;

public enum NSFW {

    None    ("None"),
    Soft    ("Soft"),
    Mature  ("Mature"),
    X       ("X");

    private final String value;

    NSFW(final String value){
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public final String toString() {
        return name();
    }

}
