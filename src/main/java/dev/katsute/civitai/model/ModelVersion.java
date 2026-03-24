package dev.katsute.civitai.model;

import java.util.Date;

import dev.katsute.civitai.model.file.ModelFile;

public abstract class ModelVersion {

    private ModelVersion(){};

    public abstract int getId();

    public abstract String getName();

    public abstract String getDescription();

    public abstract Date getCreatedAt();

    public abstract String getDownloadUrl();

    public abstract String[] getTrainedWords();

    public abstract ModelFile[] getFiles();

}
