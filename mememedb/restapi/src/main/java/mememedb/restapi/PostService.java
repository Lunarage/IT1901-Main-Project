package restapi;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_NOT_IMPLEMENTED;
import static java.net.HttpURLConnection.HTTP_OK;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.datastructures.Post;
import java.util.Collection;
import spark.Request;
import spark.Response;

/** Contains methods for communicating post data to/from the database. */
public class PostService {

  private ObjectMapper mapper;

  public PostService() {
    mapper = new ObjectMapper();
  }

  // HTTP Status Codes: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes

  // Requests to "/"
  /**
   * Gets all posts from database and returns them as JSON.
   *
   * @param request Spark Request object containing the http request.
   * @param response Spark Response object containing details of the response.
   * @return A JSON string containing the serialized posts.
   */
  public String getAllPosts(Request request, Response response) {
    response.type("application/json");
    Collection<Post> posts = Main.database.getPosts();
    try {
      // 200: OK
      response.status(HTTP_OK);
      return mapper.writeValueAsString(posts);
    } catch (JsonProcessingException e) {
      System.err.println("Json Processing Error");
      e.printStackTrace();
      // 500: Internal Server Error
      response.status(HTTP_INTERNAL_ERROR);
      return "{\"error:\", \"Json Processing Error\"}";
    }
  }

  /**
   * Create a post in the database and returns it as JSON.
   *
   * @param request Spark Request object containing the http request.
   * @param response Spark Response object containing details of the response.
   * @return A JSON string containing the srialized post or a JSON string containing an error
   *     message on failure.
   */
  public String createPost(Request request, Response response) {
    response.type("application/json");
    Post post;
    // try {
    //   post = mapper.readValue(request.body(), Post.class);
    // } catch (JsonProcessingException e) {
    //   System.err.println("Json Processing Error");
    //   e.printStackTrace();
    //   // 500: Internal Server Error
    //   response.status(500);
    //   return "{\"error:\", \"Json Processing Error\"}";
    // }
    // Method newPost(String, String, String) does not exist yet.
    // Main.database.newPost(post.getOwner(), post.getText(), post.getImage());
    // 501: Not implemented
    response.status(HTTP_NOT_IMPLEMENTED);
    return "";
  }

  // Requests to "/:postID"
  /**
   * Get a single user from database and returns it as JSON.
   *
   * @param request Spark Request object containing the http request.
   * @param response Spark Response object containing details of the response.
   * @return A JSON string containing the srialized user, an empty string if user does not exist or
   *     a JSON string containing an error message on failure.
   */
  public String getPost(Request request, Response response) {
    response.type("application/json");
    Post post = Main.database.getPost(request.params("postID"));
    if (post != null) {
      try {
        response.status(HTTP_OK);
        return mapper.writeValueAsString(post);
      } catch (JsonProcessingException e) {
        System.err.println("Json Processing Error");
        e.printStackTrace();
        response.status(HTTP_INTERNAL_ERROR);
        return "{\"error:\", \"Json Processing Error\"}";
      }
    } else {
      response.status(HTTP_NOT_FOUND);
      return "";
    }
  }

  /**
   * [TODO:description]
   *
   * @param request [TODO:description]
   * @param response [TODO:description]
   * @return [TODO:description]
   */
  public String updatePost(Request request, Response response) {
    response.type("application/json");
    response.status(HTTP_NOT_IMPLEMENTED);
    // TODO: Implement
    return "";
  }

  /**
   * [TODO:description]
   *
   * @param request [TODO:description]
   * @param response [TODO:description]
   * @return [TODO:description]
   */
  public String deletePost(Request request, Response response) {
    response.type("application/json");
    response.status(HTTP_NOT_IMPLEMENTED);
    // TODO: Implement
    return "";
  }

  // Other Requests
  /**
   * [TODO:description]
   *
   * @param request [TODO:description]
   * @param response [TODO:description]
   * @return [TODO:description]
   */
  public String getAllPostsByUser(Request request, Response response) {
    response.type("application/json");
    // 501: Not Implemented
    // TODO: Implement
    response.status(501);
    return "";
  }
}
