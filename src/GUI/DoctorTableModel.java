package GUI;

import Classes.Doctor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DoctorTableModel extends AbstractTableModel {
    private final String[] columnNames={"Doctor ID","First Name","Surname","Specialisation"};   //Title Row names
    private final ArrayList<Doctor> doctorsList;    //Array list

    public DoctorTableModel(ArrayList<Doctor> doctorList){
        doctorsList = doctorList;
    }

    @Override
    public int getRowCount() {
        return doctorsList.size();
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
            temp= doctorsList.get(rowIndex).getMedicalLicenceNumber();
        }
        else if(columnIndex==1){
            temp= doctorsList.get(rowIndex).getFirstName();
        }
        else if(columnIndex==2){
            temp= doctorsList.get(rowIndex).getSurName();
        }
        else if(columnIndex==3){
            temp= doctorsList.get(rowIndex).getSpecialisation();
        }
        return temp;
    }

    public  String getColumnName(int col){
        return columnNames[col];
    }   //get the column name
}

//reference lecture notes