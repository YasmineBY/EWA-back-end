package nl.hva.ewa.models;



import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Invitelist")
public class Invitelist implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "listId")
    private int listId;

    @ManyToMany( mappedBy = "invitelists")
    private Set<User> invitelist = new HashSet<>();




    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }
//
//    public Set<User> getInvitelist() {
//        return invitelist;
//    }
//
//    public void setInvitelist(Set<User> invitelist) {
//        this.invitelist = invitelist;
//    }
}
