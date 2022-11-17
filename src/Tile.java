public class Tile {
    int x;
    int y;

    boolean shot;
    boolean ship;
    boolean shipPlacement;
    boolean mine;
    boolean red;
    String shipType;
    boolean sunk;
    Board board;

    public boolean isSunk() {
        return sunk;
    }

    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }



    public Tile(int x, int y, Board board){
        this.x=x;
        this.y=y;
    }

    public boolean isShot() {
        return shot;
    }

    public void setShot(boolean shot) {

        this.shot = shot;
    }

    public boolean isShip() {
        return ship;
    }

    public void setShip(boolean ship, String shipType) {
        this.ship = ship;
        this.shipType = shipType;
    }

    public void setShipPlacement(boolean placement, String emoji) {
        this.shipPlacement = placement;
        this.shipType = emoji;
    }

    public boolean isShipPlacement() {
        return shipPlacement;
    }

    public void setMine(boolean mine, String emoji) {
        this.mine=mine;
        this.shipType = emoji;
    }

    public boolean isMine() {
        return mine;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    public boolean isRed(){
        return red;
    }
}
