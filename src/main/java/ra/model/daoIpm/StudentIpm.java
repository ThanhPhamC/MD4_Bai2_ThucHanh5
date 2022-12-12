package ra.model.daoIpm;

import org.springframework.stereotype.Repository;
import ra.model.dao.IStudentDao;
import ra.model.emtity.Student;
import ra.model.until.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class StudentIpm implements IStudentDao<Student,Integer> {
    @Override
    public List<Student> findAll() {
        Connection  conn= null;
        CallableStatement cast= null;
        List<Student> lists=null;
        try {
            conn= ConnectionDB.openConnection();
            cast=conn.prepareCall("{call findAllStudent()}");
            ResultSet rs= cast.executeQuery();
            lists=new ArrayList<>();
            while (rs.next()){
                Student student= new Student();
                student.setStudentId(rs.getInt("studentId"));
                student.setStudentName(rs.getString("studentName"));
                student .setEmail(rs.getString("email"));
                student.setAddress(rs.getString("address"));
                student .setBirthDate(rs.getDate("birthDate"));
                student.setStudentStatus(rs.getBoolean("studentStatus"));
                lists.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {

            try {
                ConnectionDB.closeConnection(conn,cast);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return lists;
    }

    @Override
    public boolean Save(Student student)  {
        Connection conn =null;
        CallableStatement cast = null;
        boolean result= true;
        try {
            conn= ConnectionDB.openConnection();
            cast= conn.prepareCall("{call save(?,?,?,?)}");
            cast.setString(1,student.getStudentName());
            cast.setString(2, student.getEmail());
            cast.setString(3, student.getAddress());
            cast.setDate(4,new Date(student.getBirthDate().getTime()));
            cast.executeUpdate();
        } catch (SQLException e) {
            result=false;
            throw new RuntimeException(e);
        }finally {
            try {
                ConnectionDB.closeConnection(conn,cast);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public boolean update(Student student) {
        Connection conn= null;
        CallableStatement cast= null;
        boolean result= true;
        try {
            conn= ConnectionDB.openConnection();
            cast= conn.prepareCall("{call updateStudent(?,?,?,?,?,?)}");
            cast.setInt(1,student.getStudentId());
            cast.setString(2,student.getStudentName());
            cast.setString(3, student.getEmail());
            cast.setString(4,student.getAddress());
            cast.setDate(5,new Date(student.getBirthDate().getTime())) ;
            cast.setBoolean(6, student.isStudentStatus());
            cast.executeUpdate();
        } catch (SQLException e) {
            result= false;
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) {
        Connection conn = null;
        CallableStatement cast= null;
        boolean result= true;
        try {
            conn= ConnectionDB.openConnection();
            cast= conn.prepareCall("{call deleteStudent(?)}");
            cast.setInt(1,id);
            cast.executeUpdate();
        } catch (SQLException e) {
            result=false;
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Student> searchByNameOrId(String st) {
        Connection  conn= null;
        CallableStatement cast= null;
        List<Student> lists=null;
        try {
            conn= ConnectionDB.openConnection();
            cast=conn.prepareCall("{call findByIdOrName(?)}");
            cast.setString(1,st);
            ResultSet rs= cast.executeQuery();
            lists=new ArrayList<>();
            while (rs.next()){
                Student student= new Student();
                student.setStudentId(rs.getInt("studentId"));
                student.setStudentName(rs.getString("studentName"));
                student .setEmail(rs.getString("email"));
                student.setAddress(rs.getString("address"));
                student .setBirthDate(rs.getDate("birthDate"));
                student.setStudentStatus(rs.getBoolean("studentStatus"));
                lists.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                ConnectionDB.closeConnection(conn,cast);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return lists;
    }

    @Override
    public Integer getIndex() {
        Connection conn=null;
        CallableStatement cast=null;
        int count=0;
        int index=0;
        try {
            conn=ConnectionDB.openConnection();
            cast= conn.prepareCall("{call countTotalStudent()}");
            ResultSet rs= cast.executeQuery();
            if (rs.next()){
                count=rs.getInt("count(*)");
                if (count%5==0){
                    index=count/5;
                }else {
                    index=1+(count/5);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return index;
    }

    @Override
    public List<Student> getListByIndex(Integer index) {
        Connection  conn= null;
        CallableStatement cast= null;
        List<Student> lists=null;
        try {
            conn= ConnectionDB.openConnection();
            cast=conn.prepareCall("{call findStudentByIndex(?)}");
            cast.setInt(1,(index-1)*5);
            ResultSet rs= cast.executeQuery();
            lists=new ArrayList<>();
            while (rs.next()){
                Student student= new Student();
                student.setStudentId(rs.getInt("studentId"));
                student.setStudentName(rs.getString("studentName"));
                student .setEmail(rs.getString("email"));
                student.setAddress(rs.getString("address"));
                student .setBirthDate(rs.getDate("birthDate"));
                student.setStudentStatus(rs.getBoolean("studentStatus"));
                lists.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                ConnectionDB.closeConnection(conn,cast);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return lists;
    }


}
