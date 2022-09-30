package com.codeoftheweb.salvo;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    
    public String userName;
    public String passWord;
    public String email;
    @OneToMany(mappedBy="player")
    Set<GamePlayer> gameplayers = new HashSet<>();

    public Player() { }
    
    public Player(String username, String password, String mail) {
        userName = username;
        passWord = password;
        email = mail;

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return userName;
    }
    
    public void setUser(String user) {
        this.userName = user;
    }
    
    public String getPassword() {
        return passWord;
    }
    
    public void setPassword(String password) {
        this.passWord = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<GamePlayer> getGamesPlayed() {
        return gameplayers;
    }
   public void addGamePlayer(GamePlayer gameplayer) {
        gameplayer.setPlayer(this);
       gameplayers.add(gameplayer);
    }

    @JsonIgnore
    public List<Game> getGames() {
        return gameplayers.stream().map(sub -> sub.getGame()).collect(Collectors.toList()); // ???
    }

}
