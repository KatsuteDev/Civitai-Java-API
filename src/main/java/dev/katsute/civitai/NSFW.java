package dev.katsute.civitai;

public enum NSFW {

    None    ("None"),
    Soft    ("Soft"),
    Mature  ("Mature"),
    X       ("X");

    private final String value;

    NSFW(final String value){
        this.value = value;
    }

    @Override
    public final String toString() {
        return name();
    }

}