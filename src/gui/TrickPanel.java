package gui;

import card.Trick;
import javax.swing.*;
import java.awt.*;

/**
 * TBD
 * <p/>
 * User: elee
 * Date: 10/14/2016
 * Time: 12:38 PM
 */
public class TrickPanel extends JPanel{
    private Trick _trick;
    private CardImage[] _cards;

    public TrickPanel(){
        super();
        _cards = new CardImage[4];
        setOpaque(false);
        setLayout(new GridBagLayout());
    }


    public void Update(Trick trick, int start) throws Exception {
        removeAll();
        _trick = trick;

        //make this the starting player's index
        int i = start % 4;
        _cards[i] = CardImage.Card(_trick.First());
        i = (i+1) % 4;
        _cards[i] = CardImage.Card(_trick.Second());
        i = (i+1) % 4;
        _cards[i] = CardImage.Card(_trick.Third());
        i = (i+1) % 4;
        _cards[i] = CardImage.Card(_trick.Fourth());

        add(_cards[0], 1, 2);
        add(_cards[1], 0, 1);
        add(_cards[2], 1, 0);
        add(_cards[3], 2, 1);

        revalidate();
        repaint();
    }


    private void add(CardImage card, int x, int y){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        add(card, c);
    }
}
