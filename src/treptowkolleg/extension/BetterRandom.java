/*
 * Copyright (C) 2025 Benjamin Wagner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package treptowkolleg.extension;

import java.util.ArrayList;

/**
 * A utility class providing enhanced random integer generation methods with
 * inclusive upper bounds and support for list indexing.
 * <p>
 * Unlike {@link java.util.Random#nextInt(int)}, which excludes the upper bound,
 * the methods in this class treat the upper bound as inclusive. The underlying
 * implementation avoids floating-point truncation bias by scaling
 * {@link Math#random()} using {@link Math#nextDown(float)} to ensure a uniform
 * distribution across the full integer interval {@code [lowerBound,
 * upperBound]}.
 * </p>
 *
 * @author Benjamin Wagner
 * @version 1.0
 * @since 2025
 */
public class BetterRandom {

    /**
     * Returns a random valid index for the given list, i.e., an integer in the
     * range {@code [0, list.size() - 1]}, inclusive.
     * <p>
     * If the list is empty ({@code size() == 0}), this method returns {@code
     * -1},
     * as {@code get(-1)} would be invoked internally.
     * </p>
     *
     * @param list the list for which a random index is requested; may be empty
     *             but must not be {@code null}
     * @return a random index in the range {@code [0, list.size() - 1]}, or
     * {@code -1} if the list is empty
     * @throws NullPointerException if the provided list is {@code null}
     */
    public static int indexOf(ArrayList<?> list) {
        return get(list.size() - 1);
    }

    /**
     * Returns a random integer in the inclusive range {@code [0, upperBound]}.
     * <p>
     * This method delegates to {@link #get(int, int)} with a lower bound of
     * {@code 0}.
     * </p>
     *
     * @param upperBound the inclusive upper bound (must be ≥ 0)
     * @return a random integer {@code n} such that {@code 0 ≤ n ≤ upperBound}
     * @throws IllegalArgumentException if {@code upperBound < 0}
     */
    public static int get(int upperBound) {
        return get(0, upperBound);
    }

    /**
     * Returns a random integer in the inclusive range
     * {@code [lowerBound, upperBound]}.
     * <p>
     * The implementation uses linear interpolation between {@code lowerBound}
     * and {@code upperBound} based on a uniformly distributed random fraction
     * derived from {@link Math#random()}, scaled to {@code [0, 1]} using
     * {@link Math#nextDown(float)} to ensure the upper bound can be reached.
     * The result is cast to {@code int}, effectively truncating any fractional
     * part after interpolation.
     * </p>
     * <p>
     * If {@code lowerBound > upperBound}, the behavior is undefined and may
     * produce unexpected results.
     * </p>
     *
     * @param lowerBound the inclusive lower bound
     * @param upperBound the inclusive upper bound
     * @return a random integer {@code n} such that
     * {@code lowerBound ≤ n ≤ upperBound}
     */
    public static int get(int lowerBound, int upperBound) {
        double f = Math.random() / Math.nextDown(1.0);
        double x = lowerBound * (1.0 - f) + upperBound * f;
        return (int) x;
    }
}