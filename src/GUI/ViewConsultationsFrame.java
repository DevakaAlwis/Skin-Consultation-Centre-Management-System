package GUI;

import Classes.Consultation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import static GUI.MenuFrame.listOfConsultation;

public class ViewConsultationsFrame extends JFrame implements ActionListener {
    //Initiate Panel
    JPanel mainDoctorListPanel;
    JPanel tablePanel;
    JPanel backPanel;

    //Initiate Table
    JTable consultationListTable;
    ConsultationTableModel consultationTableModel;
    ArrayList<Consultation> list;

    //Initiate ScrollPane
    JScrollPane consultationTableScrollPane;

    //Initiate Button
    JButton backButton;

    //InitiateColor
    Color tableHeaderBackgroundColor=new Color(100, 100, 100);
    Color tableHeaderFontColor=new Color(255, 255, 255);
    Color tableCellColor=new Color(229, 224, 224);
    Color buttonColor= new Color(199, 199, 203);
    Color fontColor=new Color(47, 47, 47);

    //Initiate Font
    Font tableHeaderFont=new Font("Berlin Sans FB",Font.PLAIN,20);
    Font tableBodyFont=new Font("Calibri",Font.PLAIN,20);
    Font backButtonFont=new Font("Berlin Sans FB",Font.PLAIN,20);

    /**
     * openDoctorList method
     */
    public ViewConsultationsFrame(){

        JPanel titlePanel= commonComponent.titleComponent();    //call titleComponent method to create the title panel
        mainDoctorListPanel = new JPanel();

        //create the table
        this.list= convertConsultationList();
        consultationTableModel = new ConsultationTableModel(list);
        consultationListTable = new JTable(consultationTableModel);

        consultationListTable.setAutoCreateRowSorter(true); //make the all the columns sorted

        //set the table header size and fonts
        consultationListTable.getTableHeader().setPreferredSize(new Dimension(0,40));
        consultationListTable.getTableHeader().setFont(tableHeaderFont);
        consultationListTable.getTableHeader().setBackground(tableHeaderBackgroundColor);
        consultationListTable.getTableHeader().setForeground(tableHeaderFontColor);

        //set the row width and heights
        consultationListTable.setRowHeight(30);
        consultationListTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        consultationListTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        consultationListTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        consultationListTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        consultationListTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        consultationListTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        consultationListTable.setPreferredSize(new Dimension(700,334));

        //set the table fonts
        consultationListTable.setFont(tableBodyFont);
        consultationListTable.setBackground(tableCellColor);
        consultationListTable.setGridColor(Color.BLACK);

        //create the scroll pane
        consultationTableScrollPane =new JScrollPane(consultationListTable);
        consultationTableScrollPane.setPreferredSize(consultationListTable.getPreferredSize());
        consultationTableScrollPane.setViewportView(consultationListTable);

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
        tablePanel.add(consultationTableScrollPane);

        backPanel = new JPanel();
        backPanel.setLayout(null);
        backPanel.add(backButton);  //add the back button to back panel

        mainDoctorListPanel.setPreferredSize(new Dimension(850,450));
        mainDoctorListPanel.setLayout(new BoxLayout(mainDoctorListPanel, BoxLayout.Y_AXIS));    //make the mainDoctorListPanel boxlayout
        mainDoctorListPanel.add(tablePanel);    //add table panel
        mainDoctorListPanel.add(backPanel);     //add back panel

        backButton.addActionListener(this); //add actionListener to back button

        ListSelectionModel model= consultationListTable.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {    //methods for row value is clicked
                if(!model.isSelectionEmpty()){
                    //get the selected row details
                    int selectedRow= consultationListTable.getSelectedRow();
                    int patientID=(int) consultationListTable.getValueAt(selectedRow,0);
                    String patientFirstName=(String) consultationListTable.getValueAt(selectedRow,1);
                    int doctorID=(int) consultationListTable.getValueAt(selectedRow,2);
                    String doctorFirstName=(String) consultationListTable.getValueAt(selectedRow,3);
                    LocalDate consultationDate= (LocalDate) consultationListTable.getValueAt(selectedRow,4);
                    String consultationTime=(String) consultationListTable.getValueAt(selectedRow,5);

                    int value = JOptionPane.showOptionDialog(null, "Would you like to view the consultation Patient "+patientFirstName+" with Dr."+doctorFirstName,"View Consultation",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);  //ask user whether continue

                    if (value == JOptionPane.YES_OPTION) {
                        openViewConsultation(patientID,doctorID,consultationDate,consultationTime); //if yes call openViewConsultation method
                    }
                }
            }
        });

        //set the frame
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
     * openViewConsultation method
     */
    private void openViewConsultation(int patientID,int doctorID,LocalDate date,String consultationTime){
        this.dispose(); //dispose the current frame
        ConsultationViewFrame consultationViewFrame=new ConsultationViewFrame(patientID,doctorID,date,consultationTime);    //create a new frame of consultationViewFrame
        consultationViewFrame.setVisible(true); //make the frame visible
    }

    /**
     * convertConsultationList method
     * @return Array List of Consultation
     */
    public ArrayList<Consultation> convertConsultationList(){
        ArrayList<Consultation> list=new ArrayList<Consultation>();
        //creating an array list of consultations
        for(Consultation consultation: listOfConsultation){
            if(consultation!=null) {
                list.add(consultation);
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
