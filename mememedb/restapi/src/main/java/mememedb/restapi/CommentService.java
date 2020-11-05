package mememedb.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.datastructures.Comment;
import core.datastructures.Post;
import spark.Request;
import spark.Response;

/** Contains methods for communicating post data to/from the database. */
public class CommentService {

  private ObjectMapper mapper;

  public CommentService() {
    mapper = new ObjectMapper();
  }

  // HTTP Status Codes: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes

  // Requests to "/"
  public String getAllComments(Request request, Response response) {
    response.type("application/json");
    // 501: Not Implemented
    // TODO: Implement
    response.status(501);
    return "";
  }

  public String createComment(Request request, Response response) {
    response.type("application/json");
    Comment comment;
    try {
      comment = mapper.readValue(request.body(), Comment.class);
    } catch (JsonProcessingException e) {
      System.err.println("Json Processing Error");
      e.printStackTrace();
      // 500: Internal Server Error
      response.status(500);
      return "{\"error:\", \"Json Processing Error\"}";
    }
    try {
      // TODO: Create comment in database
      // 501: Not Implemented
      response.status(501);
      return "";
    } catch (JsonProcessingException e) {
      System.err.println("Json Processing Error");
      e.printStackTrace();
      // 500: Internal Server Error
      response.status(500);
      return "{\"error:\", \"Json Processing Error\"}";
    }
  }

  // Requests to "/:commentID"
  public String getComment(Request request, Response response) {
    response.type("application/json");
    Post post = Main.database.getPost(request.params("postID"));
    Comment comment = post.getComment(request.params("commentID"));
    if (post != null) {
      try {
        // 200: OK
        response.status(200);
        return mapper.writeValueAsString(comment);
      } catch (JsonProcessingException e) {
        System.err.println("Json Processing Error");
        e.printStackTrace();
        // 500: Internal Server Error
        response.status(500);
        return "{\"error:\", \"Json Processing Error\"}";
      }
    } else {
      // 404: Not found
      response.status(404);
      // Return empty string
      return "";
    }
  }

  public String updateComment(Request request, Response response) {
    response.type("application/json");
    // 501: Not Implemented
    // TODO: Implement
    response.status(501);
    return "";
  }

  public String deleteComment(Request request, Response response) {
    response.type("application/json");
    // 501: Not Implemented
    // TODO: Implement
    response.status(501);
    return "";
  }
}
