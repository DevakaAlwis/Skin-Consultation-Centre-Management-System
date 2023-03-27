package GUI;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static Classes.EncryptDecrypt.encryptImage;

public class PatientDetailsFrame extends JFrame implements ActionListener{
    ArrayList<Object> selectedDetailsFromAvailabilityList;  //create an array list for selectedDetailsFromAvailabilityList
    ArrayList<Object> consultationDetailList;   //create an arraylist for consultationDetailList
    File[] imageFiles;  //create a file object
    int patientID;  //create an integer

    //Initiate panels
    JPanel patientDetailsBodyPanel;

    //Initiate labels
    JLabel bodyTitleLabel;
    JLabel patientFirstNameLabel;
    JLabel patientLastNameLabel;
    JLabel patientIDLabel;
    JLabel patientPhoneNumberLabel;
    JLabel patientNoteLabel;
    JLabel dateOfBirthLabel;
    JLabel patientImagesUploadLabel;
    JLabel patientImagesUploadedLabel;

    //Initiate date chooser
    JDateChooser dateOfBirth;

    //Initiate text fields
    JTextField patientFirstNameText;
    JTextField patientLastNameText;
    JTextField patientIDText;
    JTextField patientPhoneNumberText;

    //Initiate text area
    JTextArea patientNote;

    //Initiate file chooser
    JFileChooser patientImageFiles;

    //Initiate button
    JButton patientImagesUploadButton;
    JButton backButton;
    JButton bookConsultationButton;

    //Initiate colors
    Color buttonColor= new Color(199, 199, 203);
    Color fontColor=new Color(47, 47, 47);

    //Initiate fonts
    Font TitleFont=new Font("Berlin Sans FB",Font.PLAIN,28);
    Font buttonFont=new Font("Berlin Sans FB",Font.PLAIN,25);
    Font textFont=new Font("Berlin Sans FB",Font.PLAIN,17);

    public PatientDetailsFrame(ArrayList<Object> selectedDetailsList) {
        selectedDetailsFromAvailabilityList=new ArrayList<>(selectedDetailsList);
        consultationDetailList=selectedDetailsList;

        JPanel titlePanel= commonComponent.titleComponent();     //call titleComponent method to create the title panel

        patientDetailsPanel();  //call the patientDetailsPanel method

        //create action listeners for buttons
        patientImagesUploadButton.addActionListener(this);
        bookConsultationButton.addActionListener(this);
        backButton.addActionListener(this);

        this.setTitle("Westminster Skin Consultation Centre");  //set a title for the frame
        this.setSize(850, 600);  //set width and height
        this.setLocationRelativeTo(null);   //make the frame center
        this.setResizable(false);   //remove resizing the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //exit the application by clicking close icon
        ImageIcon icon=new ImageIcon("Images/logo.png");
        this.setIconImage(icon.getImage());
        this.setLayout(new BorderLayout());

        this.add(titlePanel,BorderLayout.NORTH);
        this.add(patientDetailsBodyPanel,BorderLayout.CENTER);
        this.setVisible(true);  //make the frame visible
    }

    /**
     * patientDetailsPanel method
     */
    private void patientDetailsPanel(){
        //create title
        bodyTitleLabel = new JLabel();
        bodyTitleLabel.setBounds(25,25,300,40);
        bodyTitleLabel.setText("Patient Details:");
        bodyTitleLabel.setFont(TitleFont);
        bodyTitleLabel.setForeground(fontColor);

        //create firstname
        patientFirstNameLabel = new JLabel();
        patientFirstNameLabel.setBounds(50,100,100,40);
        patientFirstNameLabel.setText("First Name:");
        patientFirstNameLabel.setFont(textFont);
        patientFirstNameLabel.setForeground(fontColor);

        //create firstname text field
        patientFirstNameText =new JTextField();
        patientFirstNameText.setBounds(175,100,150,40);
        patientFirstNameText.setFont(textFont);
        patientFirstNameText.setForeground(fontColor);

        //create lastname
        patientLastNameLabel = new JLabel();
        patientLastNameLabel.setBounds(425,100,100,40);
        patientLastNameLabel.setText("Last Name:");
        patientLastNameLabel.setFont(textFont);
        patientLastNameLabel.setForeground(fontColor);

        //create lastname text field
        patientLastNameText =new JTextField();
        patientLastNameText.setBounds(550,100,150,40);
        patientLastNameText.setFont(textFont);
        patientLastNameText.setForeground(fontColor);

        //create patient ID
        patientIDLabel = new JLabel();
        patientIDLabel.setBounds(50,150,100,40);
        patientIDLabel.setText("Patient ID:");
        patientIDLabel.setFont(textFont);
        patientIDLabel.setForeground(fontColor);

        //create patient ID text field
        patientIDText =new JTextField();
        patientIDText.setBounds(175,150,100,40);
        patientIDText.setFont(textFont);
        patientIDText.setForeground(fontColor);

        //create note
        patientNoteLabel  = new JLabel();
        patientNoteLabel.setBounds(425,150,50,40);
        patientNoteLabel.setText("Note:");
        patientNoteLabel.setFont(textFont);
        patientNoteLabel.setForeground(fontColor);

        //create note text area
        patientNote =new JTextArea(5,7);
        patientNote.setBounds(500,150,260,100);
        patientNote.setFont(textFont);
        patientNote.setForeground(fontColor);

        //create date of birth
        dateOfBirthLabel = new JLabel();
        dateOfBirthLabel.setBounds(50,200,100,40);
        dateOfBirthLabel.setText("Date Of Birth:");
        dateOfBirthLabel.setFont(textFont);
        dateOfBirthLabel.setForeground(fontColor);

        //create date of birth field
        dateOfBirth = new JDateChooser();
        dateOfBirth.setDateFormatString("yyyy-MM-dd");
        dateOfBirth.setBounds(175,200,150,40);
        dateOfBirth.setFont(textFont);
        dateOfBirth.setForeground(fontColor);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dateOfBirth.getDateEditor();
        editor.setEditable(false);

        //create phone number
        patientPhoneNumberLabel = new JLabel();
        patientPhoneNumberLabel.setBounds(50,250,120,40);
        patientPhoneNumberLabel.setText("Mobile Number:");
        patientPhoneNumberLabel.setFont(textFont);
        patientPhoneNumberLabel.setForeground(fontColor);

        //create phone number text field
        patientPhoneNumberText =new JTextField();
        patientPhoneNumberText.setBounds(175,250,150,40);
        patientPhoneNumberText.setFont(textFont);
        patientPhoneNumberText.setForeground(fontColor);
        patientPhoneNumberText.setColumns(10);

        //create image upload
        patientImagesUploadLabel = new JLabel();
        patientImagesUploadLabel.setBounds(425,250,120,40);
        patientImagesUploadLabel.setText("Choose Images:");
        patientImagesUploadLabel.setFont(textFont);
        patientImagesUploadLabel.setForeground(fontColor);

        //create image upload button
        patientImagesUploadButton= new JButton();
        patientImagesUploadButton.setBounds(550,255,95,30);
        patientImagesUploadButton.setText("Upload");
        patientImagesUploadButton.setFocusPainted(false);
        patientImagesUploadButton.setHorizontalTextPosition(JButton.CENTER);
        patientImagesUploadButton.setFont(textFont);
        patientImagesUploadButton.setForeground(fontColor);
        patientImagesUploadButton.setBackground(buttonColor);

        //create Image uploaded
        patientImagesUploadedLabel = new JLabel();
        patientImagesUploadedLabel.setBounds(680,250,120,40);
        patientImagesUploadedLabel.setFont(textFont);
        patientImagesUploadedLabel.setForeground(fontColor);

        //create back button
        backButton= new JButton();
        backButton.setBounds(50,325,100,50);
        backButton.setText("Back");
        backButton.setFocusPainted(false);
        backButton.setHorizontalTextPosition(JButton.CENTER);
        backButton.setFont(buttonFont);
        backButton.setForeground(fontColor);
        backButton.setBackground(buttonColor);

        //create book consultation button
        bookConsultationButton =new JButton();
        bookConsultationButton.setBounds(525,325,250,50);
        bookConsultationButton.setText("Book Consultation");
        bookConsultationButton.setFocusPainted(false);
        bookConsultationButton.setHorizontalTextPosition(JButton.CENTER);
        bookConsultationButton.setFont(buttonFont);
        bookConsultationButton.setForeground(fontColor);
        bookConsultationButton.setBackground(buttonColor);

        JLabel background =commonComponent.backgroundComponent ();  //set the background image

        patientDetailsBodyPanel=new JPanel();
        patientDetailsBodyPanel.setPreferredSize(new Dimension(850,450));
        patientDetailsBodyPanel.setLayout(null);

        //add all the component to the panel
        patientDetailsBodyPanel.add(bodyTitleLabel);
        patientDetailsBodyPanel.add(patientFirstNameLabel);
        patientDetailsBodyPanel.add(patientFirstNameText);
        patientDetailsBodyPanel.add(patientLastNameLabel);
        patientDetailsBodyPanel.add(patientLastNameText);
        patientDetailsBodyPanel.add(patientIDLabel);
        patientDetailsBodyPanel.add(patientIDText);
        patientDetailsBodyPanel.add(patientNoteLabel);
        patientDetailsBodyPanel.add(patientNote);
        patientDetailsBodyPanel.add(dateOfBirthLabel);
        patientDetailsBodyPanel.add(dateOfBirth);
        patientDetailsBodyPanel.add(patientPhoneNumberLabel);
        patientDetailsBodyPanel.add(patientPhoneNumberText);
        patientDetailsBodyPanel.add(patientImagesUploadLabel);
        patientDetailsBodyPanel.add(patientImagesUploadButton);
        patientDetailsBodyPanel.add(patientImagesUploadedLabel);
        patientDetailsBodyPanel.add(backButton);
        patientDetailsBodyPanel.add(bookConsultationButton);
        patientDetailsBodyPanel.add(background);
    }

    /**
     * openDoctorList method
     */
    private void goBackToCheckAvailability(){
        this.dispose(); //dispose the current frame
        ArrayList<Object> doctorDetails=new ArrayList<Object>();    //create the doctor list for the parameters for CheckAvailabilityFrame
        doctorDetails.add(selectedDetailsFromAvailabilityList.get(0));
        doctorDetails.add(selectedDetailsFromAvailabilityList.get(1));
        doctorDetails.add(selectedDetailsFromAvailabilityList.get(2));
        doctorDetails.add(selectedDetailsFromAvailabilityList.get(3));
        CheckAvailabilityFrame checkAvailabilityFrame=new CheckAvailabilityFrame(doctorDetails);   //create a new frame of checkAvailabilityFrame
        checkAvailabilityFrame.setVisible(true);    //set it visible
    }

    /**
     * openDoctorList method
     */
    private void uploadingFile() {
            patientImageFiles=new JFileChooser();
            FileNameExtensionFilter imageExtensions= new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes());   //make the file extension for all the image extensions
            patientImageFiles.addChoosableFileFilter(imageExtensions);  //add the filter
            patientImageFiles.setMultiSelectionEnabled(true);   //make multi selection true
            int value= patientImageFiles.showOpenDialog(null);  //show the dialog box
            if(value==JFileChooser.APPROVE_OPTION){ //if image uploaded
                imageFiles=patientImageFiles.getSelectedFiles();    //get the images
                patientImagesUploadedLabel.setText("Files Uploaded");   //make the label file uploaded
            }
    }

    /**
     * openDoctorList method
     */
    public void bookConsultationProcess(){
        if(!patientFirstNameText.getText().equals("")){ //check if the firstname is not empty
            if(!patientLastNameText.getText().equals("")){  //check if the lastname is not empty
                if(!patientIDText.getText().equals("")){    //check if the patient ID is not empty
                    if(dateOfBirth.getDate()!=null){    //check if the date is not null
                        if(!patientPhoneNumberText.getText().equals("")){   //check if the phone number is not empty
                            if(!patientNote.getText().equals("")) { //check if the note is not empty
                                //optional
                                if (patientImageFiles != null) {    //check if the images are not null
                                    ArrayList<String> savedFiles = new ArrayList<String>();
                                    if(imageFiles!=null) {
                                        for (File file : imageFiles) {  //run a for each loop for each and every file
                                            try {
                                                String fileName = file.getName();   //get the file name
                                                BufferedImage image = ImageIO.read(file);   //read the file
                                                String extension = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.') + 1);   //get the file extension
                                                String saveSourcePath = new File("UploadImages").getAbsolutePath(); //get the file path to save
                                                File savingFile = new File(saveSourcePath + "/" + fileName);    //create the file in the saving folder
                                                ImageIO.write(image, extension, new FileOutputStream(savingFile));  //write the image to the folder
                                                encryptImage("UploadImages/" + fileName); //encrypt the image
                                                savedFiles.add(fileName);   //add the filename to the list

                                            } catch (IOException e) {   //catch exceptions
                                                JOptionPane.showMessageDialog(null, "Error In File uploading", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    }
                                    patientID=addPatientDetailsToList();    //call the addPatientDetailsToList method
                                    //add details to consultationDetailList
                                    consultationDetailList.add(patientNote.getText());
                                    consultationDetailList.add(savedFiles);
                                }
                                else {
                                    patientID=addPatientDetailsToList();    //call the addPatientDetailsToList method
                                    consultationDetailList.add(patientNote.getText());  //add notes to consultationDetailList
                                }
                                if(patientID!=-1){  //check if the patient id is not -1
                                    if(patientID!=-3) { //check if the patient id is not -3
                                        this.dispose(); //dispose the current frame
                                        PaymentDetailsFrame paymentDetailsFrame = new PaymentDetailsFrame(consultationDetailList);  //create a new frame of viewConsultationsFrame
                                        paymentDetailsFrame.setVisible(true);   //set it visible
                                    }
                                    else {
                                        consultationDetailList=selectedDetailsFromAvailabilityList; //make the list as  selectedDetailsFromAvailabilityList
                                    }
                                }
                            }
                            //all the empty field exceptions
                            else {
                                JOptionPane.showMessageDialog(null,"Notes field is empty","Error",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Phone number field is empty","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Date of Birth field is empty","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Patient ID field is empty","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Surname field is empty","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"First Name field is empty","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * openDoctorList method
     */
    public int addPatientDetailsToList(){
        String patientFirstName= patientFirstNameText.getText();    //get the firstname
        String patientLastName= patientLastNameText.getText();      //get the last name
        int patientID =-1;
        try {
            patientID = Integer.parseInt(patientIDText.getText());  //get the patient id
        } catch (NumberFormatException e) { //catch exceptions
            patientID=-3;
            JOptionPane.showMessageDialog(null,"Patient ID is not a String. Please enter Integers","Error",JOptionPane.ERROR_MESSAGE);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String patientDateOfBirth = dateFormat.format(dateOfBirth.getDate());   //get the date

        String patientPhoneNumber =patientPhoneNumberText.getText();    //get the phone number

        if(patientID!=-1){  //if all are correct add them to the list
            consultationDetailList.add(patientFirstName);
            consultationDetailList.add(patientLastName);
            consultationDetailList.add(patientDateOfBirth);
            consultationDetailList.add(patientPhoneNumber);
            consultationDetailList.add(patientID);
        }
        return patientID;   //return patient ID
    }

    /**
     * actionPerformed method
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Book Consultation")){
            bookConsultationProcess();  //call bookConsultationProcess method when Book Consultation event performed
        }
        else if(event.getActionCommand().equals("Upload")){
            uploadingFile();    //call uploadingFile method when Upload event performed
        }
        else if(event.getActionCommand().equals("Back")){
            goBackToCheckAvailability();    //call goBackToCheckAvailability method when back event performed
        }
    }
}

//reference
//https://www.youtube.com/watch?v=YZ_tQFTMYoQ
//https://stackoverflow.com/questions/20411919/filefilter-for-jfilechooser#:~:text=addChoosableFileFilter(filter)%20will%20add%20a,see%20all%20non%2Dhidden%20files.
//https://stackoverflow.com/questions/36657082/uploading-multiple-images-with-jfilechooser
//https://stackoverflow.com/questions/49964786/jdatechooser-does-not-appear
