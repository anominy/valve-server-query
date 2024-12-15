/*
 * Copyright 2024 anominy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.anominy.valveserverquery;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A Valve server information.
 */
@SuppressWarnings("unused")
public final class ValveServerInfo {

    /**
     * A header.
     */
    private final byte header;


    /**
     * A protocol.
     */
    private final byte protocol;

    /**
     * A server name.
     */
    @NotNull
    private final String name;

    /**
     * A map name.
     */
    @NotNull
    private final String mapName;

    /**
     * A folder name.
     */
    @NotNull
    private final String folderName;

    /**
     * A game name.
     */
    @NotNull
    private final String gameName;

    /**
     * An application ID.
     */
    private final short appId;

    /**
     * A player count.
     */
    private final byte playerCount;

    /**
     * A max. player count.
     */
    private final byte maxPlayerCount;

    /**
     * A bot count.
     */
    private final byte botCount;

    /**
     * A server type.
     */
    private final byte type;

    /**
     * A server environment.
     */
    private final byte environment;

    /**
     * A server is password protected switch.
     */
    private final boolean isPasswordProtected;

    /**
     * A server is VAC secured switch.
     */
    private final boolean isVacSecured;

    /**
     * An extra data flag.
     */
    private final byte edf;

    /**
     * A server port.
     */
    @Nullable
    private final Short port;

    /**
     * A server Steam ID.
     */
    @Nullable
    private final Long steamId;

    /**
     * A Source TV port.
     */
    @Nullable
    private final Short sourceTvPort;

    /**
     * A Source TV name.
     */
    @Nullable
    private final String sourceTvName;

    /**
     * A server tags.
     */
    @Nullable
    private final String tags;

    /**
     * A server game ID.
     */
    @Nullable
    private final Long gameId;

    /**
     * Initialize a {@link ValveServerInfo} instance.
     *
     * @param header                header
     * @param protocol              protocol
     * @param name                  server name
     * @param mapName               map name
     * @param folderName            folder name
     * @param gameName              game name
     * @param appId                 application ID
     * @param playerCount           player count
     * @param maxPlayerCount        max. player count
     * @param botCount              bot count
     * @param type                  server type
     * @param environment           server environment
     * @param isPasswordProtected   is password protected switch
     * @param isVacSecured          is VAC secured switch
     * @param edf                   extra data flag
     * @param port                  server port
     * @param steamId               server Steam ID
     * @param sourceTvPort          Source TV port
     * @param sourceTvName          Source TV name
     * @param tags                  server tags
     * @param gameId                server game ID
     */
    @Contract(pure = true)
    public ValveServerInfo(
            final byte header,
            final byte protocol,

            @NotNull
            final String name,

            @NotNull
            final String mapName,

            @NotNull
            final String folderName,

            @NotNull
            final String gameName,

            final short appId,
            final byte playerCount,
            final byte maxPlayerCount,
            final byte botCount,
            final byte type,
            final byte environment,
            final boolean isPasswordProtected,
            final boolean isVacSecured,
            final byte edf,

            @Nullable
            final Short port,

            @Nullable
            final Long steamId,

            @Nullable
            final Short sourceTvPort,

            @Nullable
            final String sourceTvName,

            @Nullable
            final String tags,

            @Nullable
            final Long gameId
    ) {
        this.header = header;
        this.protocol = protocol;
        this.name = name;
        this.mapName = mapName;
        this.folderName = folderName;
        this.gameName = gameName;
        this.appId = appId;
        this.playerCount = playerCount;
        this.maxPlayerCount = maxPlayerCount;
        this.botCount = botCount;
        this.type = type;
        this.environment = environment;
        this.isPasswordProtected = isPasswordProtected;
        this.isVacSecured = isVacSecured;
        this.edf = edf;
        this.port = port;
        this.steamId = steamId;
        this.sourceTvPort = sourceTvPort;
        this.sourceTvName = sourceTvName;
        this.tags = tags;
        this.gameId = gameId;
    }

    /**
     * Get this header.
     *
     * @return  header
     */
    @Contract(pure = true)
    public byte getHeader() {
        return this.header;
    }

    /**
     * Get this protocol.
     *
     * @return  protocol
     */
    @Contract(pure = true)
    public byte getProtocol() {
        return this.protocol;
    }

    /**
     * Get this server name.
     *
     * @return  server name
     */
    @NotNull
    @Contract(pure = true)
    public String getName() {
        return this.name;
    }

    /**
     * Get this map name.
     *
     * @return  map name
     */
    @NotNull
    @Contract(pure = true)
    public String getMapName() {
        return this.mapName;
    }

    /**
     * Get this folder name.
     *
     * @return  folder name
     */
    @NotNull
    @Contract(pure = true)
    public String getFolderName() {
        return this.folderName;
    }

    /**
     * Get game name.
     *
     * @return  game name
     */
    @NotNull
    @Contract(pure = true)
    public String getGameName() {
        return this.gameName;
    }

    /**
     * Get this application ID.
     *
     * @return  application ID
     */
    @Contract(pure = true)
    public short getAppId() {
        return this.appId;
    }

    /**
     * Get this player count.
     *
     * @return  player count
     */
    @Contract(pure = true)
    public byte getPlayerCount() {
        return this.playerCount;
    }

    /**
     * Get this max. player count.
     *
     * @return  max. player count
     */
    @Contract(pure = true)
    public byte getMaxPlayerCount() {
        return this.maxPlayerCount;
    }

    /**
     * Get this bot count.
     *
     * @return  bot count
     */
    @Contract(pure = true)
    public byte getBotCount() {
        return this.botCount;
    }

    /**
     * Get this server type.
     *
     * @return  server type
     */
    @Contract(pure = true)
    public byte getType() {
        return this.type;
    }

    /**
     * Get this server environment.
     *
     * @return  server environment
     */
    @Contract(pure = true)
    public byte getEnvironment() {
        return this.environment;
    }

    /**
     * Check if server is password protected.
     *
     * @return  true if password protected,
     *          otherwise false
     */
    @Contract(pure = true)
    public boolean isPasswordProtected() {
        return this.isPasswordProtected;
    }

    /**
     * Check if server is VAC secured.
     *
     * @return  true if VAC secured,
     *          otherwise false
     */
    @Contract(pure = true)
    public boolean isVacSecured() {
        return this.isVacSecured;
    }

    /**
     * Get this extra data flag.
     *
     * @return  extra data flag
     */
    @Contract(pure = true)
    public byte getEdf() {
        return this.edf;
    }

    /**
     * Get this server port.
     *
     * @return  server port, may be null
     */
    @Nullable
    @Contract(pure = true)
    public Short getPort() {
        return this.port;
    }

    /**
     * Get this server Steam ID.
     *
     * @return  server Steam ID, may be null
     */
    @Nullable
    @Contract(pure = true)
    public Long getSteamId() {
        return this.steamId;
    }

    /**
     * Get this Source TV port.
     *
     * @return  Source TV port, may be null
     */
    @Nullable
    @Contract(pure = true)
    public Short getSourceTvPort() {
        return this.sourceTvPort;
    }

    /**
     * Get this Source TV name.
     *
     * @return  Source TV name, may be null
     */
    @Nullable
    @Contract(pure = true)
    public String getSourceTvName() {
        return this.sourceTvName;
    }

    /**
     * Get this server tags.
     *
     * @return  server tags, may be null
     */
    @Nullable
    @Contract(pure = true)
    public String getTags() {
        return this.tags;
    }

    /**
     * Get this server game ID.
     *
     * @return  server game ID, may be null
     */
    @Nullable
    @Contract(pure = true)
    public Long getGameId() {
        return this.gameId;
    }
}
