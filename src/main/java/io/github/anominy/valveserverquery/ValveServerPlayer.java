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

/**
 * A Valve server player.
 */
@SuppressWarnings("unused")
public final class ValveServerPlayer {

    /**
     * A player chunk index.
     */
    private final byte index;

    /**
     * A player name.
     */
    @NotNull
    private final String name;

    /**
     * A player score.
     */
    private final int score;

    /**
     * A player time.
     *
     * <p>In seconds.
     */
    private final float time;

    /**
     * Initialize a {@link ValveServerPlayer} instance.
     *
     * @param index     player chunk index
     * @param name      player name
     * @param score     player score
     * @param time      player time
     */
    @Contract(pure = true)
    public ValveServerPlayer(
            final byte index,

            @NotNull
            final String name,

            final int score,
            final float time
    ) {
        this.index = index;
        this.name = name;
        this.score = score;
        this.time = time;
    }

    /**
     * Get this player chunk index.
     *
     * @return  player chunk index
     */
    @Contract(pure = true)
    public byte getIndex() {
        return this.index;
    }

    /**
     * Get this player name.
     *
     * @return  player name
     */
    @NotNull
    @Contract(pure = true)
    public String getName() {
        return this.name;
    }

    /**
     * Get this player score.
     *
     * @return  player score
     */
    @Contract(pure = true)
    public int getScore() {
        return this.score;
    }

    /**
     * Get this player time.
     *
     * @return  player time
     */
    @Contract(pure = true)
    public float getTime() {
        return this.time;
    }
}
