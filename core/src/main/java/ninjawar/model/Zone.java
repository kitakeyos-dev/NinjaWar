package ninjawar.model;

public class Zone {
    public short idZone;
    public String name = "1";
    public byte status;

    public Zone() {
    }

    public Zone(short idZone2, String name2, byte status2) {
        this.idZone = idZone2;
        this.name = name2;
        this.status = status2;
    }
}
