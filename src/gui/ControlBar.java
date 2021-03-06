package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlBar extends JPanel {
    private HeartsFrame _gui;
    private Timer _timer;

    public ControlBar(HeartsFrame gui){
        super();
        _gui = gui;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JButton startButton = new JButton("Play");
        JButton stepButton = new JButton("Step");
        JButton stopButton = new JButton("Pause");

        startButton.setFont(HeartsFrame.DEFAULT_FONT);
        stepButton.setFont(HeartsFrame.DEFAULT_FONT);
        stopButton.setFont(HeartsFrame.DEFAULT_FONT);

        startButton.addActionListener(StartGame());
        stopButton.addActionListener(StopGame());
        stepButton.addActionListener(StepGame());

        add(startButton);
        add(stepButton);
        add(stopButton);
    }

    public void ResetTimer(int time) {
        if (_timer == null) return;
        boolean running = _timer.isRunning();
        _timer.stop();
        _timer.setDelay(time);
        if (running) _timer.start();
    }

    public ActionListener ChangePlayers() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(_timer != null) _timer.stop();
                ChoosePlayerFrame frame = new ChoosePlayerFrame(_gui);
                frame.createAndShowGui();
            }
        };
    }

    public ActionListener NewGame() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _gui.NewGame();
            }
        };
    }

    public ActionListener RunHeadless() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HeadlessFrame frame = new HeadlessFrame(_gui);
                frame.createAndShowGui();
            }
        };
    }

    public ActionListener StartGame() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(_timer == null){
                    _timer = _gui.GetGameTimer();
                }
                _timer.start();
            }
        };
    }

    public ActionListener StopGame() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(_timer == null) return;
                _timer.stop();

            }
        };
    }

    public ActionListener StepGame() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (_timer != null) _timer.stop();
                _gui.Step(null);
            }
        };
    }
}
