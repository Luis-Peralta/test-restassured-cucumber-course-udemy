import io.restassured.response.Response
import org.testng.annotations.Test
import org.testng.Assert
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import static io.restassured.RestAssured.*;

class Books extends BaseTest{

    @Test()
    void getBookList(){
        Response response = get("/books")
        ArrayList<String> allBooks = response.path("data.title")
        Assert.assertTrue(allBooks.size() > 1, "No books returned")
    }

    @Test
    void bookSchemaIsValid(){
        get("/books")
        .then()
        .assertThat()
        .body(matchesJsonSchemaInClasspath("bookSchema.json"))
    }

    @Test
    void createAndDeleteBook() {
        File bookFile = new File(getClass().getResource("/book.json").toURI())
        Response createResponse =
                given()
                        .body(bookFile)
                        .when()
                        .post("/books")

        String responseID = createResponse.jsonPath().getString("post.book_id")

        Response deleteResponse =
                given()
                    .body(String.format("{\n" +
                            "\t\"book_id\" : %s\"\"\n" +
                            "}", responseID))
                    .when()
                    .delete("/books")
        Assert.assertEquals(deleteResponse.getStatusCode(), 201)
        Assert.assertEquals(deleteResponse.jsonPath().getString("Message"), "Book successfully deleted")

    }

    @Test
    void deleteStatusNoExits() {
        String noExitsBookId = "112121223"

        Response deleteResponse =
                given()
                        .body(String.format("{\n" +
                                "\t\"book_id\" : %s\"\"\n" +
                                "}", noExitsBookId))
                        .when()
                        .delete("/books")
        Assert.assertEquals(deleteResponse.getStatusCode(), 500)
        Assert.assertEquals(deleteResponse.jsonPath().getString("Message"), "No exist Book: " + noExitsBookId)
    }


}
