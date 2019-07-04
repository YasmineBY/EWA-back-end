package nl.hva.ewa.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "groupname")
    private String groupname;


    @ManyToMany( fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "User_Has_Invitelist"
    )
    Set<Invitelist> invitelists = new HashSet<>();

    @OneToMany( fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    private Set<Event> event;

    public Set<Invitelist> getInvitelists() {
        return invitelists;
    }

    public void setInvitelists(Set<Invitelist> invitelists) {
        this.invitelists = invitelists;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupname() {
        return groupname;
    }

    public Set<Event> getEvent() {
        return event;
    }

    public void setEvent(Set<Event> event) {
        this.event = event;
    }

    public void addEvent(Event eventadd){
        this.event.add(eventadd);
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public void addInviteList(Invitelist invite){
        invitelists.add(invite);
    }


}
