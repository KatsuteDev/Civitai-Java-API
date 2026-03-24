package dev.katsute.civitai.model;

public enum ModelType {

    Checkpoint          ("Checkpoint"),
    TextualInversion    ("TextualInversion"),
    Hypernetwork        ("Hypernetwork"),
    AestheticGradient   ("AestheticGradient"),
    LORA                ("LORA"),
    Controlnet          ("Controlnet"),
    Poses               ("Poses");

    private final String value;

    ModelType(final String value){
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
