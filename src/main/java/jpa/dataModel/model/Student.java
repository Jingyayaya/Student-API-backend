package jpa.dataModel.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(unique = false, nullable=false)
    private String phoneNumber;

//    @Column(nullable = true)
//    @Temporal(TemporalType.DATE)
//    private Date dob;

    @Column(unique = false, nullable = false)
    private String email;

    @Column(nullable = false)
    private String university;

    @Column
    private String gender;

//    @Column
//    @Temporal(TemporalType.DATE)
//    private Date enrollment;


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Date getDob() {
//        return dob;
//    }
//
//    public void setDob(Date dob) {
//        this.dob = dob;
//    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    public Date getEnrollment() {
//        return enrollment;
//    }
//
//    public void setEnrollment(Date enrollment) {
//        this.enrollment = enrollment;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(phoneNumber, student.phoneNumber) &&
                //Objects.equals(dob, student.dob) &&
                Objects.equals(email, student.email) &&
                Objects.equals(university, student.university);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, email, university);
    }
}
