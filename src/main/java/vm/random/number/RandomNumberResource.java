package vm.random.number;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Path("/generate/numbers")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RandomNumberResource {

  private static List<RandomNumber> randomNumbers = new ArrayList<RandomNumber>();
  private static Random r = new Random();
  private Long id = 0L;


  @POST
  @Path("/random")
  public Response requestRandomNumber(@Context UriInfo uriInfo, @Suspended AsyncResponse asyncResponse) {

    List<RandomNumber> generatedNumbers = GeneratedNumbers.getInstance();
    RandomNumber randomNumber = new RandomNumber();

    int min = 3;

    LocalTime startTime = LocalTime.now();
    int num = r.nextInt(Integer.MAX_VALUE - min) + min;
    randomNumber.setNumber(num);

    LocalTime endTime = LocalTime.now();

    int hora = Math.toIntExact(endTime.until(startTime, ChronoUnit.HOURS));
    int minuto = Math.toIntExact(endTime.until(startTime, ChronoUnit.MINUTES));
    int segundo = Math.toIntExact(endTime.until(startTime, ChronoUnit.SECONDS));

    LocalTime timeDuration = LocalTime.of(0, 0, 0);

    asyncResponse.resume(num);
    randomNumber.setId(++id);
    randomNumber.setStatus(true);
    randomNumber.setDuration(LocalTime.now());
    generatedNumbers.add(randomNumber);

    URI uri = uriInfo.getAbsolutePathBuilder()
      .path(randomNumber.getId().toString())
      .build();
    asyncResponse.setTimeout(310000, TimeUnit.MILLISECONDS);
    return Response.created(uri).build();
  }

  @GET
  @Path("/history")
  public Response getListGeneratedRandomNumber() {
    List<RandomNumber> generatedNumbers = GeneratedNumbers.getInstance();

    return Response.ok(generatedNumbers).build();

  }



  @PUT
  @Path("/{requestId}/cancel")
  public Response cancelRandom(@PathParam("requestId") int id, @Suspended AsyncResponse asyncResponse, RandomNumber randomNumber) {
    List<RandomNumber> generatedNumbers = GeneratedNumbers.getInstance();

    for (RandomNumber rn : generatedNumbers) {
      if (rn.getNumber() == id) {
        int index = generatedNumbers.indexOf(rn);
        RandomNumber foundNumber = generatedNumbers.get(index);
        generatedNumbers.remove(foundNumber);

      }
    }

    return Response.ok().build();
  }


  @GET
  @Path("/pending")
  public Response listPending() {
    List<RandomNumber> generatedNumbers = GeneratedNumbers.getInstance();
    for (RandomNumber rn : generatedNumbers) {
      if (rn.getStatus() == false)
         generatedNumbers.add(rn);
    }

    return Response.ok(generatedNumbers).build();
  }

  @PUT
  @Path("/threads")
  public Response changeSize(@Suspended AsyncResponse asyncResponse) {
    int min = 1;
    int max = 10;
    RandomNumber randomNumber = new RandomNumber();

    int numGerado = r.nextInt(max - min) + min;
    randomNumber.setNumber(numGerado);
    asyncResponse.resume(numGerado);

    return Response.ok().build();
  }
}
