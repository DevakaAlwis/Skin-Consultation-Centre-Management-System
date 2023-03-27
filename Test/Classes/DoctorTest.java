package Classes;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {
    Doctor d1=new Doctor("Devaka","Alwis", LocalDate.of(2012,3,22),"0771231321",12,"asd");
    Doctor d2=new Doctor("Ann","Ann", LocalDate.of(2013,4,2),"0774512312",11,"asd");

    @Test
    void getMedicalLicenceNumber() {

        assertEquals(12,d1.getMedicalLicenceNumber());
        assertEquals(11,d2.getMedicalLicenceNumber());
    }

    @Test
    void getSpecialisation() {
        assertEquals("asd",d1.getSpecialisation());
        assertEquals("asd",d2.getSpecialisation());
    }
}