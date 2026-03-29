package dev.katsute.civitai;

import dev.katsute.civitai.image.Image;
import dev.katsute.civitai.model.Model;

public abstract class Creator {

    private Creator(){};

    //

    public abstract String getUsername();

    public abstract int getModelCount();

    public abstract String getModelsLink();

    //

    public abstract Page<Model> getModels();

    public abstract Page<Image> getImages();

}
