package za.ac.cput.service.interfaces;

import za.ac.cput.domain.Admin;

public interface IService <T, ID>{
    T create(T t);
    T read(ID id);
    T update(T t);
    void delete(ID id);
}
