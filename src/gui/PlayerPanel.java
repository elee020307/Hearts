package gui;

import card.Card;
import card.Deck;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerPanel extends JPanel {
    private final String[] DEFAULT_NAMES = new String[]{"South","West","North","East"};
    private int _id;
    private HandPanel _hand;
    private Label _label;
    private static int _height;
    private static int _width;

    public PlayerPanel(String name, int id, int orientation){
        assert(id > 0 && id < 4);
        _id = id;
        _label = new Label(name, orientation);

        _hand = new HandPanel(orientation);

        setLayout(new BoxLayout(this, orientation));
        if(orientation == BoxLayout.X_AXIS){
            setPreferredSize(new Dimension(_width, _height));
        }else{
            setPreferredSize(new Dimension( (_height*2), _width));
        }


        setOpaque(false);
        add(_label);
    }

    public PlayerPanel(int id, int orientation){
        this("", id, orientation);
        SetName(DEFAULT_NAMES[id]);
    }

    public int Id(){return _id;}

    public String Name(){return _label.name();}

    public void SetName(String name){_label.set_name(name); }

    public void UpdateType(String type) { _label.set_type(type); }

    public void Highlight(){_label.setFontColor(Color.RED);}

    public void Unhighlight(){_label.setFontColor(Color.WHITE);}

    public void UpdateHand(List<Card> hand) throws Exception {
        assert _hand != null;
        remove(_hand);
        _hand.update(hand);
        add(_hand);
        revalidate();
        repaint();
    }


    public void UpdateScore(int score) throws Exception{
        _label.set_score(score);
    }

    public static void SetDimension(int width, int height){
        _width = width;
        _height = height;
    }

    private class Label extends JPanel{
        private JLabel _name;
        private JLabel _score;
        private JLabel _type;


        private Label(String name, int orientation){
            _name = new JLabel(name);
            _score = new JLabel("0");
            _type = new JLabel();
            _name.setFont(HeartsFrame.DEFAULT_FONT);
            _score.setFont(HeartsFrame.DEFAULT_FONT);
            _type.setFont(HeartsFrame.DEFAULT_FONT);
            setLayout(new BoxLayout(this, orientation));
            setOpaque(false);

            setFontColor(Color.WHITE);
            add(_name);
            add(Box.createRigidArea(new Dimension(15,15)));
            add(_type);
            add(Box.createRigidArea(new Dimension(15,15)));
            add(_score);
            add(Box.createRigidArea(new Dimension(15,15)));
        }


        private void set_score(int score){
            _score.setText(score + "");
        }

        private String name(){
            return _name.getText();
        }

        private void set_name(String name){
            _name.setText(name);
        }

        private void set_type(String type) {
            _type.setText(type);
        }

        private void setFontColor(Color c){
            _name.setForeground(c);
            _score.setForeground(c);
            revalidate();
            repaint();
        }
    }

    private class HandPanel extends JLayeredPane{
        private int _orientation;
        private List<CardImage> _cards;
        private Point _origin;
        private int _offset;

        private HandPanel(int orientation){
            super();
            _orientation = orientation;
            _cards = new ArrayList<>();
            _origin = this.getLocation();
/*
            if(orientation == BoxLayout.X_AXIS){
                _origin.x += 50;
            }else{
                _origin.y += 50;
            }*/

            _offset = (int) (CardImage.GetWidth() * 0.4);

            setLayout(null);
            setOpaque(false);
        }

        private void update(List<Card> hand) throws Exception {
            _cards.clear();
            removeAll();
            Deck.Sort(hand);

            Point start = new Point(_origin);
            for(int i = 0; i < hand.size(); i++){
                CardImage card = CardImage.Card(hand.get(i));
                card.setBounds(start.x, start.y, card.getWidth(), card.getHeight());
                _cards.add(card);
                add(card, new Integer(i));

                if(_orientation == BoxLayout.X_AXIS){start.x += _offset;}
                else{start.y += _offset;}

            }
            revalidate();
            repaint();
        }
    }

}
