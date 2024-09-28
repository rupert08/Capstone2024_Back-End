package za.ac.cput.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Photo;
import za.ac.cput.repository.PhotoRepository;
import za.ac.cput.service.interfaces.IPhotoService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor  // Lombok will generate the constructor for final fields(Autowired)
public class PhotoService implements IPhotoService {
    private final PhotoRepository photoRepository;

    @Override
    public Photo create(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public Photo read(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    @Override
    public Photo update(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public void delete(Long id) {
        photoRepository.deleteById(id);
    }

    @Override
    public Set<Photo> getAll() {
        return photoRepository.findAll().stream().collect(Collectors.toSet());
    }
}
