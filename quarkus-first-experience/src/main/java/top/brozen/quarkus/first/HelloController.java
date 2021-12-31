package top.brozen.quarkus.first;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Brozen
 * @since 2021-12-29
 */
@Path("/api/hello")
public class HelloController {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreet() {
        return "hello RESTEasy";
    }


}
