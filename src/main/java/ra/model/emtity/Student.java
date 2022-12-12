package ra.model.emtity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Student {
    private int studentId;
    private String studentName;
    private String email;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private boolean studentStatus;

    public Student() {
    }

    public Student(int studentId, String studentName, String email, String address, Date birthDate, boolean studentStatus) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        this.studentStatus = studentStatus;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(boolean studentStatus) {
        this.studentStatus = studentStatus;
    }
}
