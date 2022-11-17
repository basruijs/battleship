public class Boatmaker {
    Board[] boards;
    int size;
    Display d;
    Ship[][] ships;
    int[] theme;

    public Boatmaker(Board[] boards, int size, Display d, Ship[][] ships, int[] theme) {
        this.boards = boards;
        this.size = size;
        this.d = d;
        this.ships = ships;
        this.theme = theme;
    }


    public Ship[][] setup() {

        while (!d.startPressed) {
            d.updateSliders();
            theme[0]= d.getTheme(0);
            theme[1]=d.getTheme(1);
        }

        int totalBoats = 0;
        for (int i = 0; i < 6; i++) {
            totalBoats = totalBoats + d.getSliderValue(i);
        }
        ships = new Ship[2][totalBoats];

        for (int i = 0; i < 2; i++) {
            int extra = 0;
            for (int j = 0; j < d.sliders.length; j++) {
                for (int k = 0; k < d.getSliderValue(j); k++) {
                    switch (j) {
                        case 0:
                            if (theme[i]==0) {
                                ships[i][extra] = new Ship(5, boards[i * 2], "Carrier", Emoji.HUGE_BOAT);
                            } else if(theme[i]==1) {
                                ships[i][extra] = new Ship(5, boards[i * 2], "Leviathan", Emoji.WHALE);
                            } else if(theme[i]==2) {
                                ships[i][extra] = new Ship(5, boards[i * 2], "Galleon", Emoji.SKULL_AND_CROSSBONES);
                            }
                            break;
                        case 1:
                            if (theme[i]==0) {
                                ships[i][extra] = new Ship(4, boards[i * 2], "Battleship", Emoji.BOAT);
                            } else if(theme[i]==1) {
                                ships[i][extra] = new Ship(4, boards[i * 2], "Kraken", Emoji.SQUID);
                            } else if(theme[i]==2) {
                                ships[i][extra] = new Ship(4, boards[i * 2], "Brigantine", Emoji.SAILBOAT);
                            }
                            break;
                        case 2:
                            if (theme[i]==0) {
                                ships[i][extra] = new Ship(3, boards[i * 2], "Destroyer", Emoji.SMALL_BOAT);
                            } else if(theme[i]==1) {
                                ships[i][extra] = new Ship(3, boards[i * 2], "Sea Serpent", Emoji.DRAGON);
                            } else if(theme[i]==2) {
                                ships[i][extra] = new Ship(3, boards[i * 2], "Schooner", Emoji.CANOE);
                            }
                            break;
                        case 3:
                            if (theme[i]==0) {
                                ships[i][extra] = new Ship(3, boards[i * 2], "Submarine", Emoji.SUBMARINE);
                            } else if(theme[i]==1) {
                                ships[i][extra] = new Ship(3, boards[i * 2], "Megalodon", Emoji.SHARK);
                            } else if(theme[i]==2) {
                                ships[i][extra] = new Ship(3, boards[i * 2], "Sloop", Emoji.SAILBOAT);
                            }
                            break;
                        case 4:
                            if (theme[i]==0) {
                                ships[i][extra] = new Ship(2, boards[i * 2], "Patrol Boat", Emoji.SPEEDBOAT);
                            } else if(theme[i]==1) {
                                ships[i][extra] = new Ship(2, boards[i * 2], "Giant Crab", Emoji.CRAB);
                            } else if(theme[i]==2) {
                                ships[i][extra] = new Ship(2, boards[i * 2], "Rowboat", Emoji.ROWBOAT);
                            }
                            break;
                        case 5:
                            if (theme[i]==0) {
                                ships[i][extra] = new Mine(1, boards[i * 2], "Mine", Emoji.BOMB);
                            } else if(theme[i]==1) {
                                ships[i][extra] = new Mine(1, boards[i * 2], "Vortex", Emoji.VORTEX);
                            } else if(theme[i]==2) {
                                ships[i][extra] = new Mine(1, boards[i * 2], "Bomb", Emoji.BOMB);
                            }
                            break;
                    }
                    extra++;
                }
            }
        }
        d.startSetup();
        return ships;
    }
}
