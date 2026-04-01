package dev.katsute.civitai;

import dev.katsute.civitai.Json.JsonObject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static dev.katsute.civitai.Json.*;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpellCheckingInspection")
final class TestJson {

    public static String readFile(final File file) throws IOException{
        final StringBuilder OUT = new StringBuilder();
        for(final String s : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8))
            OUT.append(s);
        return OUT.toString();
    }

    public static final class ObjectStream {

        private final List<Arguments> args = new ArrayList<>();

        public final ObjectStream add(final Object... object){
            args.add(Arguments.of(object));
            return this;
        }

        public final Stream<Arguments> stream(){
            return args.stream();
        }

    }

    private static JsonObject jsonObject;
    private static List<?> jsonArray;

    @BeforeAll
    static void beforeAll() throws IOException{
        final String map = readFile(new File("src/test/java/resources/map.json")).replaceAll("\\r?\\n", "");
        jsonObject = (JsonObject) parse(map);

        final String arr = readFile(new File("src/test/java/resources/array.json")).replaceAll("\\r?\\n", "");
        jsonArray = (List<?>) parse(arr);
    }

    // map

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("mapProvider")
    final void testMap(final Object expected, final Object actual){
        assertEquals(expected, actual);
    }

    @Test
    final void testMapNull(){
        assertNull(jsonObject.get("null"));
        assertTrue(jsonObject.containsKey("null"));
        assertNull(jsonObject.get("nulls"));
        assertTrue(jsonObject.containsKey("nulls"));
    }

    @Test
    final void testMapBoolean(){
        assertEquals(true, jsonObject.getBoolean("bool"));
        assertEquals(false, jsonObject.getBoolean("bools"));
    }

    @Test
    final void testMapMap(){
        assertEquals("v", jsonObject.getJsonObject("obj").getString("k"));
        assertEquals(0, jsonObject.getJsonObject("cobj").size());
    }

    @SuppressWarnings("DataFlowIssue")
    @Test
    final void testMapArray(){
        assertEquals("str", jsonObject.getStringArray("arr")[0]);
        assertEquals(0, jsonObject.getJsonArray("carr").length);
    }

    private static Stream<Arguments> mapProvider(){
        return new ObjectStream()
            .add(1.0, jsonObject.getDouble("double"))
            .add(-1.0, jsonObject.getDouble("doublen"))
            .add(1.0, jsonObject.getDouble("doubles"))
            .add(1, jsonObject.getInt("int"))
            .add(-1, jsonObject.getInt("intn"))
            .add(1, jsonObject.getInt("ints"))
            .add("string", jsonObject.getString("string"))
            .add("string", jsonObject.getString("strings"))
            .add("str\"ing", jsonObject.getString("str\"ingx"))
            .add("/\\", jsonObject.getString("slash\\"))
            .add("何", jsonObject.getString("何"))
            .add("\\u4f55", jsonObject.getString("\\u4f55"))
            .stream();
    }

    // array

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("arrayProvider")
    final void testArray(final Object object){
        assertTrue(jsonArray.contains(object));
    }

    @Test
    final void testArrayMap(){
        assertEquals("v", ((JsonObject) jsonArray.get(14)).getString("k"));
    }

    @Test
    final void testArrayEmptyMap(){
        assertEquals(0, ((JsonObject) jsonArray.get(15)).size());
    }

    private static Stream<Arguments> arrayProvider(){
        return new ObjectStream()
            .add(1.0)
            .add(1.0)
            .add(-1.0)
            .add(2.0)
            .add(1)
            .add(-1)
            .add(2)
            .add(true)
            .add(false)
            .add((Object) null)
            .add("string")
            .add("str\"ingx")
            .add("/\\")
            .add("何")
            .add("\\u4f55")
            .add(new ArrayList<String>(){{ add("str"); }})
            .add(new ArrayList<String>())
            .stream();
    }

    // malformed

    @ParameterizedTest(name="[{index}] {0}")
    @ValueSource(strings={"", "?", "{", "}", "[", "]", "{{", "}}", "[[", "]]", "{[", "[{", "}]", "]}", "{[}]", "[{]}"})
    final void testMalformed(final String string){
        try{
            parse(string);
        }catch(final JsonSyntaxException e){
            assertEquals(string, e.getRaw());
            return;
        }
        fail("Expected JsonSyntaxException for: \"" + string + '"');
    }

    // newline

    @Test
    final void testNewLine(){
        assertEquals("v", ((JsonObject) parse("{\"k\":\n\"v\"\n}")).getString("k"));
    }

    // null

    @Test
    final void testNull(){
        assertDoesNotThrow(() -> ((JsonObject) parse("{}")).getJsonObject("null"));
        assertNotNull(((JsonObject) parse("{}")).getJsonObject("null"));
        assertDoesNotThrow(() -> ((JsonObject) parse("{}")).getJsonObject("null").getString("null"));
        assertNull(((JsonObject) parse("{}")).getJsonObject("null").getString("null"));
        assertDoesNotThrow(() -> ((JsonObject) parse("{}")).getStringArray("null"));
        assertNull(((JsonObject) parse("{}")).getStringArray("null"));
        assertDoesNotThrow(() -> ((JsonObject) parse("{}")).getJsonArray("null"));
        assertNull(((JsonObject) parse("{}")).getJsonArray("null"));
    }

    // bool

    @Test
    final void testBool(){
        assertEquals(true, ((JsonObject) parse("{\"k\":true}")).getBoolean("k"));
        assertThrows(JsonSyntaxException.class, () -> ((JsonObject) parse("{\"k\":True}")).getBoolean("k"));
        assertEquals(true, ((JsonObject) parse("{\"k\":\"true\"}")).getBoolean("k"));
        assertEquals(true, ((JsonObject) parse("{\"k\":\"True\"}")).getBoolean("k"));
        assertEquals(false, ((JsonObject) parse("{\"k\":false}")).getBoolean("k"));
        assertThrows(JsonSyntaxException.class, () -> ((JsonObject) parse("{\"k\":False}")).getBoolean("k"));
        assertEquals(false, ((JsonObject) parse("{\"k\":\"false\"}")).getBoolean("k"));
        assertEquals(false, ((JsonObject) parse("{\"k\":\"False\"}")).getBoolean("k"));
        assertNull(((JsonObject) parse("{\"k\":null}")).getBoolean("k"));
        assertNull(((JsonObject) parse("{\"k\":\"null\"}")).getBoolean("k"));
        assertNull(((JsonObject) parse("{\"k\":-1}")).getBoolean("k"));
        assertNull(((JsonObject) parse("{\"k\":0}")).getBoolean("k"));
        assertNull(((JsonObject) parse("{\"k\":1}")).getBoolean("k"));
        assertNull(((JsonObject) parse("{}")).getBoolean("k"));
    }

}