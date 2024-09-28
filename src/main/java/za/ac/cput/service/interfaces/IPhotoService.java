package za.ac.cput.service.interfaces;

import za.ac.cput.domain.Photo;

import java.util.Set;

public interface IPhotoService extends IService<Photo, Long>{
    Set<Photo> getAll();
}
