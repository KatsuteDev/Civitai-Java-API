package dev.katsute.civitai.query;

public enum Period {

    AllTime ("AllTime"),
    Year    ("Year"),
    Month   ("Month"),
    Week    ("Week"),
    Day     ("Day");

    private final String value;

    Period(final String value){
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
