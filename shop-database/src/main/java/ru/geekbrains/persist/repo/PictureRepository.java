package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.Product;

import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long>, JpaSpecificationExecutor<Picture> {

    @Query("select p.contentType from Picture p " +
            "inner join p.pictureData where p.id = :id")
    Optional<String> getContentTypeForBlob(@Param("id") long id);
}
