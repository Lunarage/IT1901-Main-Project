package mememedb.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.datastructures.User;
import spark.Request;
import spark.Response;

/** Contains methods for communicating user data to/from the database. */
public class UserService {

  private ObjectMapper mapper;

  public UserService() {
    mapper = new ObjectMapper();
  }

  // HTTP Status Codes: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes

  // Requests to "/"
  /**
   * Gets all users from database and returns them as JSON.
   *
   * @param request Spark Request object containing the http request.
   * @param response Spark Response object containing details of the response.
   * @return A JSON string containing the serialized users.
   */
  public String getAllUsers(Request request, Response response) {
    response.type("application/json");
    // 501: Not Implemented
    // TODO: Implement
    response.status(501);
    return "";
  }

  /**
   * Create a user in the database and returns it as JSON.
   *
   * @param request Spark Request object containing the http request.
   * @param response Spark Response object containing details of the response.
   * @return A JSON string containing the srialized user or a JSON string containing an error
   *     message on failure.
   */
  public String createUser(Request request, Response response) {
    response.type("application/json");
    User user;
    try {
      user = mapper.readValue(request.body(), User.class);
    } catch (JsonProcessingException e) {
      System.err.println("Json Processing Error");
      e.printStackTrace();
      // 500: Internal Server Error
      response.status(500);
      return "{\"error:\", \"Json Processing Error\"}";
    }
    if (Main.database.usernameExists(user.getNickname())) {
      // 409: Conflict
      response.status(409);
      return "{\"error:\", \"Username allready exists\"}";
    } else {
      try {
        Main.database.addUser(user);
        return mapper.writeValueAsString(Main.database.getUser(user.getNickname()));
      } catch (JsonProcessingException e) {
        System.err.println("Json Processing Error");
        e.printStackTrace();
        // 500: Internal Server Error
        response.status(500);
        return "{\"error:\", \"Json Processing Error\"}";
      }
    }
  }

  // Requests to "/:nickname"
  /**
   * Get a single user from database and returns it as JSON.
   *
   * @param request Spark Request object containing the http request.
   * @param response Spark Response object containing details of the response.
   * @return A JSON string containing the srialized user, an empty string if user does not exist or
   *     a JSON string containing an error message on failure.
   */
  public String getUser(Request request, Response response) {
    User user = Main.database.getUser(request.params("nickname"));
    response.type("application/json");
    if (user != null) {
      try {
        // 200: OK
        response.status(200);
        return mapper.writeValueAsString(user);
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

  public String updateUser(Request request, Response response) {
    response.type("application/json");
    // 501: Not Implemented
    // TODO: Implement
    response.status(501);
    return "";
  }

  public String deleteUser(Request request, Response response) {
    response.type("application/json");
    // 501: Not Implemented
    // TODO: Implement
    response.status(501);
    return "";
  }
}
