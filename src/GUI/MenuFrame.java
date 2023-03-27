package GUI;

import Classes.Consultation;
import Classes.Person;

import javax.swing.*;


import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuFrame extends JFrame implements ActionListener{
    public static ArrayList<Person> listOfPatient=new ArrayList<Person>();  //create a static arraylist to store the patient details
    public static ArrayList<Consultation> listOfConsultation= new ArrayList<Consultation>();    //create a static arraylist to store the consultations details

    //Initiate buttons
    JButton viewDoctorButton;
    JButton viewConsultationButton;

    //Initiate ImageIcons
    ImageIcon viewDoctorImage;
    ImageIcon viewConsultationImage;

    //Initiate Panels
    JPanel menuBodyPanel;

    //Initiate Colors
    Color buttonColor= new Color(199, 199, 203);
    Color fontColor=new Color(47, 47, 47);

    //Initiate Border
    Border buttonBorder=BorderFactory.createRaisedBevelBorder();

    //Initiate Font
    Font font=new Font("Berlin Sans FB",Font.PLAIN,25);

    public MenuFrame(){
        this.setTitle("Westminster Skin Consultation Centre");  //set a title for the frame
        this.setSize(850, 600);  //set width and height
        this.setLocationRelativeTo(null);   //make the frame center
        this.setResizable(false);   //remove resizing the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //exit the application by clicking close icon
        ImageIcon icon=new ImageIcon("Images/logo.png");    //create an image icon
        this.setIconImage(icon.getImage()); //set the imageIcon to the frame icon
        this.setLayout(new BorderLayout()); //set layout for border layout

        JPanel titlePanel= commonComponent.titleComponent();    //call titleComponent method to create the title panel

        homeMenuPanel();    //call the homeMenuPanel to create the menu panel
        menuBodyPanel.setVisible(true); //set the menu panel visible

        //create action listeners
        viewDoctorButton.addActionListener(this);
        viewConsultationButton.addActionListener(this);

        this.add(titlePanel,BorderLayout.NORTH);    //set the title panel north
        this.add(menuBodyPanel,BorderLayout.CENTER);    //set the menu body panel center of the layout

        this.setVisible(true);  //make the frame visible
    }

    /**
     * openDoctorList method
     */
    private void openDoctorList(){
        this.dispose(); //dispose the current frame
        DoctorListFrame doctorListFrame=new DoctorListFrame();  //create a new frame of doctorListFrame
        doctorListFrame.setVisible(true);   //set is visible
    }

    /**
     * openDoctorList method
     */
    private void openViewConsultations(){
        this.dispose(); //dispose the current frame
        ViewConsultationsFrame viewConsultationsFrame = new ViewConsultationsFrame();   //create a new frame of viewConsultationsFrame
        viewConsultationsFrame.setVisible(true);    //set it visible
    }

    /**
     * actionPerformed method
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        if(event.getActionCommand().equals("View Doctor List")){
            openDoctorList();   //call openDoctorList method when view doctor list event performed
        }
        else if(event.getActionCommand().equals("View Consultations")){
            openViewConsultations();    //call openViewConsultations method when view consultations event performed
        }
    }

    /**
     * homeMenuPanel method
     */
    public void homeMenuPanel() {
        //create new imageIcons
        viewDoctorImage=new ImageIcon("Images/doctor.png");
        viewConsultationImage=new ImageIcon("Images/view.png");

        viewDoctorButton=new JButton();
        viewDoctorButton.setBounds(100,50,300,300); //set the position and the width and height
        viewDoctorButton.setText("View Doctor List");   //set the text
        viewDoctorButton.setFocusPainted(false);    //remove the boarder when clicked
        viewDoctorButton.setIcon(viewDoctorImage);  //set the icon
        viewDoctorButton.setHorizontalTextPosition(JButton.CENTER); //make it center
        viewDoctorButton.setVerticalTextPosition(JButton.BOTTOM);   //make it center
        viewDoctorButton.setFont(font); //set the font
        viewDoctorButton.setIconTextGap(30);    //set the gap between image and text
        viewDoctorButton.setForeground(fontColor);  //set font color
        viewDoctorButton.setBackground(buttonColor);    //set button color
        viewDoctorButton.setBorder(buttonBorder);   //set a button border

        viewConsultationButton=new JButton();
        viewConsultationButton.setBounds(450,50,300,300); //set the position and the width and height
        viewConsultationButton.setText("View Consultations");   //set the text
        viewConsultationButton.setFocusPainted(false);  //remove the boarder when clicked
        viewConsultationButton.setIcon(viewConsultationImage);  //set the icon
        viewConsultationButton.setHorizontalTextPosition(JButton.CENTER);   //make it center
        viewConsultationButton.setVerticalTextPosition(JButton.BOTTOM);     //make it center
        viewConsultationButton.setFont(font);   //set the font
        viewConsultationButton.setIconTextGap(30);  //set the gap between image and text
        viewConsultationButton.setForeground(fontColor);    //set font color
        viewConsultationButton.setBackground(buttonColor);  //set button color
        viewConsultationButton.setBorder(buttonBorder); //set a button border

        JLabel background =commonComponent.backgroundComponent ();  //create the background image

        menuBodyPanel=new JPanel();
        menuBodyPanel.setPreferredSize(new Dimension(850,450)); //set the dimensions of the panel
        menuBodyPanel.setLayout(null);  //make the layout null

        menuBodyPanel.add(viewDoctorButton);    //add the viewDoctor button
        menuBodyPanel.add(viewConsultationButton);  //add the viewConsultation button
        menuBodyPanel.add(background);  //add the background image
    }
}

//reference
//lecture notes 7,8,9
//https://www.youtube.com/watch?v=Kmgo00avvEw&t=7058s
//https://stackoverflow.com/questions/9361658/disable-jbutton-focus-border
//https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
