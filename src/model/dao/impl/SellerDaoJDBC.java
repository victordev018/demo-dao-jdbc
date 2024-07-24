package model.dao.impl;

import database.DB;
import database.DBException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    // dependence
    private Connection conn;

    public SellerDaoJDBC (Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
              "SELECT seller.*, department.Name as DepName " +
                      "FROM seller INNER JOIN department " +
                      "ON seller.departmentId = department.Id " +
                      "WHERE " +
                      "seller.Id = ?"
            );

            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {

                // department instance
                Department dep = instantiateDepartment(rs);

                // seller instance
                Seller obj = instantiateSeller(rs, dep);

                return obj;
            }
            return null;
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }

    // método para instanciar um Seller a partir de um resultSet e um Department
    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException{
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }

    // método para instanciar um departamento a partir de um ResultSet
    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return  dep;
    }
}
