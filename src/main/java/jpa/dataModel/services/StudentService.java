package jpa.dataModel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jpa.dataModel.data.StudentRepository;
import jpa.dataModel.model.Student;

import java.util.List;


@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student registerStudent(Student student){

        return studentRepository.save(student);
    }

    public Student updateStudent(Student student, Long id) throws Exception{
        System.out.println(student.getId());
        Student oldstudent = studentRepository.findById(id);
        if(oldstudent == null) {
            return null;
            //return studentRepository.save(student);
        } else {
            throw new Exception("error occurs when update student with id " + String.valueOf(id));
//            oldstudent.setEmail(student.getEmail());
//            oldstudent.setGender(student.getGender());
//            oldstudent.setName(student.getName());
//            oldstudent.setPhoneNumber(student.getPhoneNumber());
//            oldstudent.setUniversity(student.getUniversity());
//            return studentRepository.save(oldstudent);
        }

    }
//    public Student updateStudentEmail(String email, Long id) throws Exception{
//        Student oldstudent = studentRepository.findById(id);
//        if(oldstudent == null) {
//            return null;
//        } else {
//            throw new Exception("error occurs when finding student with id " + String.valueOf(id));
//            //oldstudent.setEmail(email);
//            //return studentRepository.save(oldstudent);
//        }
//    }
    public Student findStudent(Long id){
        Student student = studentRepository.findById(id);
        if(student == null) {
            return null;
        }
        return student;
    }
    public Student findStudentByEmail(String email) throws Exception{
        for(Student student : studentRepository.findAll()) {
            if(student.getEmail().equalsIgnoreCase(email)) {
                return student;
            }
        }
        return null;
    }
    public List<Student> getAllStudents(){
        return (List<Student>)studentRepository.findAll();
    }
    public boolean deleteStudent(Long id){
        Student student = studentRepository.findById(id);
        if(student == null) {
            return false;
        }
        studentRepository.delete(student);
        return true;
    }
}
