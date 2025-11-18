package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String TITLE = "My first java graphical interface";
    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller;

    private SimpleGUI() {
        this.controller = new Controller();
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        final JButton save = new JButton("save");
        canvas.add(textArea, BorderLayout.CENTER);
        canvas.add(save, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(canvas);

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.writeString(textArea.getText());
                } catch (final FileNotFoundException message) {
                    message.printStackTrace(); //NOPMD
                }
            }
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int h = screen.height;
        final int w = screen.width;
        frame.setSize(h, w);
        frame.setVisible(true);
    }

    /**
     * @param args args.
     */
    public static void main(final String... args) {
        new SimpleGUI().display();
    }
}
