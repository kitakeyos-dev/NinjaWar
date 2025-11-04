package ninjawar.model;

import ninjawar.myscr.Player;

public class PartySearch {
    public String level = "";
    public String name = "";
    public int numMem = 1;
    public int playerId;
    public byte sys;

    public String numMem() {
        return "(" + this.numMem + "/5)";
    }

    public PartySearch() {
    }

    public PartySearch(Player c) {
        this.playerId = c.charID;
        this.name = c.cName;
        this.sys = (byte) c.classId;
        this.level = "Lv." + String.valueOf(c.clevel);
    }
}
