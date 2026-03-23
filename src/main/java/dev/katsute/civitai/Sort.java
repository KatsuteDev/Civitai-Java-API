package dev.katsute.civitai;

public enum Sort {

    MostReactions   ("Most Reactions"),
    MostComments    ("Most Comments"),
    Newest          ("Newest");

    private final String value;

    Sort(final String value){
        this.value = value;
    }

    @Override
    public final String toString() {
        return name();
    }

}
