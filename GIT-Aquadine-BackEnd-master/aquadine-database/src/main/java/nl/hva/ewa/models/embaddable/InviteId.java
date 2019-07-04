package nl.hva.ewa.models.embaddable;

import nl.hva.ewa.models.Invitelist;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class InviteId implements Serializable {

    @Column(name = "email", nullable = false)
    private String  email;

    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "invitelist", nullable = false)
    private Invitelist invitelist;

    public String getEmail() {
        return email;
    }

    public InviteId(String email, Invitelist invitelist) {
        this.email = email;
        this.invitelist = invitelist;
    }

    public InviteId() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Invitelist getInvitelist() {
        return invitelist;
    }

    public void setInvitelist(Invitelist invitelist) {
        this.invitelist = invitelist;
    }


}
