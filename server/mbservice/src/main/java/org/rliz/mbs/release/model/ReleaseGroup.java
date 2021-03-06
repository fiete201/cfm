package org.rliz.mbs.release.model;

import org.rliz.mbs.artist.model.ArtistCredit;
import org.rliz.mbs.common.model.FirstClassEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents a group of {@link Release}s.
 */
@Entity
@Table(name = "release_group", indexes = {
        @Index(name = "release_group_pkey", columnList = "id"),
        @Index(name = "release_group_idx_gid", columnList = "gid"),
        @Index(name = "release_group_idx_artist_credit", columnList = "artist_credit"),
        @Index(name = "release_group_idx_name", columnList = "name")
})
public class ReleaseGroup extends FirstClassEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_credit")
    private ArtistCredit artistCredit;

    @Column(name = "comment")
    private String comment;

    @Column(name = "last_updated")
    private Date lastUpdated;

//    type          | integer
//    edits_pending | integer

    public String getName() {
        return name;
    }

    public ArtistCredit getArtistCredit() {
        return artistCredit;
    }

    public String getComment() {
        return comment;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public String getDisplayName() {
        return getName();
    }
}
