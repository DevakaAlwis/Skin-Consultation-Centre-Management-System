package GUI;

import javax.swing.*;
import java.awt.*;

public class commonComponent {

//Initiate TitleComponent
    static JPanel titlePanel;
    static JLabel titleLabel;
    static ImageIcon titleIcon= new ImageIcon("Images/logo.png");;
    static Color titlePanelColor = new Color(101, 101, 101);
    static Font titleFont = new Font("Berlin Sans FB", Font.PLAIN, 40);
    static JLabel background;
    static ImageIcon backgroundImage=new ImageIcon("Images/background.png");

    public static JPanel titleComponent(){
        titlePanel = new JPanel();
        titlePanel.setBackground(titlePanelColor);  //set a background color
        titlePanel.setPreferredSize(new Dimension(850, 150));   //set dimensions

        titleLabel = new JLabel();
        titleLabel.setText("Westminster Skin Consultation Centre"); //set the text
        titleLabel.setFont(titleFont);  //set the font
        titleLabel.setIcon(titleIcon);  //set the image icon
        titleLabel.setForeground(Color.WHITE);  //set the foreground
        titleLabel.setHorizontalTextPosition(JLabel.RIGHT); //set the text position
        titleLabel.setIconTextGap(40);  //set the image and text gap

        titlePanel.add(titleLabel); //add the title panel
        return titlePanel;
    }

    public static JLabel backgroundComponent (){
        background=new JLabel();
        background.setIcon(backgroundImage);    //set the background image
        background.setHorizontalAlignment(JLabel.CENTER);   //make it center
        background.setBounds(0,0,850,450);  //set the positions
        return background;
    }
}
