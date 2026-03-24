package dev.katsute.civitai.query;

public enum Sort {

    MostReactions   ("Most Reactions"),
    MostComments    ("Most Comments"),
    Newest          ("Newest");

    private final String value;

    Sort(final String value){
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
