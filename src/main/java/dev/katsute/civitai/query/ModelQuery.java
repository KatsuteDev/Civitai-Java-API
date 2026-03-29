package dev.katsute.civitai.query;

import dev.katsute.civitai.model.Model;
import dev.katsute.civitai.model.ModelType;

public abstract class ModelQuery extends Query<Model,ModelQuery> {

    public ModelQuery(){};

    public abstract ModelQuery tag(final String tag);

    public abstract ModelQuery username(final String username);

    public abstract ModelQuery types(final ModelType... types);

    public abstract ModelQuery sort(final Sort sort);

    public abstract ModelQuery favorites();

    public abstract ModelQuery favorites(final boolean favorites);

    public abstract ModelQuery hidden();

    public abstract ModelQuery hidden(final boolean hidden);

    public abstract ModelQuery primaryFileOnly();

    public abstract ModelQuery primaryFileOnly(final boolean primaryFileOnly);

    public abstract ModelQuery allowNoCredit();

    public abstract ModelQuery allowNoCredit(final boolean allowNoCredit);

    public abstract ModelQuery allowDifferentLicenses();

    public abstract ModelQuery allowDifferentLicenses(final boolean allowDifferentLicenses);

    public abstract ModelQuery allowCommercialUse();

    public abstract ModelQuery allowCommercialUse(final boolean allowCommercialUse);

    public abstract ModelQuery nsfw();

    public abstract ModelQuery nsfw(final NSFW nsfw);

    public abstract ModelQuery nsfw(final boolean nsfw);

    public abstract ModelQuery supportsGeneration();

    public abstract ModelQuery supportsGeneration(final boolean supportsGeneration);

}
