package edu.asu.bsse.dlee129.placedescription;// Copyright 2021 David Lee

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author David Lee    mailto:dlee129@asu.edu
 * @version January 2021
 */
public class GreatCircleDistanceCalculator {
    /**
     * Compute the great circle distance (in nautical miles) between two points. The following
     * formula assumes that sin, cos, and acos are computed in degrees, so we need to convert back
     * and forth between radians.
     *
     *     d = 60 * acos (sin(l1)*sin(l2) + cos(l1)*cos(l2)*cos(g1 - g2))
     *
     * @param l1 latitude of first point
     * @param g1 longitude of first point
     * @param l2 latitude of second point
     * @param g2 longitude of second point
     * @return the great circle distance (in nautical miles) between (l1, g1) and (l2, g2)
     */
    public static double distance(double l1, double g1, double l2, double g2) {
        double x1 = Math.toRadians(l1);
        double y1 = Math.toRadians(g1);
        double x2 = Math.toRadians(l2);
        double y2 = Math.toRadians(g2);
        // this is the great circle distance in radians
        double angle = Math.acos(Math.sin(x1) * Math.sin(x2)
                + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2));
        // convert back to degrees
        angle = Math.toDegrees(angle);
        // each degree on a great circle of Earth is 60 nautical miles
        return 60 * angle;
    }
}
