package no.hials.muldvarpweb.service.scrape;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("fronter")
public class FronterService {
    
    @GET
    @Path("/room/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Fronter getRoomData(@PathParam("id") String id) {
        Fronter f = new Fronter();
        List<String> messages = new ArrayList<String>();
        List<String> documents = new ArrayList<String>();
        List<Innlevering> innleveringer = new ArrayList<Innlevering>();
        
        messages.add("Husk obligatorisk innlevering 3!! Siste frist idag!!");
        messages.add("Obligatorisk 3 ligger nå ute på fronter.");
        
        documents.add("Oblig3.pdf");
        documents.add("Oblig2.pdf");
        documents.add("Oblig1.pdf");
        
        innleveringer.add(new Innlevering("Obligatorisk 1", "Godkjent"));
        innleveringer.add(new Innlevering("Obligatorisk 2", "Godkjent"));
        innleveringer.add(new Innlevering("Obligatorisk 3", "Ikke vurdert"));
        
        f.setMessages(messages);
        f.setDocuments(documents);
        f.setInnleveringer(innleveringer);
        
        return f;
    }
    
    static class Fronter {
        List<String> messages;
        List<String> documents;
        List<Innlevering> innleveringer;

        public Fronter() {
        }

        public List<String> getMessages() {
            return messages;
        }

        public void setMessages(List<String> messages) {
            this.messages = messages;
        }

        public List<String> getDocuments() {
            return documents;
        }

        public void setDocuments(List<String> documents) {
            this.documents = documents;
        }

        public List<Innlevering> getInnleveringer() {
            return innleveringer;
        }

        public void setInnleveringer(List<Innlevering> innleveringer) {
            this.innleveringer = innleveringer;
        }
    }

    private static class Innlevering {
        String navn;
        String status;

        public Innlevering() {
        }

        public Innlevering(String navn, String status) {
            this.navn = navn;
            this.status = status;
        }

        public String getNavn() {
            return navn;
        }

        public void setNavn(String navn) {
            this.navn = navn;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
