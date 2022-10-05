package dao.custom;

import dao.SuperDAO;
import entity.Register;

import java.sql.SQLException;

public interface RegisterDAO extends SuperDAO<Register, String> {
   String getLastOrderId() throws Exception;

    boolean existsByCustomerId(String customerId) throws Exception;
}
