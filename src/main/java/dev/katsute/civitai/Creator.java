package dev.katsute.civitai;

import dev.katsute.civitai.query.ImageQuery;
import dev.katsute.civitai.query.ModelQuery;

public abstract class Creator {

    private Creator(){};

    //

    public abstract String getUsername();

    public abstract int getModelCount();

    public abstract String getModelsLink();

    //

    public abstract ModelQuery getModels();

    public abstract ImageQuery getImages();

}
