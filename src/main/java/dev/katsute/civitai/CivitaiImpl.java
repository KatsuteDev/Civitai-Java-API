package dev.katsute.civitai;

import dev.katsute.civitai.model.Model;
import dev.katsute.civitai.model.ModelVersion;
import dev.katsute.civitai.query.CreatorQuery;
import dev.katsute.civitai.query.ImageQuery;
import dev.katsute.civitai.query.ModelQuery;
import dev.katsute.civitai.query.TagQuery;

class CivitaiImpl extends Civitai{

    private final String token;

    CivitaiImpl(String token){
        this.token = token;
    }

    @Override
    public Creator getCreator(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Model getModel(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ModelVersion getModelVersion(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ModelVersion getModelVersion(String hash) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CreatorQuery queryCreators() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImageQuery queryImages() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ModelQuery queryModels() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TagQuery queryTags() {
        // TODO Auto-generated method stub
        return null;
    }



}
