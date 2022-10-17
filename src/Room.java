public class Room {
    private int place;
    private boolean reserved;
    private int num;
    private int price;
    private boolean conditioner;
    private boolean bar;

    public Room(int num, int place,  int price, boolean conditioner, boolean bar) {
        this.place = place;
        this.reserved = false;
        this.num = num;
        this.price = price;
        this.conditioner = conditioner;
        this.bar = bar;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getPlace() {
        return place;
    }

    public int getNum() {
        return num;
    }

    public int getPrice() {
        return price;
    }

    public boolean isConditioner() {
        return conditioner;
    }

    public boolean isBar() {
        return bar;
    }

}
