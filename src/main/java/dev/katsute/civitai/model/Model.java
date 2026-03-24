package dev.katsute.civitai.model;

import dev.katsute.civitai.Creator;

public abstract class Model {

    private Model(){};

    public abstract int getId();

    public abstract String getName();

    public abstract String getDescription();

    public abstract boolean isNSFW();

    public abstract String[] getTags();

    public abstract ModelMode getMode();

    public abstract Creator getCreator();

    public abstract class Stats {

        public abstract int getDownloadCount();

        public abstract int getFavoriteCount();

        public abstract int getCommentCount();

        public abstract int getRatingCount();

        public abstract float getRating();

    }

    public abstract ModelVersion[] getVersions();

}
