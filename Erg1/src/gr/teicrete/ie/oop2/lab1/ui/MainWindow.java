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

    private JButton button;
    private JButton button2;

    private JSlider slider;

    private float pperc=0;
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

        button = new JButton("Button 1");
        button2 = new JButton("Button 2");

        slider = new JSlider(JSlider.VERTICAL, 0, 100, 0);

        colourPanel.setLayout(new GridLayout(1, 2));

        ((FlowLayout) colourPanell.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) colourPanelr.getLayout()).setAlignment(FlowLayout.LEFT);

        bgColourPreview.setBackground(button.getBackground());
        bgColourPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bgColourPreview.setOpaque(true);

        bgColourPreview.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Color bgColour = JColorChooser.showDialog(bgColourPreview, "BackGround Colour:", button.getBackground());

                button.setBackground(bgColour);
                bgColourPreview.setBackground(button.getBackground());
            }
        });

        colourPanelr.add(bgColourLabel);
        colourPanelr.add(bgColourPreview);

        fgColourPreview.setBackground(button.getForeground());
        fgColourPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        fgColourPreview.setOpaque(true);

        fgColourPreview.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Color fgColour = JColorChooser.showDialog(fgColourPreview, "Foreground Colour:", button.getForeground());

                button.setForeground(fgColour);
                fgColourPreview.setBackground(button.getForeground());
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

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                buttonSelectedLabel.setText(button.getText());
                System.out.println("size: "+button.getSize());
            }
        });

        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                buttonSelectedLabel.setText(button2.getText());
                
            }
        });

        mainPanel.add(button);
        mainPanel.add(button2);

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

        slider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()){
                    float perc = source.getValue();
                    
                    System.out.println("prefSize: "+button.getPreferredSize());
                    
                    if(perc>pperc){
                        button.setSize(new Dimension((int)(button.getPreferredSize().getWidth()*(1+(perc/100.0f))),(int)(button.getPreferredSize().getHeight()*(1+(perc/100.0f)))));
                        pperc = perc;
                    }else if(perc<pperc){
                        button.setSize(new Dimension((int)(button.getWidth()*(1-((pperc-perc)/100.0f))),(int)(button.getHeight()*(1-((pperc-perc)/100.0f)))));
                        pperc = perc;
                    }     
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
