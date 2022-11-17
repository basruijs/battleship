public class Mine extends Ship{


    public Mine(int length, Board board, String name, String emoji) {
        super(length, board, name, emoji);
    }

    @Override
    public void drawShip() {
        hits = 0;
        board.setMine(xOrigin, yOrigin, emoji);
        if(!sunk && board.tiles[xOrigin][yOrigin].isShot()){
            explode();
        }
    }

    private void explode() {
        Board explodeBoard = Battleship.boards[board.enemyBoardNr()];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    try {
                        explodeBoard.shoot(xOrigin - 1 + i, yOrigin - 1 + j);
                        System.out.println(xOrigin - 1 + i);
                        System.out.println(yOrigin - 1 + j);
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                }
            }
            Battleship.d.setLabel("<font color=RED>" + this.name + " exploded!</color>");
        sunk=true;
    }
}
