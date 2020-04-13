package swagger.demo4.bye;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/swagger/demo4/bye")
public class ByeEndpoint {

    @GET
    @Produces("application/json")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = String.class)),
            description = "Goodbye message")
    public Response getBye() {
        return Response.ok("swagger/demo4/bye").build();
    }
}
