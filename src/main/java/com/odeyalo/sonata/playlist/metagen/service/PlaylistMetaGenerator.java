package com.odeyalo.sonata.playlist.metagen.service;

import com.odeyalo.sonata.suite.brokers.events.playlist.gen.PlaylistMetaGeneratedEvent;
import com.odeyalo.sonata.suite.brokers.events.playlist.gen.PlaylistTracksGeneratedEvent;
import org.jetbrains.annotations.NotNull;

public interface PlaylistMetaGenerator {

    boolean supports(@NotNull final PlaylistTracksGeneratedEvent event);

    @NotNull
    PlaylistMetaGeneratedEvent onTracksGenerated(@NotNull PlaylistTracksGeneratedEvent event);

}
