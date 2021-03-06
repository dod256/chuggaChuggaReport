package chuggaChugga.Controller;

import chuggaChugga.domain.Ticket;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class TicketController {

    private Date firstDate;
    private Date secondDate;
    private List<Ticket> result = new ArrayList<>();

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(Date secondDate) {
        this.secondDate = secondDate;
    }

    public String loadReport() {
        String url = "http://localhost:8080/ChuggaChugga/api/report/" + firstDate.getTime() + "/" + secondDate.getTime();

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.register(JsonProcessingFeature.class).target(url);
        JsonArray jsonArray = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get(JsonArray.class);

        for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
            Ticket.Builder ticketBuilder = Ticket.newBuilder();
            ticketBuilder.withUserFirstName(jsonObject.getString("userFirstName"))
                         .withUserLastName(jsonObject.getString("userLastName"))
                         .withTrainName(jsonObject.getString("trainName"))
                         .withArrivalDate(jsonObject.getString("arrivalDate"))
                         .withDepartureDate(jsonObject.getString("departureDate"))
                         .withArrivalStation(jsonObject.getString("arrivalStation"))
                         .withDepartureStation(jsonObject.getString("departureStation"))
                         .build();

            result.add(ticketBuilder.build());
        }
        return "success";
    }

    public List<Ticket> getTicketList() {
        return result;
    }
}
