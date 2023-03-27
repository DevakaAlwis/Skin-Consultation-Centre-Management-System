package GUI;

import Classes.Consultation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static Classes.EncryptDecrypt.*;
import static GUI.MenuFrame.listOfConsultation;

public class ConsultationViewFrame extends JFrame implements ActionListener {
    int currentIndex=0; //Initiate current index

    ArrayList<String> images;   //Initiate array list of images

    //Initiate Panels
    JPanel consultationDetailsPanel;
    JPanel patientImagePanel;

    //Initiate Labels
    JLabel patientIDLabel;
    JLabel patientNameLabel;
    JLabel doctorIDLabel;
    JLabel doctorNameLabel;
    JLabel doctorSpecialisationLabel;
    JLabel consultationDateLabel;
    JLabel consultationTimeLabel;
    JLabel consultationCostLabel;
    JLabel consultationNoteLabel;
    JLabel patientImages;

    //Initiate buttons
    JButton backToMenuButton;
    JButton nextImage;
    JButton previousImage;

    //Initiate colors
    Color buttonColor= new Color(199, 199, 203);
    Color fontColor=new Color(47, 47, 47);

    //Initiate fonts
    Font buttonFont=new Font("Berlin Sans FB",Font.PLAIN,25);
    Font textFont=new Font("Berlin Sans FB",Font.PLAIN,17);


    public ConsultationViewFrame(int patientID,int doctorID,LocalDate date,String time){

        JPanel titlePanel= commonComponent.titleComponent();     //call titleComponent method to create the title panel
        consultationViewBodyPanel(patientID,doctorID,date,time);    //call the consultationViewBodyPanel method

        //add action listeners to buttons
        backToMenuButton.addActionListener(this);
        nextImage.addActionListener(this);
        previousImage.addActionListener(this);

        this.setTitle("Westminster Skin Consultation Centre");  //set a title for the frame
        this.setSize(850, 600);  //set width and height
        this.setLocationRelativeTo(null);   //make the frame center
        this.setResizable(false);   //remove resizing the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //exit the application by clicking close icon
        ImageIcon icon=new ImageIcon("Images/logo.png");
        this.setIconImage(icon.getImage());
        this.setLayout(new BorderLayout());

        this.add(titlePanel,BorderLayout.NORTH);
        this.add(consultationDetailsPanel,BorderLayout.CENTER);
        this.setVisible(true);  //make the frame visible
    }

    /**
     * consultationViewBodyPanel method
     */
    public void consultationViewBodyPanel(int patientID,int doctorID,LocalDate date,String time){
        Consultation correctConsultation=null;  //make the correctConsultation null
        for (Consultation consultation:listOfConsultation) {    //check for the correctConsultation
            if(consultation!=null){
                if(consultation.getPatient().getPatientID()==patientID){    //check for patient ID
                    if(consultation.getDoctor().getMedicalLicenceNumber()==doctorID){   //check for doctor ID
                        if(consultation.getConsultationDate()==date){   //check for date
                            if(consultation.getConsultationTime().equals(time)){    //check for time
                                correctConsultation=consultation;   //if all true get the consultation
                            }
                        }
                    }
                }
            }
        }

        //create patient ID
        patientIDLabel = new JLabel();
        patientIDLabel.setBounds(50,10,400,40);
        patientIDLabel.setText("Patient ID: "+ correctConsultation.getPatient().getPatientID());
        patientIDLabel.setFont(textFont);
        patientIDLabel.setForeground(fontColor);

        //create patient name
        patientNameLabel = new JLabel();
        patientNameLabel.setBounds(50,50,400,40);
        patientNameLabel.setText("Patient Name: "+correctConsultation.getPatient().getFirstName()+" "+correctConsultation.getPatient().getSurName());
        patientNameLabel.setFont(textFont);
        patientNameLabel.setForeground(fontColor);

        //create doctor ID
        doctorIDLabel = new JLabel();
        doctorIDLabel.setBounds(50,90,400,40);
        doctorIDLabel.setText("Doctor ID: "+correctConsultation.getDoctor().getMedicalLicenceNumber());
        doctorIDLabel.setFont(textFont);
        doctorIDLabel.setForeground(fontColor);

        //create doctor name
        doctorNameLabel = new JLabel();
        doctorNameLabel.setBounds(50,130,400,40);
        doctorNameLabel.setText("Doctor Name: "+correctConsultation.getDoctor().getFirstName()+" "+correctConsultation.getDoctor().getSurName());
        doctorNameLabel.setFont(textFont);
        doctorNameLabel.setForeground(fontColor);

        //create doctor specialisation
        doctorSpecialisationLabel = new JLabel();
        doctorSpecialisationLabel.setBounds(50,170,400,40);
        doctorSpecialisationLabel.setText("Doctor Specialisation: "+correctConsultation.getDoctor().getSpecialisation());
        doctorSpecialisationLabel.setFont(textFont);
        doctorSpecialisationLabel.setForeground(fontColor);

        //create consultation date
        consultationDateLabel = new JLabel();
        consultationDateLabel.setBounds(50,210,400,40);
        consultationDateLabel.setText("Consultation Date: "+correctConsultation.getConsultationDate());
        consultationDateLabel.setFont(textFont);
        consultationDateLabel.setForeground(fontColor);

        //create consultation time
        consultationTimeLabel = new JLabel();
        consultationTimeLabel.setBounds(50,250,400,40);
        consultationTimeLabel.setText("Consultation Time: "+correctConsultation.getConsultationTime());
        consultationTimeLabel.setFont(textFont);
        consultationTimeLabel.setForeground(fontColor);

        //create consultation cost
        consultationCostLabel = new JLabel();
        consultationCostLabel.setBounds(50,290,400,40);
        consultationCostLabel.setText("Consultation Cost:"+correctConsultation.getConsultationCost());
        consultationCostLabel.setFont(textFont);
        consultationCostLabel.setForeground(fontColor);

        String note=correctConsultation.getConsultationNote();  //get the note
        note=decryptPatientNotes(note); //decrypt the note

        //create consultation note
        consultationNoteLabel = new JLabel();
        consultationNoteLabel.setBounds(50,330,400,40);
        consultationNoteLabel.setText("Consultation Note: "+note);
        consultationNoteLabel.setFont(textFont);
        consultationNoteLabel.setForeground(fontColor);

        //create patient image
        patientImages = new JLabel();
        patientImages.setBounds(50,0,250,250);
        patientImages.setHorizontalAlignment(JLabel.CENTER);
        patientImages.setVerticalAlignment(JLabel.CENTER);

        //create previous image button
        previousImage=new JButton();
        previousImage.setBounds(0,270,175,30);
        previousImage.setText("Previous");
        previousImage.setFocusPainted(false);
        previousImage.setHorizontalTextPosition(JButton.CENTER);
        previousImage.setFont(buttonFont);
        previousImage.setForeground(fontColor);
        previousImage.setBackground(buttonColor);

        //create next image button
        nextImage=new JButton();
        nextImage.setBounds(175,270,175,30);
        nextImage.setText("Next");
        nextImage.setFocusPainted(false);
        nextImage.setHorizontalTextPosition(JButton.CENTER);
        nextImage.setFont(buttonFont);
        nextImage.setForeground(fontColor);
        nextImage.setBackground(buttonColor);

        images=correctConsultation.getImageNames(); //get the image list

        if(images!=null){   //check if the images are null
            if(images.size()!=0) {  //check if the images size is not 0
                decryptImages(images);  //decrypt the image list
                ImageIcon imageIcon = new ImageIcon("UploadImages/" + images.get(currentIndex));    //get the image icon
                patientImages.setIcon(imageIcon);   //set the icon to labe;

                //make the buttons visible
                previousImage.setVisible(true);
                nextImage.setVisible(true);
            }
            else {
                patientImages.setText("No Images Added");   //if no image set the text
                patientImages.setFont(textFont);
                patientImages.setForeground(fontColor);

                //make the buttons visible false
                previousImage.setVisible(false);
                nextImage.setVisible(false);
            }
        }
        else {
            patientImages.setText("No Images Added");   //if no image set the text
            patientImages.setFont(textFont);
            patientImages.setForeground(fontColor);

            //make the buttons visible false
            previousImage.setVisible(false);
            nextImage.setVisible(false);
        }

        patientImagePanel=new JPanel();
        patientImagePanel.setBounds(450,10,350,300);
        patientImagePanel.setLayout(null);

        //add the image
        patientImagePanel.add(patientImages);
        patientImagePanel.add(previousImage);
        patientImagePanel.add(nextImage);

        //create a back button
        backToMenuButton =new JButton();
        backToMenuButton.setBounds(525,325,250,50);
        backToMenuButton.setText("Back To Menu");
        backToMenuButton.setFocusPainted(false);
        backToMenuButton.setHorizontalTextPosition(JButton.CENTER);
        backToMenuButton.setFont(buttonFont);
        backToMenuButton.setForeground(fontColor);
        backToMenuButton.setBackground(buttonColor);

        JLabel background =commonComponent.backgroundComponent ();  //set the background image

        consultationDetailsPanel =new JPanel();
        consultationDetailsPanel.setPreferredSize(new Dimension(250,300));
        consultationDetailsPanel.setLayout(null);

        //add to the panel
        consultationDetailsPanel.add(patientIDLabel);
        consultationDetailsPanel.add(patientNameLabel);
        consultationDetailsPanel.add(doctorIDLabel);
        consultationDetailsPanel.add(doctorNameLabel);
        consultationDetailsPanel.add(doctorSpecialisationLabel);
        consultationDetailsPanel.add(consultationDateLabel);
        consultationDetailsPanel.add(consultationTimeLabel);
        consultationDetailsPanel.add(consultationCostLabel);
        consultationDetailsPanel.add(consultationNoteLabel);
        consultationDetailsPanel.add(patientImagePanel);
        consultationDetailsPanel.add(backToMenuButton);
        consultationDetailsPanel.add(background);
    }

    /**
     * decryptImages method
     */
    public void decryptImages(ArrayList<String> images){
        for (String imageName:images) { //run a for each to get the image name
            String fileName="UploadImages/"+imageName;  //make the file path
            try {
                decryptImage(fileName); //call the decryptImage method to decrypt the image
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * decryptImages method
     */
    public void encryptImages(ArrayList<String> images){
        for (String imageName:images) { //run a for each to get the image name
            String fileName="UploadImages/"+imageName;  //make the file path
            try {
                encryptImage(fileName); //call the encryptImage method to encrypt the image
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * goBackToMenu method
     */
    private void goBackToMenu(){
        if(images!=null) {  //check if the image is not null
            encryptImages(images);  //if not null call encryptImages to encrypt the images
        }
        this.dispose(); //dispose the current frame
        MenuFrame menuFrame =new MenuFrame();   //create a new frame of menuFrame
        menuFrame.setVisible(true); //set is visible
    }

    /**
     * actionPerformed method
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Back To Menu")){
            goBackToMenu(); //call goBackToMenu method when back to menu event performed
        }
        else if(event.getActionCommand().equals("Previous")){   //change the previous image when previous event performed
            currentIndex--; //decrement currentIndex
            if (currentIndex < 0) { //check if it is less than 0
                currentIndex = images.size() - 1;   //if so make the currentIndex position last element of the image array list
            }
            ImageIcon imageIcon=new ImageIcon("UploadImages/"+images.get(currentIndex));    //get the image
            patientImages.setIcon(imageIcon);   //set the image

        }
        else if(event.getActionCommand().equals("Next")){   //change the next image when next event performed
            currentIndex++; //increment currentIndex
            if(currentIndex >= images.size()) { //check if it is greater than array size
                currentIndex = 0;   //if so make it 0
            }
            ImageIcon imageIcon=new ImageIcon("UploadImages/"+images.get(currentIndex));    //get the image
            patientImages.setIcon(imageIcon);   //set the image
        }
    }
}


//reference
//https://stackoverflow.com/questions/18405660/how-to-set-component-size-inside-container-with-boxlayout
//lecture notes 7,8,9
//https://www.geeksforgeeks.org/java-awt-boxlayout-class/
//https://www.codejava.net/java-se/swing/setting-column-width-and-row-height-for-jtable