package com.spotify.oauth2.tests;

import org.testng.annotations.Test;
import static com.spotify.oauth2.tests.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.tests.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.*;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    @Test
    public void shouldBeAbleToCreatePlaylist() {
        String payload = "{\n" +
                "  \"name\": \"New Playlist\",\n" +
                "  \"description\": \"New playlist description\",\n" +
                "  \"public\": false\n" +
                "}";
        given(getRequestSpec()).
                body(payload).
                when().post("/users/abdallacarlos/playlists").
                then().spec(getResponseSpec()).
                assertThat().
                statusCode(201).
                body("name", equalTo("New Playlist"),
                        "description", equalTo("New playlist description"),
                        "public", equalTo(false));
    }

    @Test
    public void shouldBeAbleToGetPlaylist() {

        given(getRequestSpec()).
                when().get("/playlists/5PBNAlZewjhV6SBFwM0O0U").
                then().spec(getResponseSpec()).
                assertThat().
                statusCode(200).
                body("name", equalTo("2nd Updated playlist name"),
                        "description", equalTo("2nd Updated playlist description"),
                        "public", equalTo(false));
    }

    @Test
    public void shouldBeAbleToUpdatePlaylist() {
        String payload = "{\n" +
                "  \"name\": \"test Updated playlist name\",\n" +
                "  \"description\": \"test Updated playlist description\",\n" +
                "  \"public\": false\n" +
                "}";
        given(getRequestSpec()).
                body(payload).
                when().put("/playlists/3EN32WPvRQeLOWT172zSyT").
                then().spec(getResponseSpec()).
                assertThat().
                statusCode(200);
    }

    @Test
    public void shouldNotBeAbleToCreatePlaylistWithoutName() {
        String payload = "{\n" +
                "  \"name\": \"\",\n" +
                "  \"description\": \"New playlist description\",\n" +
                "  \"public\": false\n" +
                "}";
        given(getRequestSpec()).
                body(payload).
                when().post("/users/abdallacarlos/playlists").
                then().spec(getResponseSpec()).
                assertThat().
                statusCode(400).
                body("error.status", equalTo(400),
                        "error.message", equalTo("Missing required field: name"));
    }

    @Test
    public void shouldNotBeAbleToCreatePlaylistWithExpiredToken() {
        String payload = "{\n" +
                "  \"name\": \"New Playlist\",\n" +
                "  \"description\": \"New playlist description\",\n" +
                "  \"public\": false\n" +
                "}";
        given().
                baseUri("https://api.spotify.com").
                basePath("/v1").
                header("Authorization", "Bearer " + "InvalidAccessToken").
                contentType(JSON).
                log().all().
                body(payload).
                when().post("/users/abdallacarlos/playlists").
                then().spec(getResponseSpec()).
                assertThat().
                statusCode(401).
                body("error.status", equalTo(401),
                        "error.message", equalTo("Invalid access token"));
    }

}
