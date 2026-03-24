package dev.katsute.civitai;

import dev.katsute.civitai.model.Model;
import dev.katsute.civitai.model.ModelVersion;
import dev.katsute.civitai.query.CreatorQuery;
import dev.katsute.civitai.query.ImageQuery;
import dev.katsute.civitai.query.ModelQuery;
import dev.katsute.civitai.query.TagQuery;

public abstract class Civitai {

    Civitai(){};

    public static Civitai authenticate(final String token){
        return new CivitaiImpl(token);
    }

    //

    public abstract Creator getCreator(final int id);

    public abstract CreatorQuery queryCreators();

    public abstract ImageQuery queryImages();

    public abstract ModelQuery queryModels();

    public abstract Model getModel(final int id);

    public abstract ModelVersion getModelVersion(final int id);
    public abstract ModelVersion getModelVersion(final String hash);

    public abstract TagQuery queryTags();

    //

    public abstract String queueImage(final String options);

    public abstract String getJob(final int job);
    public abstract String getJob(final String token);

    public abstract String queryJob(final String query);

    public abstract boolean cancelJob(final String id);

    public abstract String getModelCoverage(final String model);
    public abstract String getModelCoverage(final String[] models);

}
