package dev.katsute.civitai.model.file;

public enum FloatingPoint {

    FP16("fp16", 16),
    FP32("fp32", 32);

    private final String value;
    private final int bits;

    FloatingPoint(final String value, final int bits){
        this.value = value;
        this.bits = bits;
    }

    public final String value(){
        return value;
    }

    public final int bits(){
        return bits;
    }

    @Override
    public final String toString() {
        return name();
    }

}
