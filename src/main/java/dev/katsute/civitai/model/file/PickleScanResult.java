package dev.katsute.civitai.model.file;

public enum PickleScanResult {

    Pending ("Pending"),
    Success ("Success"),
    Danger  ("Danger"),
    Error   ("Error");

    private final String value;

    PickleScanResult(String value) {
        this.value = value;
    }

    public final String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
