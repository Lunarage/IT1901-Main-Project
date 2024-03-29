package restapi;

import static java.net.HttpURLConnection.HTTP_CONFLICT;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.datastructures.User;
import core.json.MememeModule;
import java.util.Collection;
import spark.Request;
import spark.Response;

/** Contains methods for communicating user data to/from the database. */
public class UserService {

  private ObjectMapper mapper;

  public UserService() {
    mapper = new ObjectMapper();
    mapper.registerModule(new MememeModule());
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
    Collection<User> users = Server.database.getUsers();
    try {
      response.status(HTTP_OK);
      return mapper.writeValueAsString(users);
    } catch (JsonProcessingException e) {
      System.err.println("Json Processing Error");
      e.printStackTrace();
      response.status(HTTP_INTERNAL_ERROR);
      return "{\"message\": \"\", \"error\": \"Json Processing Error\"}";
    }
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
      response.status(HTTP_INTERNAL_ERROR);
      return "{\"message\": \"\", \"error\": \"Json Processing Error\"}";
    }
    if (Server.database.nicknameExists(user.getNickname())) {
      response.status(HTTP_CONFLICT);
      return "{\"message\": \"\", \"error\": \"Nickname already exists\"}";
    } else {
      Server.database.addUser(user);
      response.status(HTTP_OK);
      return "{\"message\": \"User created successfully\", \"error\": \"\"}";
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
    response.type("application/json");
    User user = Server.database.getUser(request.params("nickname"));
    if (user != null) {
      try {
        response.status(HTTP_OK);
        return mapper.writeValueAsString(user);
      } catch (JsonProcessingException e) {
        System.err.println("Json Processing Error");
        response.status(HTTP_INTERNAL_ERROR);
        return "{\"message\": \"\", \"error\": \"Nickname already exists\"}";
      }
    } else {
      response.status(HTTP_NOT_FOUND);
      return "";
    }
  }
}
