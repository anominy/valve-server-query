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

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/**
 * A Valve server query utility.
 */
@SuppressWarnings({"unused", "DefaultAnnotationParam", "DuplicatedCode"})
public final class ValveServerQuery {

    /**
     * An A2S_INFO request.
     */
    private static final byte @NotNull [] A2S_INFO = new byte[] {-1, -1, -1, -1, 0x54, 0x53, 0x6F, 0x75, 0x72, 0x63, 0x65, 0x20, 0x45, 0x6E, 0x67, 0x69, 0x6E, 0x65, 0x20, 0x51, 0x75, 0x65, 0x72, 0x79, 0x00, -1, -1, -1, -1};

    /**
     * An A2S_PLAYERS request.
     */
    private static final byte @NotNull [] A2S_PLAYERS = new byte[] {-1, -1, -1, -1, 0x55, -1, -1, -1, -1};

    /**
     * Request server players.
     *
     * @param ip    server ip:port
     * @return      array of server players
     */
    @NotNull
    public static ValveServerPlayer @Nullable [] requestServerPlayers(
            @NotNull
            final String ip
    ) {
        final String[] split = ip.split(":", 2);
        return requestServerPlayers(split[0], split[1]);
    }

    /**
     * Request server players.
     *
     * @param ip    server ip
     * @param port  server port
     * @return      array of server players
     */
    @NotNull
    public static ValveServerPlayer @Nullable [] requestServerPlayers(
            @NotNull
            final String ip,

            @NotNull
            final String port
    ) {
        try {
            return requestServerPlayers(ip, Integer.parseInt(port));
        } catch (final NumberFormatException ignored) {
        }

        return null;
    }

    /**
     * Request server players.
     *
     * @param ip    server ip
     * @param port  server port
     * @return      array of server players
     */
    @NotNull
    public static ValveServerPlayer @Nullable [] requestServerPlayers(
            @NotNull
            final String ip,

            final int port
    ) {
        return requestServerPlayers(new InetSocketAddress(ip, port));
    }

    /**
     * Request server players.
     *
     * @param address   server address
     * @return          array of server players
     */
    @NotNull
    public static ValveServerPlayer @Nullable [] requestServerPlayers(
            @NotNull
            final SocketAddress address
    ) {
        final byte[] responseBytes = request(address, A2S_PLAYERS);
        if (responseBytes == null) {
            return null;
        }

        int index = 4;

        final byte header = responseBytes[index++];
        final byte playerCount = responseBytes[index++];

        final ValveServerPlayer[] players = new ValveServerPlayer[playerCount];
        for (int i = 0; i < players.length; ++i) {
            final byte playerIndex = responseBytes[index++];

            int j = 0;
            final byte[] playerNameBytes = new byte[256];
            while (j < playerNameBytes.length && responseBytes[index] != 0) {
                playerNameBytes[j++] = responseBytes[index++];
            }
            final String playerName = new String(playerNameBytes, 0, j, StandardCharsets.UTF_8);

            ++index;

            final int playerScore = ByteBuffer.allocate(Integer.BYTES)
                    .order(ByteOrder.LITTLE_ENDIAN)
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .getInt(0);

            final float playerTime = ByteBuffer.allocate(Float.BYTES)
                    .order(ByteOrder.LITTLE_ENDIAN)
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .getFloat(0);

            players[i] = new ValveServerPlayer(
                    playerIndex,
                    playerName,
                    playerScore,
                    playerTime
            );
        }

        if (players.length == 0) {
            return null;
        }

        return players;
    }

    /**
     * Request server information.
     *
     * @param ip    server ip:port
     * @return      server information
     */
    @Nullable
    public static ValveServerInfo requestServerInfo(
            @NotNull
            final String ip
    ) {
        final String[] split = ip.split(":", 2);
        return requestServerInfo(split[0], split[1]);
    }

    /**
     * Request server information.
     *
     * @param ip    server ip
     * @param port  server port
     * @return      server information
     */
    @Nullable
    public static ValveServerInfo requestServerInfo(
            @NotNull
            final String ip,

            @NotNull
            final String port
    ) {
        try {
            return requestServerInfo(ip, Integer.parseInt(port));
        } catch (final NumberFormatException ignored) {
        }

        return null;
    }

    /**
     * Request server information.
     *
     * @param ip    server ip
     * @param port  server port
     * @return      server information
     */
    @Nullable
    public static ValveServerInfo requestServerInfo(
            @NotNull
            final String ip,

            final int port
    ) {
        return requestServerInfo(new InetSocketAddress(ip, port));
    }

    /**
     * Request server information.
     *
     * @param address   server address
     * @return          server information
     */
    @Nullable
    public static ValveServerInfo requestServerInfo(
            @NotNull
            final SocketAddress address
    ) {
        final byte[] responseBytes = request(address, A2S_INFO);
        if (responseBytes == null) {
            return null;
        }

        int index = 4;

        final byte header = responseBytes[index++];
        final byte protocol = responseBytes[index++];

        int j = 0;
        final byte[] serverNameBytes = new byte[256];
        while (j < serverNameBytes.length && responseBytes[index] != 0) {
            serverNameBytes[j++] = responseBytes[index++];
        }
        final String serverName = new String(serverNameBytes, 0, j, StandardCharsets.UTF_8);

        ++index;

        j = 0;
        final byte[] mapNameBytes = new byte[256];
        while (j < mapNameBytes.length && responseBytes[index] != 0) {
            mapNameBytes[j++] = responseBytes[index++];
        }
        final String mapName = new String(mapNameBytes, 0, j, StandardCharsets.UTF_8);

        ++index;

        j = 0;
        final byte[] folderNameBytes = new byte[256];
        while (j < folderNameBytes.length && responseBytes[index] != 0) {
            folderNameBytes[j++] = responseBytes[index++];
        }
        final String folderName = new String(folderNameBytes, 0, j, StandardCharsets.UTF_8);

        ++index;

        j = 0;
        final byte[] gameNameBytes = new byte[256];
        while (j < gameNameBytes.length && responseBytes[index] != 0) {
            gameNameBytes[j++] = responseBytes[index++];
        }
        final String gameName = new String(gameNameBytes, 0, j, StandardCharsets.UTF_8);

        ++index;

        final short appId = ByteBuffer.allocate(Short.BYTES)
                .order(ByteOrder.LITTLE_ENDIAN)
                .put(responseBytes[index++])
                .put(responseBytes[index++])
                .getShort(0);

        final byte playerCount = responseBytes[index++];
        final byte maxPlayerCount = responseBytes[index++];
        final byte botCount = responseBytes[index++];
        final byte serverType = responseBytes[index++];
        final byte serverEnvironment = responseBytes[index++];
        final boolean isServerPasswordProtected = responseBytes[index++] == 1;
        final boolean isServerVacSecured = responseBytes[index++] == 1;

        j = 0;
        final byte[] gameVersionBytes = new byte[256];
        while (j < gameVersionBytes.length && responseBytes[index] != 0) {
            gameVersionBytes[j++] = responseBytes[index++];
        }
        final String gameVersion = new String(gameVersionBytes, 0, j, StandardCharsets.UTF_8);

        ++index;

        final byte edf = responseBytes[index++];

        Short serverPort = null;
        if ((edf & 0x80) != 0) {
            serverPort = ByteBuffer.allocate(Short.BYTES)
                    .order(ByteOrder.LITTLE_ENDIAN)
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .getShort(0);
        }

        Long serverSteamId = null;
        if ((edf & 0x10) != 0) {
            serverSteamId = ByteBuffer.allocate(Long.BYTES)
                    .order(ByteOrder.LITTLE_ENDIAN)
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .getLong(0);
        }

        Short sourceTvPort = null;
        String sourceTvName = null;
        if ((edf & 0x40) != 0) {
            sourceTvPort = ByteBuffer.allocate(Short.BYTES)
                    .order(ByteOrder.LITTLE_ENDIAN)
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .getShort(0);

            j = 0;
            final byte[] sourceTvNameBytes = new byte[256];
            while (j < sourceTvNameBytes.length && responseBytes[index] != 0) {
                sourceTvNameBytes[j++] = responseBytes[index++];
            }
            sourceTvName = new String(sourceTvNameBytes, 0, j, StandardCharsets.UTF_8);

            ++index;
        }

        String serverTags = null;
        if ((edf & 0x20) != 0) {
            j = 0;
            final byte[] serverTagsBytes = new byte[256];
            while (j < serverTagsBytes.length && responseBytes[index] != 0) {
                serverTagsBytes[j++] = responseBytes[index++];
            }
            serverTags = new String(serverTagsBytes, 0, j, StandardCharsets.UTF_8);

            ++index;
        }

        Long serverGameId = null;
        if ((edf & 0x01) != 0) {
            serverGameId = ByteBuffer.allocate(Long.BYTES)
                    .order(ByteOrder.LITTLE_ENDIAN)
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .put(responseBytes[index++])
                    .put(responseBytes[index/*++*/])
                    .getLong(0);
        }

        return new ValveServerInfo(
                header,
                protocol,
                serverName,
                mapName,
                folderName,
                gameName,
                appId,
                playerCount,
                maxPlayerCount,
                botCount,
                serverType,
                serverEnvironment,
                isServerPasswordProtected,
                isServerVacSecured,
                edf,
                serverPort,
                serverSteamId,
                sourceTvPort,
                sourceTvName,
                serverTags,
                serverGameId
        );
    }

    /**
     * General request w/ challenge number.
     *
     * @param address   server address
     * @param bytes     request bytes
     * @return          response bytes, may be null
     */
    private static byte @Nullable [] request(
            @NotNull
            final SocketAddress address,

            final byte @NotNull [] bytes
    ) {
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(address);

            final DatagramPacket requestPacket = new DatagramPacket(bytes, bytes.length);
            socket.send(requestPacket);

            final byte[] challengeBytes = new byte[9];
            final DatagramPacket challengePacket = new DatagramPacket(challengeBytes, challengeBytes.length);
            socket.receive(challengePacket);

            final byte[] requestBytes = bytes.clone();
            for (int i = 4; i >= 1; --i) {
                requestBytes[requestBytes.length - i] = challengeBytes[challengeBytes.length - i];
            }

            final DatagramPacket requestPacket1 = new DatagramPacket(requestBytes, requestBytes.length);
            socket.send(requestPacket1);

            final byte[] responseBytes = new byte[4096];
            final DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length);
            socket.receive(responsePacket);

            return responseBytes;
        } catch (final IOException ignored) {
        }

        return null;
    }

    @Contract(value = "-> fail", pure = false)
    private ValveServerQuery() {
        throw new UnsupportedOperationException();
    }
}
