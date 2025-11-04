package ninjawar.model;

public class mGiftData {
    int amount = 1;
    int hsd = -1;
    public String hsdStr = "";
    short idItem = -1;
    public int num;

    public static int partTypeServerToTypeClient(int key) {
        switch (key) {
            case 1:
                return 0;
            case 2:
                return 2;
            case 3:
                return 1;
            default:
                return 0;
        }
    }

    public mGiftData(short idItem2, int num2, String hsd2) {
        this.num = num2;
        this.idItem = idItem2;
        this.hsdStr = hsd2;
    }
}
