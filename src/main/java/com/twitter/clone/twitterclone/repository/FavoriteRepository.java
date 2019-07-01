package com.twitter.clone.twitterclone.repository;

import com.twitter.clone.twitterclone.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
