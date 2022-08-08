package com.mymusicstore.musicstorecatalog.repository;

import com.mymusicstore.musicstorecatalog.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LabelRepository extends JpaRepository<Label, Long> {
}
