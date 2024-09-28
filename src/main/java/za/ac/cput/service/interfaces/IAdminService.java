package za.ac.cput.service.interfaces;

import za.ac.cput.domain.Admin;
import za.ac.cput.service.interfaces.IService;

import java.util.Set;

public interface IAdminService extends IService<Admin, Long> {
    Set<Admin> getAll();

    Admin findByUsernameAndPassword(String username, String password);
}
