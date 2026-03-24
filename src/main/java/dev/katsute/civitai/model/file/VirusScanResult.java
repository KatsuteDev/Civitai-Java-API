package dev.katsute.civitai.model.file;

public enum VirusScanResult {

    Pending ("Pending"),
    Success ("Success"),
    Danger  ("Danger"),
    Error   ("Error");

    private final String value;

    VirusScanResult(String value) {
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
