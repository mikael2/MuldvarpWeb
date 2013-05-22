package no.hials.muldvarpweb.service.scrape;

import java.io.Serializable;
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
        List<Message> messages = new ArrayList<Message>();
        List<Document> documents = new ArrayList<Document>();
        List<Innlevering> innleveringer = new ArrayList<Innlevering>();
        
        messages.add(new Message("Husk obligatorisk innlevering 3!! Siste frist idag!!", "29.04.13"));
        messages.add(new Message("Obligatorisk 3 ligger nå ute på fronter.", "14.04.13"));
        
        documents.add(new Document("Oblig3.pdf", "14.04.13", "http://domene.no/filer/oblig3.pdf"));
        documents.add(new Document("Oblig2.pdf", "10.03.13", "http://domene.no/filer/oblig2.pdf"));
        documents.add(new Document("Oblig1.pdf", "03.02.13", "http://domene.no/filer/oblig1.pdf"));
        
        innleveringer.add(new Innlevering("Obligatorisk 1", "Godkjent"));
        innleveringer.add(new Innlevering("Obligatorisk 2", "Godkjent"));
        innleveringer.add(new Innlevering("Obligatorisk 3", "Ikke vurdert"));
        
        f.setMessages(messages);
        f.setDocuments(documents);
        f.setInnleveringer(innleveringer);
        
        return f;
    }
    
    public static class Fronter implements Serializable {
        List<Message> messages;
        List<Document> documents;
        List<Innlevering> innleveringer;

        public Fronter() {
        }

        public List<Message> getMessages() {
            return messages;
        }

        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }

        public List<Document> getDocuments() {
            return documents;
        }

        public void setDocuments(List<Document> documents) {
            this.documents = documents;
        }

        public List<Innlevering> getInnleveringer() {
            return innleveringer;
        }

        public void setInnleveringer(List<Innlevering> innleveringer) {
            this.innleveringer = innleveringer;
        }
    }

    public static class Innlevering implements Serializable {
        String navn;
        String status;

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

    public static class Message implements Serializable {
        String message;
        String date;

        public Message(String message, String date) {
            this.message = message;
            this.date = date;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public static class Document implements Serializable {
        String name;
        String date;
        String url;

        public Document(String name, String date, String url) {
            this.name = name;
            this.date = date;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
