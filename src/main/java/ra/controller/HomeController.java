package ra.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.emtity.Student;
import ra.model.serviceIpm.StudentServiceIpm;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.List;


@Controller
@RequestMapping("HomeController")
public class HomeController {
    @Autowired
    private StudentServiceIpm studentServiceIpm;

    @GetMapping("getAll")
    public String getAll(Model model) {
        List<Student> list = studentServiceIpm.getListByIndex(1);
        int index=studentServiceIpm.getIndex();
        model.addAttribute("index",index);
        model.addAttribute("listStudent", list);
        return "students";
    }
    @GetMapping("getListByIndex")
    public String getListByIndex(Model model, Integer currentIndex){
        List<Student>studentList= studentServiceIpm.getListByIndex(currentIndex);
        model.addAttribute("currentIndex",currentIndex);
        model.addAttribute("listStudent",studentList);
        int index=studentServiceIpm.getIndex();
        model.addAttribute("index",index);
        return "students";
    }

    @PostMapping("creatStudent")
    public String creatStudent(Model model, Student student) {
        boolean result = studentServiceIpm.Save(student);
        if (result) {
            return "redirect:getAll";
        } else {
            model.addAttribute("error", "Có lỗi trong quá trình đăng kí!");
            List<Student> list = studentServiceIpm.findAll();
            model.addAttribute("listStudent", list);
            return "students";
        }
    }

    @PostMapping("updateStudent")
    public String updateStudent(Model model, Student student) {
        boolean result = studentServiceIpm.update(student);
        if (result) {
            return "redirect:getAll";
        } else {
            model.addAttribute("error", "Có lỗi trong quá trình update!");
            List<Student> list = studentServiceIpm.findAll();
            model.addAttribute("listStudent", list);
            return "students";
        }
    }

    @PostMapping("deleteStudent")
    public String deleteStudent(Model model, Integer studentId) {
        boolean result = studentServiceIpm.delete(studentId);
        if (result) {
            return "redirect:getAll";
        } else {
            model.addAttribute("error", "Có lỗi trong quá trình delete!");
            List<Student> list = studentServiceIpm.findAll();
            model.addAttribute("listStudent", list);
            return "students";
        }
    }
    @GetMapping("SearchByName")
    public void SearchByName(Model model, String studentName, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        List<Student> studentList = studentServiceIpm.searchByNameOrId(studentName);
        if (studentList.size() == 0) {
            getAll(model);
        }
        for (Student st : studentList) {
            showData(response, st);
        }
    }
    public void showData(HttpServletResponse response, Student student) {
        Writer out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
            String str= sdf.format(student.getBirthDate());
            out.write(" <tr style=\"color: black\">\n" +
                    "            <td>" + student.getStudentId() + "</td>\n" +
                    "            <td>" + student.getStudentName() + "</td>\n" +
                    "            <td>" +str + " </td>\n" +
                    "            <td>" + student.getEmail() + "</td>\n" +
                    "            <td>" + student.getAddress() + "</td>\n" +
                    "            <td>" + (student.isStudentStatus() ? "Hoạt Động" : "Nghỉ học") + "</td>\n" +
                    "            <td>\n" +
                    "                <button type=\"button\" id=\"update\" class=\"btn btn-outline-primary\"\n" +
                    "                        data-bs-toggle=\"modal\" data-bs-target=\"#updateStudent\"\n" +
                    "                ><i class=\"bi bi-pencil-square\"></i>\n" +
                    "                </button>\n" +
                    "                <input type=\"hidden\" id=\"oldId\" value=\"" + student.getStudentId() + "\">\n" +
                    "                <input type=\"hidden\" id=\"oldName\" value=\"" + student.getStudentName() + "\">\n" +
                    "                <input type=\"hidden\" id=\"oldDate\" value=\"" + student.getBirthDate() + "\">\n" +
                    "                <input type=\"hidden\" id=\"oldEmail\" value=\"" + student.getEmail() + "\">\n" +
                    "                <input type=\"hidden\" id=\"oldAddress\" value=\"" + student.getAddress() + "\">\n" +
                    "                <input type=\"hidden\" id=\"oldStatus\" value=\"" + student.isStudentStatus() + "\">\n" +
                    "            </td>\n" +
                    "            <td>\n" +
                    "                <button type=\"button\" id=\"delete\" class=\"btn btn-outline-danger\"\n" +
                    "                        data-bs-toggle=\"modal\" data-bs-target=\"#deleteStudent\"\n" +
                    "                ><i class=\"bi bi-journal-x\"></i>\n" +
                    "                </button>\n" +
                    "                <input type=\"hidden\" id=\"deleteId\" value=\"" + student.getStudentId() + "\">\n" +
                    "                <input type=\"hidden\" id=\"deleteName\" value=\"" + student.getStudentName() + "\">\n" +
                    "            </td>\n" +
                    "        </tr>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
