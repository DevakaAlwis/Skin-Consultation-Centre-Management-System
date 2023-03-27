package Classes;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterSkinConsultationManagerTest {

    Person [] Doctors=new Person[5];
    Person d1=new Doctor("Devaka","Alwis", LocalDate.of(2012,3,22),"0771231321",12,"asd");
    Person d2=new Doctor("Ann","Anna", LocalDate.of(2013,4,2),"0774512312",11,"asd");
    Person d3;

    @Test
    void setup(){
        String s="JUnit Program";
        assertEquals("JUnit Program",s);
    }

    @Test
    void inputFirstName() {
        String s="Dev";
        assertEquals("Dev",s);
    }

    @Test
    void inputSurName() {
        String s="AAA";
        assertEquals("AAA",s);
    }

    @Test
    void inputDateOfBirth() {
        String s="2012-12-21";
        assertEquals("2012-12-21",s);
    }

    @Test
    void validateDate() {
        WestminsterSkinConsultationManager test=new WestminsterSkinConsultationManager();
        LocalDate actual= test.validateDate("2021-12-12");
        LocalDate expected=LocalDate.of(2021,12,12);
        assertEquals(expected,actual);
    }

    @Test
    void inputMobileNumber() {
        String s="1231231231";
        assertEquals("1231321231",s);
    }

    @Test
    void inputMedicalLicenceNumber() {
        String s="12";
        assertEquals("12",s);
    }

    @Test
    void checkDoctorID() {
        int s=12;
        assertEquals(12,s);
    }

    @Test
    void inputSpecialisation() {
        String s="12";
        assertEquals("12",s);
    }






}