package gr.teicrete.ie.oop2.lab1.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author manosgem
 */
public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel sliderPanel;
    private JPanel colourPanel;
    private JPanel colourPanell;
    private JPanel colourPanelr;
    private JPanel infoPanel;

    private JLabel bgColourLabel;
    private JLabel bgColourPreview;
    private JLabel fgColourLabel;
    private JLabel fgColourPreview;
    private JLabel buttonSelectedTextLabel;
    private JLabel buttonSelectedLabel;

    private JColorChooser bgColourChooser;
    private JColorChooser fgColourChooser;

    private JSlider slider;

    private ActionListener buttonSelectedAL;
    private int pperc = 0;

    public MainWindow() {

        mainPanel = new JPanel();
        sliderPanel = new JPanel();
        colourPanel = new JPanel();
        colourPanell = new JPanel();
        colourPanelr = new JPanel();
        infoPanel = new JPanel();

        bgColourLabel = new JLabel("Background Colour:");
        bgColourPreview = new JLabel("     ");
        fgColourLabel = new JLabel("Foreground Colour:");
        fgColourPreview = new JLabel("     ");
        buttonSelectedTextLabel = new JLabel("ButtonSelected:");
        buttonSelectedLabel = new JLabel();

        slider = new JSlider(JSlider.VERTICAL, 0, 100, 0);

        colourPanel.setLayout(new GridLayout(1, 2));

        ((FlowLayout) colourPanell.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) colourPanelr.getLayout()).setAlignment(FlowLayout.LEFT);

        bgColourPreview.setBackground(new JButton().getBackground());
        bgColourPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bgColourPreview.setOpaque(true);

        bgColourPreview.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Color bgColour = JColorChooser.showDialog(bgColourPreview, "BackGround Colour:", Color.white);

                bgColourPreview.setBackground(bgColour);
                for(int i=0;i<mainPanel.getComponentCount();i++){
                    mainPanel.getComponent(i).setBackground(bgColour);
                }
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        colourPanelr.add(bgColourLabel);
        colourPanelr.add(bgColourPreview);

        fgColourPreview.setBackground(new JButton().getForeground());
        fgColourPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        fgColourPreview.setOpaque(true);

        fgColourPreview.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Color fgColour = JColorChooser.showDialog(fgColourPreview, "Foreground Colour:", Color.white);

                fgColourPreview.setBackground(fgColour);
                for(int i=0;i<mainPanel.getComponentCount();i++){
                    mainPanel.getComponent(i).setForeground(fgColour);
                }
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        colourPanell.add(fgColourLabel);
        colourPanell.add(fgColourPreview);

        colourPanel.add(colourPanell);
        colourPanel.add(colourPanelr);

        add(colourPanel, BorderLayout.NORTH);

        ((FlowLayout) infoPanel.getLayout()).setAlignment(FlowLayout.LEFT);

        infoPanel.add(buttonSelectedTextLabel);
        infoPanel.add(buttonSelectedLabel);

        add(infoPanel, BorderLayout.SOUTH);

        ((FlowLayout) mainPanel.getLayout()).setAlignment(FlowLayout.LEFT);

        buttonSelectedAL = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Object source = e.getSource();

                if (source instanceof JButton) {
                    buttonSelectedLabel.setText(((JButton) source).getText());
                }
            }
        };

        add(mainPanel, BorderLayout.CENTER);

        sliderPanel.setLayout(new GridLayout(0, 1));

        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);

        Hashtable labelTable = new Hashtable();
        for (int i = 0; i < 101; i += 5) {
            labelTable.put(new Integer(i), new JLabel(Integer.toString(i)));
        }

        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int perc = source.getValue();

                    if (perc > pperc) {
                        
                        for(int i =1; i<=perc-pperc; i++ ){
                            JButton button = new JButton("Button "+(pperc+i));
                            
                            button.addActionListener(buttonSelectedAL);
                            button.setBackground(bgColourPreview.getBackground());
                            button.setForeground(fgColourPreview.getBackground());
                            mainPanel.add(button);
                        }
                        
                        pperc = perc;
                    } else if (perc < pperc) {
                        
                        for(int j= pperc; j>perc;j--){
                            mainPanel.remove(mainPanel.getComponent(mainPanel.getComponentCount()-1));
                        }
                        pperc = perc;
                    }
                    
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        });

        sliderPanel.add(slider);

        add(sliderPanel, BorderLayout.EAST);

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
