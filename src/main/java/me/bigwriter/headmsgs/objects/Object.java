package me.bigwriter.headmsgs.objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collections;

public class Object {

    private Location loc;
    private String msg;
    private String name;


    public Object(Player p, Location location) {
        this.name = p.getName();
        this.loc = location;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public void addLoc(Location locations){
        this.loc.add((Location) Collections.singletonList(locations));
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
