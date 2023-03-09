package codetest;

import codetest.PO.Location;
import codetest.util.JSONLocationBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

class JSONLocationBuilderTest {

    @Test
    public void test1() throws IOException, URISyntaxException {
        String json = new String(Files.readAllBytes(Path.of(ClassLoader.getSystemResource("interchanges.json").toURI())), StandardCharsets.UTF_8);
        JSONLocationBuilder builder = new JSONLocationBuilder(json);
        Map<Integer, Location> result = builder.getLocationList();
        System.out.println(result);

    }

}