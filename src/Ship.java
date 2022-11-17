public class Ship {
    int length;
    boolean vertical;
    int xOrigin;
    int yOrigin;
    Board board;
    String name;
    int hits;
    boolean sunk;
    String emoji;

    public Ship(int length, Board board, String name, String emoji){
        this.length = length;
        this.board = board;
        this.name = name;
        this.emoji = emoji;
        hits = 0;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public int getxOrigin() {
        return xOrigin;
    }

    public void setxOrigin(int xOrigin) {
        this.xOrigin = xOrigin;
    }

    public int getyOrigin() {
        return yOrigin;
    }

    public void setyOrigin(int yOrigin) {
        this.yOrigin = yOrigin;
    }

    public void drawShip() {
        hits = 0;
        for (int i = 0; i < length; i++) {
            if(vertical){
                board.setShip(xOrigin, yOrigin+i, emoji);
                if(board.tiles[xOrigin][yOrigin+i].isShot()){
                    hits++;
                }
            } else {
                board.setShip(xOrigin+i, yOrigin, emoji);
                if(board.tiles[xOrigin+i][yOrigin].isShot()){
                    hits++;
                }
            }
        }
        if(hits >= length){
            sink();
        }
    }

    private void sink() {
        for (int i = 0; i < length; i++) {
            if(vertical){
                board.setSunk(xOrigin, yOrigin+i);
            } else {
                board.setSunk(xOrigin+i, yOrigin);
            }
        }
        if(!sunk) {

            Battleship.d.setLabel("<font color=RED>" + name + " is down!</color");
            sunk = true;
        }
    }

    public void drawShipPlacement() {
        for (int i = 0; i < length; i++) {
            if(vertical){
                board.setShipPlacement(xOrigin, yOrigin+i, emoji);
            } else {
                board.setShipPlacement(xOrigin+i, yOrigin, emoji);
            }
        }
    }

    public int getLength() {
        return length;
    }

    public void drawShipPlacementRed() {
        for (int i = 0; i < length; i++) {
            if(vertical){
                board.setRed(xOrigin, yOrigin+i);
            } else {
                board.setRed(xOrigin+i, yOrigin);
            }
        }
    }
}
