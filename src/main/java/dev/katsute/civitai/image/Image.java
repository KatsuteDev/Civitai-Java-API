package dev.katsute.civitai.image;

import java.util.Date;

import dev.katsute.civitai.Creator;
import dev.katsute.civitai.query.NSFW;

public abstract class Image {

    private Image(){};

    //

    public abstract int getId();

    public abstract String getLink();

    public abstract String getHash();

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract boolean isNSFW();

    public abstract NSFW getNSFW();

    public abstract Date getCreatedAt();

    public abstract int getPostId();

    public abstract Creator getCreator();

    public abstract class Stats {

        public abstract int getCryCount();

        public abstract int getLaughCount();

        public abstract int getLikeCount();

        public abstract int getHeartCount();

        public abstract int getCommentCount();

    }

}
