package chuggaChugga.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import chuggaChugga.domain.Ticket;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@SessionScoped
public class TicketController {

    public String loadReport() {
        String url = "http://localhost:8080/ChuggaChugga/api/report/";
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        List<Ticket> ticketList = new ArrayList<>();

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.register(JsonProcessingFeature.class).target(url);
        JsonArray jsonArray = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get(JsonArray.class);

        for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
            JsonObject ticketForReport = jsonObject.getJsonObject("ticketForReport");
            String firstName = ticketForReport.getString("firstName");
            String lastName = ticketForReport.getString("lastName");
            System.out.println(firstName + "  " + lastName);
        }
        return "success";
    }
}
