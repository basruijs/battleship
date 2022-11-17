import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display implements ActionListener {
    boolean hide = true;
    JTextArea censorShip = new JTextArea();
    int width = 900;
    int height = 600;
    JLabel[] themeLabels = new JLabel[2];
    JComboBox[] themeSelectors = new JComboBox[2];
    JTextField input;
    JTable[] maps = new JTable[4];
    JTextArea textArea;
    JLabel label, player;
    JButton flip, shoot, place, start;
    JSlider[] sliders = new JSlider[6];
    JLabel[] labels = new JLabel[sliders.length];
    JLabel[] sliderCounters = new JLabel[sliders.length];
    JFrame f;
    JTextArea selectedDisplay;
    boolean shootPressed = false;
    boolean flipPressed = false;
    boolean placePressed = false;
    boolean startPressed = false;
    public String inputString = "";
    Display() {
        f= new JFrame("Battleship");
        String[] themes = {"Naval", "Seamonster", "Pirate"};

        for (int i = 0; i < themeSelectors.length; i++) {
            themeSelectors[i] = new JComboBox(themes);
            themeLabels[i] = new JLabel();
            themeSelectors[i].setBounds(50+i*250, 300, 200, 20);
            themeLabels[i].setBounds(50+i*250, 280, 200, 20);
            f.add(themeLabels[i]);
            f.add(themeSelectors[i]);
        }
        themeLabels[0].setText("Player 1 theme");
        themeLabels[1].setText("Player 2 theme");

        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setBounds(20, 10+i*30, 200, 20);
            labels[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            labels[i].setHorizontalAlignment(SwingConstants.RIGHT);
            f.add(labels[i]);
        }

        labels[0].setText("(5) Carriers");
        labels[1].setText("(4) Battleships");
        labels[2].setText("(3) Destroyers");
        labels[3].setText("(3) Submarines");
        labels[4].setText("(2) Patrol Boats");
        labels[5].setText("Mines");

        for (int i = 0; i < sliders.length; i++) {
            sliders[i] = new JSlider(0, 5, 1);
            sliders[i].setBounds(250, 10+i*30, 200, 20);


            f.add(sliders[i]);
        }

        for (int i = 0; i < sliders.length; i++) {
            sliderCounters[i] = new JLabel();
            sliderCounters[i].setBounds(500, 10+i*30, 200, 20);
            f.add(sliderCounters[i]);
        }

        start = new JButton(Emoji.CHECKMARK);
        start.setBounds((width/2)-25, 440, 50, 50);



        label = new JLabel();
        label.setBounds(width/2-250, 200, 500, 100);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        player = new JLabel();
        player.setBounds(width/2-250, 100, 500, 100);
        player.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        player.setHorizontalAlignment(SwingConstants.CENTER);

        input = new JTextField();
        input.setBounds(10, 415, 300, 20);

        maps[0] = new JTable(Battleship.size,Battleship.size);
        maps[0].setBounds(10, 10, 200, 200);
        maps[0].setRowHeight(20);

        maps[1] = new JTable(Battleship.size,Battleship.size);
        maps[1].setBounds(10, 220, 200, 200);
        maps[1].setRowHeight(20);

        maps[2] = new JTable(Battleship.size,Battleship.size);
        maps[2].setBounds(width-20-200, 10, 200, 200);
        maps[2].setRowHeight(20);

        maps[3] = new JTable(Battleship.size,Battleship.size);
        maps[3].setBounds(width-20-200, 220, 200, 200);
        maps[3].setRowHeight(20);

        shoot = new JButton(Emoji.BOMB);
        shoot.setBounds((width/2)-25, 440, 50, 50);

        flip = new JButton(Emoji.TURN_LEFT);
        flip.setBounds((width/2)-25, 440, 50, 50);

        place = new JButton(Emoji.CHECKMARK);
        place.setBounds((width/2)-25, 380, 50, 50);

        // adding action listener
        shoot.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                shootPressed=true;
            }
        });

        flip.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flipPressed=true;
            }
        });

        place.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                placePressed=true;
            }
        });

        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startPressed=true;
            }
        });

        // adding components to frame
        f.add(start);
        f.add(player);
        // setting size, layout and visibility of frame
        f.setSize(width,height);
        f.setLayout(null);
        f.setVisible(true);
    }
    // defining the actionPerformed method to generate an event on buttons
    public void actionPerformed(ActionEvent e) {

    }

    public int getSelectedX(int map){
        return maps[map].getSelectedColumn();
    }

    public int getSelectedY(int map){
        return maps[map].getSelectedRow();
    }

    public int getTheme(int player){
        return themeSelectors[player].getSelectedIndex();
    }


    public void setMap(String text, int x, int y, int map){
//        f.setSize(900,600);
        maps[map].setValueAt("<html><bold>" + text +"</bold></html>", y, x);
    }

    public void setLabel(String text){
        label.setText("<html><bold>" + text +"</bold></html>");
    }

    public void setPlayer(String text){
        player.setText("<html><bold>" + text +"</bold></html>");
    }

    public void startSetup(){
        startPressed = false;
        start.setVisible(false);
        for (int i = 0; i < 2; i++) {
            themeLabels[i].setVisible(false);
            themeSelectors[i].setVisible(false);
        }
        for (int i = 0; i < sliders.length; i++) {
            sliders[i].setVisible(false);
            labels[i].setVisible(false);
            sliderCounters[i].setVisible(false);
        }
        for (JTable map : maps) {
            f.add(map);
        }
        f.add(flip);
        f.add(place);
        f.add(label);
        if(hide) {
            f.setSize((width/4)*3, height);

        }
    }

    public void fireAtWill(){
        setLabel("Prepare your cannons!");
        flip.setVisible(false);
        place.setVisible(false);
        f.add(shoot);
        f.add(player);
    }

    public void updateSliders() {
        for (int i = 0; i < sliderCounters.length; i++) {
            sliderCounters[i].setText(String.valueOf(sliders[i].getValue()));
        }
    }

    public int getSliderValue(int slider){
        return sliders[slider].getValue();
    }
}

