package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.DTO.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {
    List<CustomerDTO> getAll();
    boolean saveCustomer(CustomerDTO customerDTO);
    boolean updateCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomer(String cusId);
    boolean deleteCustomer(String cusId);
    String getNextId();
}
