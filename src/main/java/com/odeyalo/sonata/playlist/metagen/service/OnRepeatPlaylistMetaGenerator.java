package com.odeyalo.sonata.playlist.metagen.service;

import com.odeyalo.sonata.suite.brokers.events.playlist.gen.GeneratedPlaylistType;
import com.odeyalo.sonata.suite.brokers.events.playlist.gen.PlaylistMetaGeneratedEvent;
import com.odeyalo.sonata.suite.brokers.events.playlist.gen.PlaylistTracksGeneratedEvent;
import com.odeyalo.sonata.suite.brokers.events.playlist.gen.payload.PlaylistMetaGeneratedPayload;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public final class OnRepeatPlaylistMetaGenerator implements PlaylistMetaGenerator {

    private static final PlaylistMetaGeneratedPayload.Meta STATIC_META = new PlaylistMetaGeneratedPayload.Meta(
            "On Repeat",
            "Songs you love the most right now"
    );


    @Override
    public boolean supports(final @NotNull PlaylistTracksGeneratedEvent event) {
        return event.getType() == GeneratedPlaylistType.ON_REPEAT;
    }

    @Override
    @NotNull
    public PlaylistMetaGeneratedEvent onTracksGenerated(@NotNull final PlaylistTracksGeneratedEvent event) {
        return new PlaylistMetaGeneratedEvent(new PlaylistMetaGeneratedPayload(
                event.getBody(), STATIC_META
        ), GeneratedPlaylistType.ON_REPEAT);
    }
}
