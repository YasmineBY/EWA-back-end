package nl.hva.ewa.models;

import nl.hva.ewa.models.embaddable.InviteId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invite")

public class Invite implements Serializable {

    public Invite(InviteId inviteId) {
        this.inviteId = inviteId;
    }

    @EmbeddedId
    private InviteId inviteId;

    public Invite() {
    }


    public InviteId getInviteId() {
        return inviteId;
    }

    public void setInviteId(InviteId inviteId) {
        this.inviteId = inviteId;
    }

}
