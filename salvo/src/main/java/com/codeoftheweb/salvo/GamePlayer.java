package com.codeoftheweb.salvo;


import java.lang.Long;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    private Set<Ship> ships = new HashSet<>();

    public GamePlayer() {}

    public Long getId() {
        return this.id;
    }

    public GamePlayer(Game game ,Player player) {
        this.game = game;
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setUser(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<Ship> getShip() {
        return this.ships;
    }

    public void setShips(Set<Ship> ship) {
        this.ships = ship;
    }

    public void addShip(Ship ship) {
        ship.setGamePlayer(this);
        this.ships.add(ship);
    }
}