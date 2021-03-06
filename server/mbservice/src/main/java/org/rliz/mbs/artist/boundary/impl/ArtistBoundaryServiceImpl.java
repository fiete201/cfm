package org.rliz.mbs.artist.boundary.impl;

import org.rliz.mbs.artist.boundary.ArtistBoundaryService;
import org.rliz.mbs.artist.model.Artist;
import org.rliz.mbs.artist.repository.ArtistRepository;
import org.rliz.mbs.common.exception.MbEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of {@link ArtistBoundaryService}.
 */
@Service
public class ArtistBoundaryServiceImpl implements ArtistBoundaryService {

    private ArtistRepository artistRepository;

    @Autowired
    public ArtistBoundaryServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist getSingleArtistByIdentifier(UUID identifier) {
        Artist foundArtist = this.artistRepository.findOneByIdentifier(identifier);
        if (foundArtist == null) {
            throw new MbEntityNotFoundException(MbEntityNotFoundException.EC_NO_SUCH_UUID,
                    "No artist found for UUID %s.", identifier);
        }
        return foundArtist;
    }

    @Override
    public Page<Artist> findArtistsByName(String name, Pageable pageable) {
        return artistRepository.findByName(name, pageable);
    }
}
