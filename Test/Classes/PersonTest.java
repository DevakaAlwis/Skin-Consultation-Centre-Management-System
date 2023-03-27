package Classes;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person d1=new Doctor("Devaka","Alwis", LocalDate.of(2012,3,22),"0771231321",12,"asd");
    Person d2=new Doctor("Ann","Anna", LocalDate.of(2013,4,2),"0774512312",11,"asd");
    Person d3;

    @Test
    void test() {
        assertNull(d3);
    }

    @Test
    void getFirstName() {
        assertEquals("Devaka",d1.getFirstName());
        assertEquals("Ann",d2.getFirstName());
        assertNotEquals(d1.getFirstName(),d2.getFirstName());
    }


    @Test
    void getSurName() {
        assertEquals("Alwis",d1.getSurName());
        assertEquals("Anna",d2.getSurName());
        assertNotEquals(d1.getSurName(),d2.getSurName());
    }

    @Test
    void getDateOfBirth() {
        assertEquals(LocalDate.of(2012,3,22),d1.getDateOfBirth());
        assertEquals(LocalDate.of(2013,4,2),d2.getDateOfBirth());
        assertNotEquals(d1.getDateOfBirth(),d2.getDateOfBirth());
    }

    @Test void getMobileNumber() {
        assertEquals("0771231321",d1.getMobileNumber());
        assertEquals("0774512312",d2.getMobileNumber());
        assertNotEquals(d1.getMobileNumber(),d2.getMobileNumber());
    }

}