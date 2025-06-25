package com.itsprotsenko;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.itsprotsenko.TriangleCalculator.decimalPlaces;

public class Calculate {
    public Calculate() {

    }

    public double roundDouble(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

//    AB, BC, CA, A, B, C
    public void calculateTriangle(double[] sides, double[] angles) {
        for(int i = 0; i < 3; i++) {
            if(angles[i] == 0) {
                if(angles[(i+1)%3] != 0) {
                    if(angles[(i+2)%3] != 0) {
                        angles[i] = 180 - angles[(i + 2) % 3] - angles[(i + 1) % 3];
                    } else if(sides[(i+2)%3] != 0) {
                        if(sides[(i+1)%3] != 0) {
                            angles[i] = crossMultAngle(angles[(i+1)%3], sides[(i+2)%3], sides[(i+1)%3]);
                        } else {
                            angles[(i+2)%3] = crossMultAngle(angles[(i+1)%3], sides[(i+2)%3], sides[i]);
                            angles[i] = 180 - angles[(i + 2) % 3] - angles[(i + 1) % 3];
                        }
                    } else {
                        sides[(i+2)%3] = sideAngleSide(angles[(i+1)%3], sides[i], sides[(i+1)%3]);
                        angles[i] = crossMultAngle(angles[(i+1)%3], sides[(i+2)%3], sides[(i+1)%3]);
                    }
                } else if(angles[(i+2)%3] != 0) {
                    if(sides[i] != 0) {
                        if(sides[(i+1)%3] != 0) {
                            angles[i] = crossMultAngle(angles[(i+2)%3], sides[i], sides[(i+1)%3]);
                        } else {
                            angles[(i+1)%3] = crossMultAngle(angles[(i+2)%3], sides[i], sides[(i+2)%3]);
                            angles[i] = 180 - angles[(i + 2) % 3] - angles[(i + 1) % 3];
                        }
                    } else {
                        sides[i] = sideAngleSide(angles[(i+2)%3], sides[(i+1)%3], sides[(i+2)%3]);
                        angles[i] = crossMultAngle(angles[(i+2)%3], sides[i], sides[(i+1)%3]);
                    }
                } else {
                    angles[i] = allSides(sides[i], sides[(i+1)%3], sides[(i+2)%3]);
                }
            }
        }

        for(int i = 0; i < 3; i++) {
            if(sides[i] == 0) {
                if(sides[(i+1)%3] != 0) {
                    sides[i] = crossMultSide(sides[(i+1)%3], angles[i], angles[(i+2)%3]);
                } else {
                    sides[i] = crossMultSide(sides[(i+2)%3], angles[(i+1)%3], angles[(i+2)%3]);
                }
            }
        }

        for(int i = 0; i < 3; i++) {
            sides[i] = roundDouble(sides[i], decimalPlaces);
            angles[i] = roundDouble(angles[i], decimalPlaces);
        }
    }

    private double sideAngleSide(double angle, double side1, double side2) {
        return Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2) - (2 * side1 * side2 * Math.cos(angle * Math.PI / 180)));
    }

    private double crossMultAngle(double angle, double side1, double side2) {
        return Math.asin(Math.sin(angle * Math.PI / 180) * side2 / side1) * 180 / Math.PI;
    }

    private double allSides(double side1, double side2, double side3) {
        return Math.acos((Math.pow(side2, 2) - Math.pow(side1, 2) - Math.pow(side3, 2)) / (-2 * side1 * side3)) * 180 / Math.PI;
    }

    private double crossMultSide(double side, double angle1, double angle2) {
        return Math.sin(angle2 * Math.PI / 180) * side / Math.sin(angle1 * Math.PI / 180);
    }
}
