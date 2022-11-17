public class Board {
    Tile[][] tiles = new Tile[Battleship.size][Battleship.size];

    int boardNr;
    public Board(int boardNr) {
        this.boardNr=boardNr;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                tiles[i][j] = new Tile(i, j, this);
            }
        }
    }

    public int opposingBoardNr(){
        if(this.boardNr==0){
            return 3;
        } else if(boardNr==2){
            return 1;
        } else if(this.boardNr==1){
            return 2;
        } else{
            return 0;
        }
    }


    public int enemyBoardNr(){
        if(this.boardNr==1){
            return 3;
        } else if(boardNr==3){
            return 1;
        } else if(this.boardNr==0){
            return 2;
        } else{
            return 0;
        }
    }

    public boolean shoot(int x, int y) {
        setShot(x, y);
        if (tiles[x][y].isShip() || tiles[x][y].isMine()) {
            Battleship.d.setLabel("<font color=RED>KABOOM!!</color>");
            return true;
        } else {
            Battleship.d.setLabel("<font color=BLUE>Splooosh...</color>");
            return false;
        }
    }

    public void setShip(int x, int y, String shipType) {
        tiles[x][y].setShip(true, shipType);
    }

    public void setShipPlacement(int x, int y, String emoji) {
        tiles[x][y].setShipPlacement(true, emoji);
    }

    public void setShot(int x, int y) {
        tiles[x][y].setShot(true);
    }

    public void setSunk(int x, int y) {
        tiles[x][y].setSunk(true);
    }

    public void drawBoard() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if(tiles[i][j].isSunk()){
                    Battleship.d.setMap("<font color=RED>" + tiles[i][j].shipType + "</color>", i, j, opposingBoardNr());
                    Battleship.d.setMap("<font color=RED>" + tiles[i][j].shipType + "</color>", i, j, boardNr);
                } else if (tiles[i][j].isShip() && tiles[i][j].isShot()) {
                    Battleship.d.setMap("<font color=RED>" + Emoji.EXPLOSION + "</color>", i, j, opposingBoardNr());
                    Battleship.d.setMap("<font color=RED>" + tiles[i][j].shipType + "</color>", i, j, boardNr);
                } else if (tiles[i][j].isRed()) {
                    Battleship.d.setMap("<font color=RED>" + Emoji.X + "</color>", i, j, boardNr);


                } else if (tiles[i][j].isShip()) {
                    Battleship.d.setMap("<font color=BLACK>" + tiles[i][j].shipType + "</color>", i, j, boardNr);


                } else if (tiles[i][j].isMine() && tiles[i][j].isShot()) {
                    Battleship.d.setMap("<font color=RED>" + tiles[i][j].shipType + "</color>", i, j, boardNr);
                    Battleship.d.setMap("<font color=RED>" + tiles[i][j].shipType + "</color>", i, j, opposingBoardNr());
                } else if (tiles[i][j].isMine()) {
                    Battleship.d.setMap("<font color=BLACK>" + tiles[i][j].shipType + "</color>", i, j, boardNr);
                } else if (tiles[i][j].isShot()) {
                    Battleship.d.setMap("<font color=BLUE>" + Emoji.X + "</color>", i, j, opposingBoardNr());
                    Battleship.d.setMap("<font color=BLUE>" + Emoji.X + "</color>", i, j, boardNr);
                } else if (tiles[i][j].isShipPlacement()) {
                    Battleship.d.setMap("<font color=GREEN>" + tiles[i][j].shipType + "</color>", i, j, boardNr);
                } else {
                    if(boardNr==0 || boardNr==2) {
                        Battleship.d.setMap("", i, j, boardNr);
                    }
                }
            }
        }
    }

    public boolean checkWin() {
        boolean win=true;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j].isShip() && !tiles[i][j].isShot()) {
                    win=false;
                }
            }
        }
        return win;
    }

    public void setMine(int x, int y, String emoji) {
        tiles[x][y].setMine(true, emoji);
    }

    public void setRed(int x, int y) {
        tiles[x][y].setRed(true);
    }
}

