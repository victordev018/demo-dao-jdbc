package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    // método que retorna uma instância da SellerDaoJBDC
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC();
    }
}
