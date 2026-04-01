/*
 * Copyright (C) 2026 Katsute <https://github.com/Katsute>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package dev.katsute.civitai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import dev.katsute.civitai.Json.JsonObject;
import dev.katsute.civitai.image.Image;
import dev.katsute.civitai.model.Model;
import dev.katsute.civitai.model.ModelType;
import dev.katsute.civitai.model.ModelVersion;
import dev.katsute.civitai.query.CreatorQuery;
import dev.katsute.civitai.query.ImageQuery;
import dev.katsute.civitai.query.ModelQuery;
import dev.katsute.civitai.query.NSFW;
import dev.katsute.civitai.query.Period;
import dev.katsute.civitai.query.Sort;
import dev.katsute.civitai.query.TagQuery;
import dev.katsute.civitai.tag.Tag;

final class CivitaiService extends Civitai{

    private final CivitaiObjectService service = new CivitaiObjectService(this);

    private final String token;

    CivitaiService(String token){
        this.token = token;
    }

    // creator

    @Override
    public final CreatorQuery queryCreators() {
        return new CreatorQuery() {
            private Integer limit;
            private Integer page;
            private String query;

            @Override
            public final CreatorQuery limit(final int limit) {
                this.limit = limit;
                return this;
            }

            @Override
            public final CreatorQuery page(final int page) {
                this.page = page;
                return this;
            }

            @Override
            public final CreatorQuery query(final String query) {
                this.query = query;
                return this;
            }

            @Override
            public final Creator[] search() {
                final JsonObject res = handleResponse(
                    () -> request("https://civitai.com/api/v1/creators", map(
                        "limit", limit != null ? String.valueOf(limit) : null,
                        "page", page != null ? String.valueOf(page) : null,
                        "query", query
                    ))
                );

                if(res == null) return null;

                final List<Creator> li = new ArrayList<>();
                for(final JsonObject i : res.getJsonArray("items"))
                    li.add(service.asCreator(i));
                return li.toArray(new Creator[0]);
            }

            @Override
            public final Page<Creator> searchAll() {
                return new PagedIterator<Creator>(
                    page,
                    page -> request("https://civitai.com/api/v1/creators", map(
                        "limit", limit != null ? String.valueOf(limit) : null,
                        "page", page != null ? String.valueOf(page) : null,
                        "query", query != null ? query : null
                    )).json(),
                    i -> service.asCreator(i)
                );
            }
        };
    }

    // image

    @Override
    public final ImageQuery queryImages() {
        return new ImageQuery() {
            private Integer modelId;
            private Integer modelVersionId;
            private String username;
            private String nsfw;
            private String sort;
            private String period;
            private Integer limit;
            private Integer page;
            private String query;

            @Override
            public final ImageQuery modelId(final int id) {
                this.modelId = id;
                return this;
            }

            @Override
            public final ImageQuery modelVersionId(final int id) {
                this.modelVersionId = id;
                return this;
            }

            @Override
            public final ImageQuery username(final String username) {
                this.username = username;
                return this;
            }

            @Override
            public final ImageQuery nsfw() {
                this.nsfw = "true";
                return this;
            }

            @Override
            public final ImageQuery nsfw(final NSFW nsfw) {
                this.nsfw = nsfw != null ? nsfw.value() : null;
                return this;
            }

            @Override
            public final ImageQuery nsfw(final boolean nsfw) {
                this.nsfw = nsfw ? "true" : "false";
                return this;
            }

            @Override
            public final ImageQuery sort(final Sort sort) {
                this.sort = sort != null ? sort.value() : null;
                return this;
            }

            @Override
            public final ImageQuery period(final Period period) {
                this.period = period != null ? period.value() : null;
                return this;
            }

            @Override
            public final ImageQuery limit(final int limit) {
                this.limit = limit;
                return this;
            }

            @Override
            public final ImageQuery page(final int page) {
                this.page = page;
                return this;
            }

            @Override
            public final ImageQuery query(final String query) {
                this.query = query;
                return this;
            }

            @Override
            public final Image[] search() {
                final JsonObject res = handleResponse(
                    () -> request("https://civitai.com/api/v1/images", map(
                        "modelId", modelId != null ? String.valueOf(modelId) : null,
                        "modelVersionId", modelVersionId != null ? String.valueOf(modelVersionId) : null,
                        "username", username,
                        "nsfw", nsfw,
                        "sort", sort,
                        "period", period,
                        "limit", limit != null ? String.valueOf(limit) : null,
                        "page", page != null ? String.valueOf(page) : null,
                        "query", query
                    ))
                );

                if(res == null) return null;

                final List<Image> li = new ArrayList<>();
                for(final JsonObject i : res.getJsonArray("items"))
                    li.add(service.asImage(i));
                return li.toArray(new Image[0]);
            }

            @Override
            public final Page<Image> searchAll() {
                return new PagedIterator<Image>(
                    page,
                    page -> request("https://civitai.com/api/v1/images", map(
                        "modelId", modelId != null ? String.valueOf(modelId) : null,
                        "modelVersionId", modelVersionId != null ? String.valueOf(modelVersionId) : null,
                        "username", username,
                        "nsfw", nsfw,
                        "sort", sort,
                        "period", period,
                        "limit", limit != null ? String.valueOf(limit) : null,
                        "page", page != null ? String.valueOf(page) : null,
                        "query", query
                    )).json(),
                    i -> service.asImage(i)
                );
            }

        };
    }

    // model

    @Override
    public final Model getModel(final int id) {
        return service.asModel(
            handleResponse(() -> request("https://civitai.com/api/v1/models/" + id))
        );
    }


    @Override
    public final ModelQuery queryModels() {
        return new ModelQuery() {
            private String tag;
            private String username;
            private String[] types;
            private String sort;
            private Boolean favorites;
            private Boolean hidden;
            private Boolean primaryFileOnly;
            private Boolean allowNoCredit;
            private Boolean allowDifferentLicenses;
            private Boolean allowCommercialUse;
            private String nsfw;
            private Boolean supportsGeneration;
            private Integer limit;
            private Integer page;
            private String query;

            @Override
            public final ModelQuery tag(final String tag) {
                this.tag = tag;
                return this;
            }

            @Override
            public final ModelQuery username(final String username) {
                this.username = username;
                return this;
            }

            @Override
            public final ModelQuery types(final ModelType... types) {
                this.types = types != null && types.length > 0 ? Arrays.stream(types).map(ModelType::value).toArray(String[]::new) : null;
                return this;
            }

            @Override
            public final ModelQuery sort(final Sort sort) {
                this.sort = sort != null ? sort.value() : null;
                return this;
            }

            @Override
            public final ModelQuery favorites() {
                this.favorites = true;
                return this;
            }

            @Override
            public final ModelQuery favorites(final boolean favorites) {
                this.favorites = favorites;
                return this;
            }

            @Override
            public final ModelQuery hidden() {
                this.hidden = true;
                return this;
            }

            @Override
            public final ModelQuery hidden(final boolean hidden) {
                this.hidden = hidden;
                return this;
            }

            @Override
            public final ModelQuery primaryFileOnly() {
                this.primaryFileOnly = true;
                return this;
            }

            @Override
            public final ModelQuery primaryFileOnly(final boolean primaryFileOnly) {
                this.primaryFileOnly = primaryFileOnly;
                return this;
            }

            @Override
            public final ModelQuery allowNoCredit() {
                this.allowNoCredit = true;
                return this;
            }

            @Override
            public final ModelQuery allowNoCredit(final boolean allowNoCredit) {
                this.allowNoCredit = allowNoCredit;
                return this;
            }

            @Override
            public final ModelQuery allowDifferentLicenses() {
                this.allowDifferentLicenses = true;
                return this;
            }

            @Override
            public final ModelQuery allowDifferentLicenses(final boolean allowDifferentLicenses) {
                this.allowDifferentLicenses = allowDifferentLicenses;
                return this;
            }

            @Override
            public final ModelQuery allowCommercialUse() {
                this.allowCommercialUse = true;
                return this;
            }

            @Override
            public final ModelQuery allowCommercialUse(final boolean allowCommercialUse) {
                this.allowCommercialUse = allowCommercialUse;
                return this;
            }

            @Override
            public final ModelQuery nsfw() {
                this.nsfw = "true";
                return this;
            }

            @Override
            public final ModelQuery nsfw(final NSFW nsfw) {
                this.nsfw = nsfw != null ? nsfw.value() : null;
                return this;
            }

            @Override
            public final ModelQuery nsfw(final boolean nsfw) {
                this.nsfw = nsfw ? "true" : "false";
                return this;
            }

            @Override
            public final ModelQuery supportsGeneration() {
                this.supportsGeneration = true;
                return this;
            }

            @Override
            public final ModelQuery supportsGeneration(final boolean supportsGeneration) {
                this.supportsGeneration = supportsGeneration;
                return this;
            }

            @Override
            public final ModelQuery limit(final int limit) {
                this.limit = limit;
                return this;
            }

            @Override
            public final ModelQuery page(final int page) {
                this.page = page;
                return this;
            }

            @Override
            public final ModelQuery query(final String query) {
                this.query = query;
                return this;
            }

            @Override
            public final Model[] search() {
                final JsonObject res = handleResponse(
                    () -> request("https://civitai.com/api/v1/models", map(
                        "tag", tag,
                        "username", username,
                        "types", types != null ? String.join(",", types) : null,
                        "sort", sort,
                        "favorites", favorites != null ? favorites.toString() : null,
                        "hidden", hidden != null ? hidden.toString() : null,
                        "primaryFileOnly", primaryFileOnly != null ? primaryFileOnly.toString() : null,
                        "allowNoCredit", allowNoCredit != null ? allowNoCredit.toString() : null,
                        "allowDifferentLicenses", allowDifferentLicenses != null ? allowDifferentLicenses.toString() : null,
                        "allowCommercialUse", allowCommercialUse != null ? allowCommercialUse.toString() : null,
                        "nsfw", nsfw,
                        "supportsGeneration", supportsGeneration != null ? supportsGeneration.toString() : null,
                        "limit", limit != null ? String.valueOf(limit) : null,
                        "page", page != null ? String.valueOf(page) : null,
                        "query", query
                    ))
                );

                if(res == null) return null;

                final List<Model> li = new ArrayList<>();
                for(final JsonObject i : res.getJsonArray("items"))
                    li.add(service.asModel(i));
                return li.toArray(new Model[0]);
            }

            public final Page<Model> searchAll() {
                return new PagedIterator<Model>(
                    page,
                    page -> request("https://civitai.com/api/v1/models", map(
                        "tag", tag,
                        "username", username,
                        "types", types != null ? String.join(",", types) : null,
                        "sort", sort,
                        "favorites", favorites != null ? favorites.toString() : null,
                        "hidden", hidden != null ? hidden.toString() : null,
                        "primaryFileOnly", primaryFileOnly != null ? primaryFileOnly.toString() : null,
                        "allowNoCredit", allowNoCredit != null ? allowNoCredit.toString() : null,
                        "allowDifferentLicenses", allowDifferentLicenses != null ? allowDifferentLicenses.toString() : null,
                        "allowCommercialUse", allowCommercialUse != null ? allowCommercialUse.toString() : null,
                        "nsfw", nsfw,
                        "supportsGeneration", supportsGeneration != null ? supportsGeneration.toString() : null,
                        "limit", limit != null ? String.valueOf(limit) : null,
                        "page", page != null ? String.valueOf(page) : null,
                        "query", query
                    )).json(),
                    i -> service.asModel(i)
                );
            }
        };
    }

    @Override
    public final ModelVersion getModelVersion(final int id) {
        return service.asModelVersion(
            handleResponse(() -> request("https://civitai.com/api/v1/models-version/" + id))
        );
    }

    @Override
    public final ModelVersion getModelVersion(final String hash) {
        return service.asModelVersion(
            handleResponse(() -> request("https://civitai.com/api/v1/models-versions/by-hash/" + hash))
        );
    }

    // tags

    @Override
    public final TagQuery queryTags() {
        return new TagQuery() {
            private Integer limit;
            private Integer page;
            private String query;

            @Override
            public final TagQuery limit(int limit) {
                this.limit = limit;
                return this;
            }

            @Override
            public final TagQuery page(int page) {
                this.page = page;
                return this;
            }

            @Override
            public final TagQuery query(String query) {
                this.query = query;
                return this;
            }

            @Override
            public final Tag[] search() {
                final JsonObject res = handleResponse(
                    () -> request("https://civitai.com/api/v1/tags", map(
                        "limit", limit != null ? String.valueOf(limit) : null,
                        "page", page != null ? String.valueOf(page) : null,
                        "query", query != null ? query : null
                    ))
                );

                if(res == null) return null;

                final List<Tag> li = new ArrayList<>();
                for(final JsonObject i : res.getJsonArray("items"))
                    li.add(service.asTag(i));
                return li.toArray(new Tag[0]);
            }

            @Override
            public final Page<Tag> searchAll() {
                return new PagedIterator<Tag>(
                    page,
                    page -> request("https://civitai.com/api/v1/tags", map(
                        "limit", limit != null ? String.valueOf(limit) : null,
                        "page", page != null ? String.valueOf(page) : null,
                        "query", query != null ? query : null
                    )).json(),
                    i -> service.asTag(i)
                );
            }
        };
    }

    // generation

    @Override
    public final String queueImage(final String options) {
        return null;
    }


    @Override
    public final String getModelCoverage(final String model) {
        return null;
    }


    @Override
    public final String getModelCoverage(final String[] models) {
        return null;
    }


    @Override
    public final String getJob(final int job) {
        return null;
    }


    @Override
    public final String getJob(final String token) {
        return null;
    }


    @Override
    public final String queryJob(final String query) {
        return null;
    }


    @Override
    public final boolean cancelJob(final String id) {
        return false;
    }

    // iter

    private static class PagedIterator<T> extends Page<T> {

        private final Function<Integer,JsonObject> pageSupplier;
        private final Function<JsonObject,T> listAdapter;

        private final AtomicInteger page = new AtomicInteger(0);
        private final AtomicBoolean hasNextPage = new AtomicBoolean(true);

        PagedIterator(
            final Integer page,
            final Function<Integer,JsonObject> pageSupplier,
            final Function<JsonObject,T> listAdapter
        ){
            this.pageSupplier = pageSupplier;
            this.listAdapter  = listAdapter;

            this.page.set(page);
            list = getNextPage();
            size = list == null ? 0 : list.size();
        }

        @Override
        final boolean hasNextPage() {
            return hasNextPage.get();
        }

        @Override
        synchronized final List<T> getNextPage() {
            final JsonObject obj = pageSupplier.apply(page.incrementAndGet());

            final List<T> list = new ArrayList<>();

            for(final JsonObject i : obj.getJsonArray("items")){
                list.add(listAdapter.apply(i));
            }

            if(obj.getInt("currentPage") >= obj.getInt("totalPages")){
                hasNextPage.set(false);
            }

            return list;
        }
    }

    // http request

    private class Response {

        final HttpURLConnection connection;
        final String body;

        Response(HttpURLConnection connection, String body) {
            this.connection = connection;
            this.body = body;
        }

        JsonObject json(){
            return (JsonObject) Json.parse(body);
        }

    }

    // [{}|\\^\[\]`]
    private static final Pattern blockedURI = Pattern.compile("[{}|\\\\^\\[\\]`]");

    private static final Function<MatchResult,String> encoder = (final MatchResult matchResult) -> {
        final char ch = matchResult.group().charAt(0);
        switch(ch){
            case '{':
                return "%7B";
            case '}':
                return "%7D";
            case '|':
                return "%7C";
            case '\\':
                return "%5C";
            case '^':
                return "%5E";
            case '[':
                return "%5B";
            case ']':
                return "%5D";
            case '`':
                return "%60";
            default:
                return matchResult.group(0);
        }
    };

    private final Map<String,String> map(final String... args){
        final Map<String,String> map = new HashMap<>();
        for(int i = 0; i < args.length - 1; i += 2){
            if(args[i + 1] != null)
                map.put(args[i], args[i + 1]);
        }
        return map;
    }

    private final Response request(final String url){
        return request(url, "GET", null);
    }

    private final Response request(final String url, final Map<String,String> params){
        return request(url, "GET", params);
    }

    private final Response request(final String url, final String method){
        return request(url, method, null);
    }

    private final Response request(final String url, final String method, final Map<String,String> params){
        HttpURLConnection conn;
        try {
            final String URL =
                url +
                (params == null || params.isEmpty() ? "" : '?' +
                    params
                        .entrySet()
                        .stream()
                        .filter(e -> e.getValue() != null)
                        .map(e -> e.getKey() + '=' + e.getValue()).collect(Collectors.joining("&"))
                );

            conn = (HttpURLConnection) URI.create(Regex9.replaceAll(URL, blockedURI.matcher(URL), encoder)).toURL().openConnection();

            conn.setRequestProperty("Cache-Control", "no-cache, no-store, must-revalidate");
            conn.setRequestProperty("Accept", "application/json; charset=UTF-8");
            conn.setConnectTimeout(10_000);
            conn.setReadTimeout(10_000);

            conn.setRequestProperty("Authorization", "Bearer " + token);

            conn.setRequestMethod(method);

            String body;
            try(final BufferedReader IN = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))){
                String buffer;
                final StringBuilder OUT = new StringBuilder();
                while((buffer = IN.readLine()) != null)
                    OUT.append(buffer);
                body = OUT.toString();
            }catch(final IOException ignored){
                body = "{}";
            }finally{
                conn.disconnect();
            }

            return new Response(conn, body);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    // http response

    private interface ThrowableSupplier<T,E extends Throwable> {

        T get() throws E;

    }

    private JsonObject handleResponse(final ThrowableSupplier<Response,IOException> supplier){
        final Response res = handleResponseCodes(supplier);
        try {
            return res.connection.getResponseCode() == HttpURLConnection.HTTP_OK ? (JsonObject) res.json() : null;
        } catch (final IOException ignored) {
            return null;
        }
    }

    private Response handleResponseCodes(final ThrowableSupplier<Response,IOException> supplier){
        try{
            final Response res = supplier.get();
            final int code = res.connection.getResponseCode();

            switch(code){
                case HttpURLConnection.HTTP_OK:
                    return res;
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    throw new InvalidTokenException("The token provided is invalid");
                default:
                    throw new HttpException(res.connection.getURL().toString(), code);
            }
        }catch(final IOException e){
            throw new UncheckedIOException(e);
        }
    }

}