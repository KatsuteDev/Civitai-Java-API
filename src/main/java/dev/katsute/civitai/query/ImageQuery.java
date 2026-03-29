package dev.katsute.civitai.query;

import dev.katsute.civitai.image.Image;

public abstract class ImageQuery extends Query<Image,ImageQuery> {

    public ImageQuery(){};

    public abstract ImageQuery modelId(final int id);

    public abstract ImageQuery modelVersionId(final int id);

    public abstract ImageQuery username(final String username);

    public abstract ImageQuery nsfw();

    public abstract ImageQuery nsfw(final NSFW nsfw);

    public abstract ImageQuery nsfw(final boolean nsfw);

    public abstract ImageQuery sort(final Sort sort);

    public abstract ImageQuery period(final Period period);

}
