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
    
    public String user;
    public String password;

    @OneToMany(mappedBy="owner")
    Set<Game> GamesPlayed = new HashSet<>();

    public Player() { }
    
    public Player(String first, String last) {
        user = first;
        password = last;
    }



    public String getUser() {
        return user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Game> getGamesPlayed() {
        return GamesPlayed;
    }

   public void addGame(Game game) {
        game.setOwner(this);
       GamesPlayed.add(game);
    }

    @JsonIgnore
    public List<Game> getGames() {
        return GamesPlayed.stream().map(sub -> sub.getGame()).collect(Collectors.toList()); // ???
    }

}
