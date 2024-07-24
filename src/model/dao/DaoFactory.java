package model.dao;

import database.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    // método que retorna uma instância da SellerDaoJBDC
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
}
