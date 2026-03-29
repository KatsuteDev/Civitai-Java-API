package dev.katsute.civitai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import dev.katsute.civitai.Json.JsonObject;
import dev.katsute.civitai.model.Model;
import dev.katsute.civitai.model.ModelVersion;
import dev.katsute.civitai.query.CreatorQuery;
import dev.katsute.civitai.query.ImageQuery;
import dev.katsute.civitai.query.ModelQuery;
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
            public final CreatorQuery limit(int limit) {
                this.limit = limit;
                return this;
            }

            @Override
            public final CreatorQuery page(int page) {
                this.page = page;
                return this;
            }

            @Override
            public final CreatorQuery query(String query) {
                this.query = query;
                return this;
            }

            @Override
            public final Creator[] search() {
                final JsonObject res = handleResponse(
                    () -> request("https://civitai.com/api/v1/creators", Map.of(
                        "limit", limit != null ? String.valueOf(limit) : null,
                        "page", page != null ? String.valueOf(page) : null,
                        "query", query != null ? query : null
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
                    page -> request("https://civitai.com/api/v1/creators", Map.of(
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
        return null;
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
        return null;
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
                    () -> request("https://civitai.com/api/v1/tags", Map.of(
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
                    page -> request("https://civitai.com/api/v1/tags", Map.of(
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

    private final Response request(final String url){
        return request(url, "GET", Map.of());
    }

    private final Response request(final String url, final Map<String,String> params){
        return request(url, "GET", params);
    }

    private final Response request(final String url, final String method){
        return request(url, method, Map.of());
    }

    private final Response request(final String url, final String method, final Map<String,String> params){
        HttpURLConnection conn;
        try {
            final String URL =
                url +
                (params.isEmpty() ? "" : '?' +
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
