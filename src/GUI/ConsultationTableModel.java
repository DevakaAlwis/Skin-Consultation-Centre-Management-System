package GUI;

import Classes.Consultation;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ConsultationTableModel extends AbstractTableModel {
    private final String[] columnNames={"Patient ID","Patient First Name","Doctor ID","Doctor First Name","Date","Time"};   //Title Row names
    private final ArrayList<Consultation> consultationsList;    //Array list

    public ConsultationTableModel(ArrayList<Consultation> consultationList){
        consultationsList = consultationList;
    }


    @Override
    public int getRowCount() {
        return consultationsList.size();
    }   //get the row count

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }   //get the column count

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp=null;
        //get there relevant column details
        if(columnIndex==0){
            temp= consultationsList.get(rowIndex).getPatient().getPatientID();
        }
        else if(columnIndex==1){
            temp= consultationsList.get(rowIndex).getPatient().getFirstName();
        }
        else if(columnIndex==2){
            temp= consultationsList.get(rowIndex).getDoctor().getMedicalLicenceNumber();
        }
        else if(columnIndex==3){
            temp= consultationsList.get(rowIndex).getDoctor().getFirstName();
        }
        else if(columnIndex==4){
            temp= consultationsList.get(rowIndex).getConsultationDate();
        }
        else if(columnIndex==5){
            temp= consultationsList.get(rowIndex).getConsultationTime();
        }
        return temp;
    }

    public  String getColumnName(int col){
        return columnNames[col];
    }   //get the column name
}

////reference lecture notes