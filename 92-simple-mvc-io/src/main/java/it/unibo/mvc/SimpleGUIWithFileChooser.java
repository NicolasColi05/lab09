package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final int HPROPORTION = 2;
    private static final int WPROPORTION = 6;
    private final JFrame frame = new JFrame();
    private final Controller controller;

    private SimpleGUIWithFileChooser() {
        this.controller = new Controller();
        final JPanel canvas2 = new JPanel();
        canvas2.setLayout(new BorderLayout());
        final JTextField text = new JTextField();
        text.setEditable(false);
        text.setText(controller.getCurrentPath());
        canvas2.add(text, BorderLayout.CENTER);
        final JButton browse = new JButton("Browse");
        canvas2.add(browse, BorderLayout.EAST);
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        final JButton save = new JButton("save");
        canvas.add(textArea, BorderLayout.CENTER);
        canvas.add(save, BorderLayout.SOUTH);
        canvas.add(canvas2, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(canvas);

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.writeString(textArea.getText());
                    textArea.setText("");
                } catch (final FileNotFoundException message) {
                    message.printStackTrace(); //NOPMD
                }
            }
        });

        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                final FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
                chooser.setFileFilter(filter);
                final int value = chooser.showSaveDialog(null);
                if (JFileChooser.APPROVE_OPTION == value) {
                    controller.setFile(chooser.getSelectedFile());
                    text.setText(controller.getCurrentPath());
                }
                if (JFileChooser.ERROR_OPTION == value) {
                    JOptionPane.showMessageDialog(frame, "An ERROR has occurred");
                }
            }
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int h = screen.height;
        final int w = screen.width;
        frame.setSize(h / HPROPORTION, w / WPROPORTION);
        frame.setVisible(true);
    }

    /**
     * @param args .
     */
    public static void main(final String... args) {
        new SimpleGUIWithFileChooser().display();
    }

}
