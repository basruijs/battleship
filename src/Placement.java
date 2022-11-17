import java.util.Random;

public class Placement {
    Display d;
    int player;
    Ship[][] ships;
    Board[] boards;
    int size;

    int x;
    int y;
    boolean vertical;

    boolean comp;

    public Placement(Display d, int player, Ship[][] ships, Board[] boards, int size, boolean comp) {
        this.d = d;
        this.player = player;
        this.ships = ships;
        this.boards = boards;
        this.size = size;
        this.comp=comp;
    }

    public void placement() {
        for (int p = 0; p < 2; p++) {
            d.placePressed = false;
            d.setLabel("Player " + (player + 1) + ", prepare your fleet");
            int map = getMap(player);
            for (int i = 0; i < ships[player].length; i++) {
                d.setPlayer(ships[player][i].name + " (" +  ships[player][i].length + ")");
                System.out.println(ships[player].length);
                do {
                    if(comp && player==1) {
                        d.placePressed = true;
                    }


                    x = d.getSelectedX(map);
                    y = d.getSelectedY(map);
                    if (x == -1 || y == -1) {
                        x = 0;
                        y = 0;
                    }
                    if (d.flipPressed) {
                        vertical = !vertical;
                        d.flipPressed = false;
                    }

                    ships[player][i].setVertical(vertical);
                    ships[player][i].setxOrigin(x);
                    ships[player][i].setyOrigin(y);

                    try {
                        ships[player][i].drawShipPlacement();
                    } catch (ArrayIndexOutOfBoundsException ignored) {

                    }

                    for (int j = 0; j < i; j++) {
                        ships[player][j].drawShip();
                    }
                    if(player==1){
                        for (int j = 0; j < ships[0].length; j++) {
                            ships[0][j].drawShip();
                        }
                    }
                    for (Board board : boards) {
                        board.drawBoard();
                        for (int j = 0; j < board.tiles.length; j++) {
                            for (int k = 0; k < board.tiles.length; k++) {
                                board.tiles[j][k].setShipPlacement(false, ships[player][i].emoji);
                            }
                        }
                    }
                } while (!d.placePressed);

                System.out.println(comp && player==1);

                if(comp && player==1){

                    System.out.println("random");
                    Random random = new Random();
                    vertical = random.nextBoolean();
                    if(vertical){
                        x=random.nextInt(size);
                        y=random.nextInt(size-ships[player][i].length);
                    } else {
                        x=random.nextInt(size-ships[player][i].length);
                        y=random.nextInt(size);
                    }

                }

                if (x != -1 && y != -1 && !offMap(x, y, ships[player][i].getLength(), vertical, boards[2 * player])) {
                    d.setLabel("Player " + (player + 1) + ", prepare your fleet");
                    ships[player][i].setVertical(vertical);
                    ships[player][i].setxOrigin(x);
                    ships[player][i].setyOrigin(y);
                } else {
                    d.setLabel("Invalid location, try again");
                    i--;
                }
                d.placePressed = false;
            }
            if (player == 0) {
                player = 1;
            } else {
                player=1;
                d.fireAtWill();
            }
        }
    }

    private int getMap(int player) {
        if(player==0){
            return 0;
        } else {
            return 2;
        }
    }

    private boolean offMap(int x, int y, int length, boolean vertical, Board board) {
        if(vertical){
            if(y+length-1>=size){
                return true;
            }
            for (int i = 0; i < board.tiles.length; i++) {
                for (int j = 0; j < board.tiles.length; j++) {
                    for (int k = 0; k < length; k++) {
                        if(board.tiles[x][y+k].isShip()){
                            return true;
                        }
                    }
                }
            }
        } else {
            if(x+length-1>=size){
                return true;
            }
            for (int i = 0; i < board.tiles.length; i++) {
                for (int j = 0; j < board.tiles.length; j++) {
                    for (int k = 0; k < length; k++) {
                        if(board.tiles[x+k][y].isShip()){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
