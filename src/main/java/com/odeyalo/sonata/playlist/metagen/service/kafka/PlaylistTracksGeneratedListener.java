package com.odeyalo.sonata.playlist.metagen.service.kafka;

import com.odeyalo.sonata.playlist.metagen.service.PlaylistMetaGenerator;
import com.odeyalo.sonata.suite.brokers.events.playlist.gen.PlaylistMetaGeneratedEvent;
import com.odeyalo.sonata.suite.brokers.events.playlist.gen.PlaylistTracksGeneratedEvent;
import com.odeyalo.sonata.suite.brokers.events.playlist.gen.payload.GenerativePlaylistEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class PlaylistTracksGeneratedListener {
    private final List<PlaylistMetaGenerator> generators;
    private final KafkaTemplate<String, GenerativePlaylistEvent<?>> kafkaTemplate;

    public PlaylistTracksGeneratedListener(final List<PlaylistMetaGenerator> generators,
                                           final KafkaTemplate<String, GenerativePlaylistEvent<?>> kafkaTemplate) {
        this.generators = generators;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "playlists.gen.tracks")
    public void consume(@NotNull final PlaylistTracksGeneratedEvent event) {
        for (final PlaylistMetaGenerator generator : generators) {
            if ( generator.supports(event) ) {
                PlaylistMetaGeneratedEvent playlistMetaGeneratedEvent = generator.onTracksGenerated(event);

                kafkaTemplate.send("playlists.gen.meta", playlistMetaGeneratedEvent);

                return;
            }
        }
    }
}
