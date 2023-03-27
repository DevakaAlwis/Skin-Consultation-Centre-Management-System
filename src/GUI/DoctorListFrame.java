package GUI;

import Classes.Doctor;
import Classes.Person;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Classes.WestminsterSkinConsultationManager.listOfDoctors;

public class DoctorListFrame extends JFrame implements ActionListener{

    //Initiate panels
    JPanel mainDoctorListPanel;
    JPanel tablePanel;
    JPanel backPanel;

    //Initiate table
    JTable doctorListTable;
    DoctorTableModel doctorTableModel;
    ArrayList<Doctor> list;
    ArrayList <Object>selectedDoctorDetails;

    //Initiate scroll pane
    JScrollPane doctorTableScrollPane;

    //Initiate buttons
    JButton backButton;

    //Initiate colors
    Color tableHeaderBackgroundColor=new Color(100, 100, 100);
    Color tableHeaderFontColor=new Color(255, 255, 255);
    Color tableCellColor=new Color(229, 224, 224);
    Color buttonColor= new Color(199, 199, 203);
    Color fontColor=new Color(47, 47, 47);

    //Initiate fonts
    Font tableHeaderFont=new Font("Berlin Sans FB",Font.PLAIN,20);
    Font tableBodyFont=new Font("Calibri",Font.PLAIN,20);
    Font backButtonFont=new Font("Berlin Sans FB",Font.PLAIN,20);

    public DoctorListFrame(){

        JPanel titlePanel= commonComponent.titleComponent();    //call titleComponent method to create the title panel

        mainDoctorListPanel = new JPanel();


        //create the table
        this.list=convertDoctorList();
        doctorTableModel = new DoctorTableModel(list);
        doctorListTable = new JTable(doctorTableModel);

        doctorListTable.setAutoCreateRowSorter(true);   //make the all the columns sorted

        //set the table header size and fonts
        doctorListTable.getTableHeader().setPreferredSize(new Dimension(0,40));
        doctorListTable.getTableHeader().setFont(tableHeaderFont);
        doctorListTable.getTableHeader().setBackground(tableHeaderBackgroundColor);
        doctorListTable.getTableHeader().setForeground(tableHeaderFontColor);

        //set the row width and heights
        doctorListTable.setRowHeight(30);
        doctorListTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        doctorListTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        doctorListTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        doctorListTable.getColumnModel().getColumn(3).setPreferredWidth(250);
        doctorListTable.setPreferredSize(new Dimension(700,334));

        //set the table fonts
        doctorListTable.setFont(tableBodyFont);
        doctorListTable.setBackground(tableCellColor);
        doctorListTable.setGridColor(Color.BLACK);

        //create the scroll pane
        doctorTableScrollPane =new JScrollPane(doctorListTable);
        doctorTableScrollPane.setPreferredSize(doctorListTable.getPreferredSize());
        doctorTableScrollPane.setViewportView(doctorListTable);

        //create te back button
        backButton= new JButton();
        backButton.setText("Back");
        backButton.setFocusPainted(false);
        backButton.setForeground(fontColor);
        backButton.setBackground(buttonColor);
        backButton.setBounds(65,0,100,40);
        backButton.setHorizontalTextPosition(JButton.CENTER);
        backButton.setVerticalTextPosition(JButton.BOTTOM);
        backButton.setFont(backButtonFont);

        tablePanel = new JPanel();
        tablePanel.setPreferredSize(new Dimension(850,300));
        tablePanel.add(doctorTableScrollPane);

        backPanel = new JPanel();
        backPanel.setLayout(null);
        backPanel.add(backButton);  //add the back button to back panel

        mainDoctorListPanel.setPreferredSize(new Dimension(850,450));
        mainDoctorListPanel.setLayout(new BoxLayout(mainDoctorListPanel, BoxLayout.Y_AXIS));    //make the mainDoctorListPanel boxlayout
        mainDoctorListPanel.add(tablePanel);    //add table panel
        mainDoctorListPanel.add(backPanel);     //add back panel

        backButton.addActionListener(this); //add actionListener to back button

        ListSelectionModel model= doctorListTable.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {    //methods for row value is clicked
                if(!model.isSelectionEmpty()){
                    int selectedRow= doctorListTable.getSelectedRow();

                    selectedDoctorDetails=new ArrayList<Object>();
                    int doctorID=(int) doctorListTable.getValueAt(selectedRow,0);
                    selectedDoctorDetails.add(String.valueOf(doctorID));
                    String doctorFirstName=(String) doctorListTable.getValueAt(selectedRow,1);
                    selectedDoctorDetails.add(doctorFirstName);
                    String doctorLastName=(String) doctorListTable.getValueAt(selectedRow,2);
                    selectedDoctorDetails.add(doctorLastName);
                    String doctorSpecialisation=(String) doctorListTable.getValueAt(selectedRow,3);
                    selectedDoctorDetails.add(doctorSpecialisation);

                    int value = JOptionPane.showOptionDialog(null, "Would you like to add a consultation with Dr."+doctorFirstName+" " +doctorLastName,"Add Consultation",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);   //ask user whether continue

                    if (value == JOptionPane.YES_OPTION) {
                        openCheckAvailability(selectedDoctorDetails);   //if yes call openViewConsultation method
                    }
                }
            }
        });

        this.setTitle("Westminster Skin Consultation Centre");  //set a title for the frame
        this.setSize(850, 600);  //set width and height
        this.setLocationRelativeTo(null);   //make the frame center
        this.setResizable(false);   //remove resizing the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //exit the application by clicking close icon
        ImageIcon icon=new ImageIcon("Images/logo.png");
        this.setIconImage(icon.getImage());
        this.setLayout(new BorderLayout());
        this.add(titlePanel,BorderLayout.NORTH);
        this.add(mainDoctorListPanel,BorderLayout.CENTER);
        this.setVisible(true);  //make the frame visible
    }

    /**
     * openCheckAvailability method
     */
    private void openCheckAvailability(ArrayList<Object> selectedDoctorDetails){
        this.dispose(); //dispose the current frame
        CheckAvailabilityFrame checkAvailabilityFrame=new CheckAvailabilityFrame(selectedDoctorDetails);    //create a new frame of checkAvailabilityFrame
        checkAvailabilityFrame.setVisible(true);    //make the frame visible
    }

    /**
     * convertDoctorList method
     * * @return Array List of doctors
     */
    public ArrayList<Doctor> convertDoctorList(){
        ArrayList<Doctor> list=new ArrayList<Doctor>();
        //creating an array list of doctors
        for(Person doctor: listOfDoctors){
            if(doctor!=null) {
                list.add((Doctor) doctor);
            }
        }
        return list;
    }

    /**
     * goBackTOMenu method
     */
    private void goBackTOMenu(){
        this.dispose(); //dispose the current frame
        MenuFrame menuFrame=new MenuFrame();    //create a new frame of meniFrame
        menuFrame.setVisible(true); //make the frame visible
    }

    /**
     * actionPerformed method
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Back")){
            goBackTOMenu(); //call goBackTOMenu method when back event performed
        }
    }
}

//reference
//https://stackoverflow.com/questions/18405660/how-to-set-component-size-inside-container-with-boxlayout
//lecture notes 7,8,9
//https://www.geeksforgeeks.org/java-awt-boxlayout-class/
//https://www.codejava.net/java-se/swing/setting-column-width-and-row-height-for-jtable
//https://coderanch.com/t/338971/java/height-JTable-header
