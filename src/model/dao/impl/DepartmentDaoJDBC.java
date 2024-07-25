package model.dao.impl;

import database.DB;
import database.DBException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    // Connection instance
    private Connection conn = null;

    public DepartmentDaoJDBC (Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(
                    "insert into department "
                            +"(Name) values (?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, obj.getName());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    obj.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DBException("Unexpected error! No rows affected!");
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Department obj) {

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(
                    "update department "
                       +"set Name = ? "
                       +"where Id = ?"
            );

            ps.setString(1, obj.getName());
            ps.setInt(2, obj.getId());

            ps.executeUpdate();

        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(
                    "select * from department "
                       +"where Id = ?"
            );

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()){

                // department instance
                Department dep = instantiateDepartment(rs);
                return dep;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public List<Department> findAll() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(
                    "select * from department order by name"
            );

            rs = ps.executeQuery();

            List<Department> list = new ArrayList<>();

            while (rs.next()) {

                // added department in list
                list.add(instantiateDepartment(rs));
            }

            return  list;
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    // método para instanciar um departamento a partir de um ResultSet
    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return  dep;
    }
}
