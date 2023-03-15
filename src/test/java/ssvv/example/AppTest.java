package ssvv.example;

import domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Service service;
    @Before
    public void setUp(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "C:\\Users\\16112001\\IdeaProjects\\LabSSVV\\src\\main\\java\\fisiere\\Studenti.xml";
        String filenameTema = "C:\\Users\\16112001\\IdeaProjects\\LabSSVV\\src\\main\\java\\fisiere\\Teme.xml";
        String filenameNota = "C:\\Users\\16112001\\IdeaProjects\\LabSSVV\\src\\main\\java\\fisiere\\Note.xml";


        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void addStudentValidGroupTest()
    {
        Student validStudent = new Student("1","Test name",2,"test@gmail.com");
        Student s = service.addStudent(validStudent);
        assertNotNull(s);
    }

    @Test
    public void addStudentInvalidGroupTest()
    {
        Student invalidStudent = new Student("1","Test name",-1000,"test@gmail.com");
        assertThrows(ValidationException.class, ()-> service.addStudent(invalidStudent));
    }
}
