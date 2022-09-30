package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    public Game game;

    public List<GamePlayer> partidas;
    @OneToMany(mappedBy = "gamesplayed", fetch = FetchType.EAGER )
    Set<GamePlayer> gameplayers;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private Player owner;
    private Date creationDate;


//    public Game() { }

    public Game( ) {
        setCreationDate( new Date() );
        System.out.println(getCreationDate().toString());
    }

    public Game(String dateString){

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try{
            creationDate = format.parse( dateString );
            System.out.println("Successfully Parsed Date " + creationDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public long getGameID(){ return id; }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date date) {
        this.creationDate = date;
    }
    public Game getGame() {
        return this;
    }
    public void setGame(Game game) {
        this.game = game;
    }

    public void addGamePlayer(GamePlayer gameplayer) {
        gameplayer.setGame(this);
        gameplayers.add(gameplayer);
    }

    public Set<GamePlayer> getGamePlayers(){ return gameplayers; }

    public List<Player> getPlayers() {
        return gameplayers.stream().map(gp -> gp.getPlayer()).collect(toList()); // ???
    }

}