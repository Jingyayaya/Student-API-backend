package jpa.dataModel.controller;

import jpa.dataModel.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jpa.dataModel.model.Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping(value = "/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(path="/students",consumes = "application/json", produces="application/json")
    @ResponseBody
    public ResponseEntity<Student> registerStudent(@RequestBody Student newstudent) {
        //student.setDob(convertDate(student.getDob()));

        Student registeredStudent = studentService.registerStudent(newstudent);

        return ResponseEntity.ok().body(registeredStudent);
    }

    @PutMapping(path="/students/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable Long id) throws Exception{
        //student.setDob(convertDate(student.getDob()));
        //student.setEnrollment(convertDate(student.getEnrollment()));
        student = studentService.updateStudent(student, id);
        if(student == null) {
            return ResponseEntity.badRequest().body("student with id " + String.valueOf(id) + " doesn't exist");
        }
        return ResponseEntity.ok().body(student);
    }

//    @PatchMapping(path="/students/{id}", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<?> updateStudent(@RequestParam("email") String email, @PathVariable Long id) throws Exception{
//        //student.setDob(convertDate(student.getDob()));
//        //student.setEnrollment(convertDate(student.getEnrollment()));
//        Student student = studentService.updateStudentEmail(email, id);
//        if(student == null) {
//            return ResponseEntity.badRequest().body("student with id " + String.valueOf(id) + " doesn't exist");
//        }
//        return ResponseEntity.ok().body(student);
//    }

    @GetMapping(path="/students", produces="application/json")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> studentList =  studentService.getAllStudents();
        return ResponseEntity.ok().body(studentList);
    }

    @GetMapping(path="/students/{id}", produces="application/json")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        Student student =  studentService.findStudent(id);
        if(student == null) {
            return ResponseEntity.badRequest().body("student with id " + String.valueOf(id) + " not found");
        }
        return ResponseEntity.ok().body(student);
    }
    @GetMapping(path="/students/emails", produces="application/json")
    public ResponseEntity<?> getStudentByEmail(@RequestParam("email") String email) throws Exception{
        Student student =  studentService.findStudentByEmail(email);
        if(student == null) {
            return ResponseEntity.badRequest().body("student with email " + email + " not found");
        }
        return ResponseEntity.ok().body(student);
    }

    @DeleteMapping(path="/students/{id}", produces="application/json")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(studentService.deleteStudent(id)) {
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.badRequest().body("student with id " + String.valueOf(id) + " doesn't exist");
    }

    public Date convertDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(date); // Now use today date.
        c.add(Calendar.DATE, 1); // Adding 5 days
       return c.getTime();
    }

}
