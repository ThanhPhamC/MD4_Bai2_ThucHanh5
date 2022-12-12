package ra.model.serviceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dao.IStudentDao;
import ra.model.emtity.Student;
import ra.model.service.IStudentService;

import java.util.List;
@Service
public class StudentServiceIpm implements IStudentService<Student,Integer> {
    @Autowired
    private IStudentDao<Student,Integer> studentDao;
    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }
    @Override
    public boolean Save(Student student) {
        return studentDao.Save(student);
    }
    @Override
    public boolean update(Student student) {
        return studentDao.update(student);
    }
    @Override
    public boolean delete(Integer id) {
        return studentDao.delete(id);
    }
    @Override
    public List<Student> searchByNameOrId(String st) {
        return studentDao.searchByNameOrId(st);
    }
    @Override
    public Integer getIndex() {
        return studentDao.getIndex();
    }
    @Override
    public List<Student> getListByIndex(Integer index) {
        return studentDao.getListByIndex(index);
    }
}
