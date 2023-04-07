import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Battleship {
    static Battleship b;
    static Display d;
    static int size;
    static Ship[][] ships;
    static Board[] boards = new Board[4];
    int player = 0;
    boolean comp = true;
    Placement placement;
    int[] theme = new int[2];

    public static void main(String[] args) {
        b = new Battleship();
        b.play();
    }

    public Battleship() {
        size = 10;
        for (int i = 0; i < boards.length; i++) {
            boards[i] = new Board(i);
        }
        d = new Display();

        Boatmaker boatmaker = new Boatmaker(boards, size, d, ships, theme);
        ships = boatmaker.setup();
        theme[0]= d.getTheme(0);
        theme[1]=d.getTheme(1);

        placement = new Placement(d, player, ships, boards, size, comp);
        placement.placement();
    }



    public void play() {
        player = 0;
        boolean win = false;
        while (!win) {
            d.setPlayer("Player " + (player + 1));
            for (Board board : boards) {
                board.drawBoard();
            }
            for (int i = 0; i < ships[0].length; i++) {
                ships[0][i].drawShip();
                ships[1][i].drawShip();
            }

            if (player == 1 && comp) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean validShot = false;
                Random random = new Random();
                while (!validShot) {
                    int x = random.nextInt(size);
                    int y = random.nextInt(size);
                    boolean evenShot = false;
                    while (true) {
                        if ((x + y) % 2 == 0) {
                            break;
                        } else {
                            x = random.nextInt(size);
                            y = random.nextInt(size);
                        }
                    }
                    loop:
                    for (int i = 0; i < boards[0].tiles.length; i++) {
                        for (int j = 0; j < boards[0].tiles.length; j++) {
                            if (boards[0].tiles[i][j].isShot() && boards[0].tiles[i][j].isShip()
                                    && !boards[0].tiles[i][j].isSunk()) {
                                for (int k = 0; k < 4; k++) {
                                    switch (k) {
                                        case 0:
                                            if (j - 1 >= 0 && !boards[0].tiles[i][j - 1].isShot()) {
                                                System.out.println("up?");
                                                x = i;
                                                y = j - 1;
                                            }
                                            break;
                                        case 1:
                                            if (i - 1 >= 0 && !boards[0].tiles[i - 1][j].isShot()) {
                                                System.out.println("left?");
                                                x = i - 1;
                                                y = j;
                                            }
                                            break;
                                        case 2:
                                            if (j + 1 < size && !boards[0].tiles[i][j + 1].isShot()) {
                                                System.out.println("down?");
                                                x = i;
                                                y = j + 1;
                                            }
                                            break;
                                        case 3:
                                            if (i + 1 < size && !boards[0].tiles[i + 1][j].isShot()) {
                                                System.out.println("right?");
                                                x = i + 1;
                                                y = j;
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                }
//                                for (int k = 0; k < 4; k++) {
//                                    switch (k) {
//                                        case 0:
                                            if (j - 1 >= 0 && j + 1 < size
                                                    && boards[0].tiles[i][j + 1].isShot()
                                                    && !boards[0].tiles[i][j - 1].isShot()
                                                    && boards[0].tiles[i][j + 1].isShip()
                                                    && !boards[0].tiles[i][j + 1].isSunk()) {
                                                System.out.println("up");
                                                x = i;
                                                y = j - 1;
                                                break loop;
                                            }
//                                            break;
//                                        case 1:
                                                else if (i - 1 >= 0 && i + 1 < size
                                                        && boards[0].tiles[i + 1][j].isShot()
                                                        && !boards[0].tiles[i - 1][j].isShot()
                                                        && boards[0].tiles[i + 1][j].isShip()
                                                        && !boards[0].tiles[i + 1][j].isSunk()) {
                                                    System.out.println("left");
                                                    x = i - 1;
                                                    y = j;
                                                break loop;
                                                }
//                                            break;
//                                        case 2:
                                            else if (j - 1 >= 0 && j + 1 < size
                                                    && boards[0].tiles[i][j - 1].isShot()
                                                    && !boards[0].tiles[i][j + 1].isShot()
                                                    && boards[0].tiles[i][j - 1].isShip()
                                                    && !boards[0].tiles[i][j - 1].isSunk()) {
                                                System.out.println("down");
                                                x = i;
                                                y = j + 1;
                                                break loop;
                                            }
//                                            break;
//                                        case 3:
                                             else if (i - 1 >= 0 && i + 1 < size
                                                    && boards[0].tiles[i - 1][j].isShot()
                                                    && !boards[0].tiles[i + 1][j].isShot()
                                                    && boards[0].tiles[i - 1][j].isShip()
                                                    && !boards[0].tiles[i - 1][j].isSunk()) {
                                                System.out.println("right");
                                                x = i + 1;
                                                y = j;
                                                break loop;
                                            }
//                                            break;
//                                        default:
//                                            break;
//                                    }
//                                }
                            }

                        }
                    }
//                    System.out.println("OTHER x: " + x + " y: " + y + " % " + ((x + y) % 2) + " Is shot? " + boards[0].tiles[x][y].isShot());
                    if (!boards[0].tiles[x][y].isShot()) {
                        System.out.println("Boom!");
                        boards[0].shoot(x, y);
                        validShot = true;
                    }
                }
                player = 0;
            }


            if (d.shootPressed) {
                if (player == 1 && !comp) {
                    if (d.getSelectedX(3) != -1 && d.getSelectedY(3) != -1
                            && !boards[0].tiles[d.getSelectedX(3)][d.getSelectedY(3)].isShot()) {
                        boards[0].shoot(d.getSelectedX(3), d.getSelectedY(3));

                        player = 0;
                    } else {
                        d.setLabel("Invalid target");
                    }
                } else {
                    if (d.getSelectedX(1) != -1 && d.getSelectedY(1) != -1
                            && !boards[2].tiles[d.getSelectedX(1)][d.getSelectedY(1)].isShot()) {
                        boards[2].shoot(d.getSelectedX(1), d.getSelectedY(1));

                        player = 1;
                    } else {
                        d.setLabel("Invalid target");
                    }
                }
                d.shootPressed = false;
            }
            if (boards[0].checkWin()) {
                for (Board board : boards) {
                    board.drawBoard();
                }
                d.setPlayer("BATTLESHIP");
                d.setLabel("Player 2 wins!");
                win = true;
            }
            if (boards[2].checkWin()) {
                for (Board board : boards) {
                    board.drawBoard();
                }
                d.setPlayer("BATTLESHIP");
                d.setLabel("Player 1 wins!");
                win = true;
            }
            boards[0].checkWin();
        }
    }
}
