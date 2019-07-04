package nl.hva.ewa.models;


import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)    @Column(name = "eventId")
    private int eventId;


    @Column(name = "eventName")
    private String eventName;

    @Column(name = "eventDate")
    private String eventDate;

    @Column(name = "eventDetails")
    private String eventDetails;

    @Column(name = "locatie")
    private String locatie;

    @Column(name = "restaurant")
    private String restaurant;

    @OneToOne
    private Invitelist invitelist;


    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public Invitelist getInvitelist() {
        return invitelist;
    }

    public void setInvitelist(Invitelist invitelist) {
        this.invitelist = invitelist;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
}
