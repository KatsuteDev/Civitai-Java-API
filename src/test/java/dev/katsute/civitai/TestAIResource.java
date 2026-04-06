package dev.katsute.civitai;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import dev.katsute.civitai.generate.AIResource;
import dev.katsute.civitai.generate.MalformedResourceException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

final class TestAIResource {

    @Test
    final void testValidResourceParsing() {
        final AIResource r = new AIResource("urn:air:sdmodel:civitai:123@1:meta");
        final String s = r.name();
        assertNotNull(s);
        assertTrue(s.startsWith("urn:urn:air"));
    }

    private static Stream<String> exceptionProvider() {
        return Stream.of(
            "urn:air:sdmodel:civitai:123@1",
            "urn:air:sdmodel:civitai:abc@1:meta",
            "urn:air:sdmodel:civitai:123@x:meta",
            "foo:air:sdmodel:civitai:123@1:meta",
            "urn:foo:sdmodel:civitai:123@1:meta"
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("exceptionProvider")
    final void testMalformedVariants(final String input) {
        assertThrows(MalformedResourceException.class, () -> new AIResource(input));
    }

}