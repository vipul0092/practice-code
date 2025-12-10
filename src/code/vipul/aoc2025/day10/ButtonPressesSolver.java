package code.vipul.aoc2025.day10;

import java.util.*;

public class ButtonPressesSolver {

    static final double INF = Double.MAX_VALUE;
    static final double EPS = 1e-9;

    // Best solution tracking
    static double bestVal;
    static double[] bestSol;

// Java equivalent of https://github.com/RussellDash332/advent-of-code/blob/main/aoc-2025%2FDay-10%2FPython%2Fmain.py
//    ┌─────────────────────────────────────────────────────────────┐
//    │  SIMPLEX ALGORITHM                                          │
//    │  Finds the optimal solution for LINEAR programs             │
//    │  (where variables can be fractional, like x = 3.7)          │
//    └─────────────────────────────────────────────────────────────┘
//                          │
//                          ▼
//    ┌─────────────────────────────────────────────────────────────┐
//    │  BRANCH AND BOUND                                           │
//    │  Wraps Simplex to find INTEGER solutions                    │
//    │  If Simplex gives x = 3.7, it tries x ≤ 3 and x ≥ 4         │
//    └─────────────────────────────────────────────────────────────┘
// The Simplex algorithm solves problems of the form:
// Minimize:    c₁x₁ + c₂x₂ + ... + cₙxₙ
// Subject to:  (linear constraints) all xᵢ ≥ 0

// For this problem:
// Minimize: total presses = x1 + x2 + x3 .... ( so all ci = 1)
// Subject to: each position equation must equal its target
// Constraint: all xi >= 0
    public static int solveProblem(int[][] groups, int[] target) {
        int[] solution = solve(groups, target);

        if (solution != null) {
            // System.out.println("========== SOLUTION FOUND ==========\n");
            int total = 0;
            for (int g = 0; g < groups.length; g++) {
                // System.out.printf("G%-2d %-25s: %3d times%n", g, formatGroup(groups[g]), solution[g]);
                total += solution[g];
            }
            // System.out.println("\nMinimum total presses: " + total);

            // Verify
//            System.out.println("\n========== VERIFICATION ==========");
//            int[] result = new int[target.length];
//            for (int g = 0; g < groups.length; g++) {
//                for (int pos : groups[g]) {
//                    result[pos] += solution[g];
//                }
//            }
//            System.out.println("Achieved: " + Arrays.toString(result));
//            System.out.println("Target:   " + Arrays.toString(target));
//            System.out.println("Match: " + Arrays.equals(result, target));
            return total;
        } else {
            System.out.println("No solution found!");
            throw new RuntimeException("No solution found!");
        }
    }

    /**
     * Main solver: builds constraint matrix and calls Branch & Bound
     */
    static int[] solve(int[][] groups, int[] target) {
        int nGroups = groups.length;
        int nPositions = target.length;

        // Build constraint matrix A
        // Each position gives us an equality constraint: sum of group presses = target
        // We convert equality to two inequalities: ≤ and ≥

        List<double[]> constraints = new ArrayList<>();

        for (int pos = 0; pos < nPositions; pos++) {
            // Build coefficient row: which groups affect this position
            double[] rowLe = new double[nGroups + 1];  // ≤ constraint
            double[] rowGe = new double[nGroups + 1];  // ≥ constraint (as ≤)

            for (int g = 0; g < nGroups; g++) {
                boolean affects = false;
                for (int p : groups[g]) {
                    if (p == pos) {
                        affects = true;
                        break;
                    }
                }
                if (affects) {
                    rowLe[g] = 1;
                    rowGe[g] = -1;
                }
            }

            // sum ≤ target[pos]
            rowLe[nGroups] = target[pos];
            constraints.add(rowLe);

            // sum ≥ target[pos]  →  -sum ≤ -target[pos]
            rowGe[nGroups] = -target[pos];
            constraints.add(rowGe);
        }

        // Convert to array
        double[][] A = constraints.toArray(new double[0][]);

        // Objective: minimize sum of all variables (all coefficients = 1)
        double[] C = new double[nGroups];
        Arrays.fill(C, 1.0);

        // Solve using Branch and Bound
        double[] solution = branchAndBound(A, C, nGroups);

        if (solution == null) {
            return null;
        }

        // Convert to int array
        int[] result = new int[nGroups];
        for (int i = 0; i < nGroups; i++) {
            result[i] = (int) Math.round(solution[i]);
        }
        return result;
    }

    /**
     * Branch and Bound: finds optimal INTEGER solution
     */
    static double[] branchAndBound(double[][] A, double[] C, int n) {
        bestVal = INF;
        bestSol = null;

        branch(A, C, n);

        return bestSol;
    }

    static void branch(double[][] A, double[] C, int n) {
        // Solve relaxed (fractional) problem
        SimplexResult result = simplex(A, C);

        if (result.value == -INF || result.x == null) {
            return;  // Infeasible
        }

        // Pruning: if this can't beat our best, stop
        if (result.value + EPS >= bestVal) {
            return;
        }

        // Find first fractional variable
        int fracIdx = -1;
        int fracFloor = 0;
        for (int i = 0; i < n; i++) {
            if (Math.abs(result.x[i] - Math.round(result.x[i])) > EPS) {
                fracIdx = i;
                fracFloor = (int) Math.floor(result.x[i]);
                break;
            }
        }

        if (fracIdx == -1) {
            // All integers! Update best solution
            if (result.value + EPS < bestVal) {
                bestVal = result.value;
                bestSol = new double[n];
                for (int i = 0; i < n; i++) {
                    bestSol[i] = Math.round(result.x[i]);
                }
            }
        } else {
            // Branch on fractional variable
            // Branch 1: x[fracIdx] ≤ fracFloor
            double[] newConstraint1 = new double[n + 1];
            newConstraint1[fracIdx] = 1;
            newConstraint1[n] = fracFloor;
            branch(addConstraint(A, newConstraint1), C, n);

            // Branch 2: x[fracIdx] ≥ fracFloor + 1  →  -x[fracIdx] ≤ -(fracFloor + 1)
            double[] newConstraint2 = new double[n + 1];
            newConstraint2[fracIdx] = -1;
            newConstraint2[n] = -(fracFloor + 1);
            branch(addConstraint(A, newConstraint2), C, n);
        }
    }

    static double[][] addConstraint(double[][] A, double[] constraint) {
        double[][] newA = new double[A.length + 1][];
        for (int i = 0; i < A.length; i++) {
            newA[i] = A[i].clone();
        }
        newA[A.length] = constraint.clone();
        return newA;
    }

    /**
     * Simplex algorithm: finds optimal FRACTIONAL solution
     * Minimizes C·x subject to Ax ≤ b (last column of A is b)
     */
    static SimplexResult simplex(double[][] A, double[] C) {
        int m = A.length;           // Number of constraints
        int n = A[0].length - 1;    // Number of variables

        // Build tableau D with dimensions (m+2) x (n+2)
        // Rows 0 to m-1: constraints
        // Row m: objective function
        // Row m+1: auxiliary objective (for Phase 1)
        // Columns 0 to n-1: original variables
        // Column n: auxiliary variable
        // Column n+1: RHS (b values)

        double[][] D = new double[m + 2][n + 2];

        // Fill constraint rows
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                D[i][j] = A[i][j];
            }
            D[i][n] = -1;           // Auxiliary variable coefficient
            D[i][n + 1] = A[i][n];  // RHS
        }

        // Fill objective row
        for (int j = 0; j < n; j++) {
            D[m][j] = C[j];
        }

        // Auxiliary objective row (for Phase 1)
        D[m + 1][n] = 1;

        // Basic and non-basic variable indices
        int[] N = new int[n + 1];  // Non-basic
        int[] B = new int[m];      // Basic

        for (int i = 0; i < n; i++) {
            N[i] = i;
        }
        N[n] = -1;  // Auxiliary variable marker

        for (int i = 0; i < m; i++) {
            B[i] = n + i;  // Slack variables
        }

        // Phase 1: Find initial feasible solution
        int r = 0;
        for (int i = 1; i < m; i++) {
            if (D[i][n + 1] < D[r][n + 1]) {
                r = i;
            }
        }

        if (D[r][n + 1] < -EPS) {
            pivot(D, B, N, m, n, r, n);
            if (!find(D, B, N, m, n, 1) || D[m + 1][n + 1] < -EPS) {
                return new SimplexResult(-INF, null);
            }
        }

        // Handle degenerate case
        for (int i = 0; i < m; i++) {
            if (B[i] == -1) {
                int s = 0;
                for (int j = 1; j < n; j++) {
                    if (Math.abs(D[i][j]) > Math.abs(D[i][s]) ||
                            (Math.abs(D[i][j]) == Math.abs(D[i][s]) && N[j] < N[s])) {
                        s = j;
                    }
                }
                pivot(D, B, N, m, n, i, s);
            }
        }

        // Phase 2: Optimize
        if (find(D, B, N, m, n, 0)) {
            double[] x = new double[n];
            for (int i = 0; i < m; i++) {
                if (B[i] >= 0 && B[i] < n) {
                    x[B[i]] = D[i][n + 1];
                }
            }

            double val = 0;
            for (int i = 0; i < n; i++) {
                val += C[i] * x[i];
            }

            return new SimplexResult(val, x);
        } else {
            return new SimplexResult(-INF, null);  // Unbounded
        }
    }

    /**
     * Pivot operation: swap basic and non-basic variables
     */
    static void pivot(double[][] D, int[] B, int[] N, int m, int n, int r, int s) {
        double k = 1.0 / D[r][s];

        for (int i = 0; i < m + 2; i++) {
            if (i == r) continue;
            for (int j = 0; j < n + 2; j++) {
                if (j != s) {
                    D[i][j] -= D[r][j] * D[i][s] * k;
                }
            }
        }

        for (int j = 0; j < n + 2; j++) {
            D[r][j] *= k;
        }

        for (int i = 0; i < m + 2; i++) {
            D[i][s] *= -k;
        }

        D[r][s] = k;

        // Swap indices
        int temp = B[r];
        B[r] = N[s];
        N[s] = temp;
    }

    /**
     * Find optimal solution by iterating pivots
     * @param phase 1 for Phase 1 (feasibility), 0 for Phase 2 (optimization)
     */
    static boolean find(double[][] D, int[] B, int[] N, int m, int n, int phase) {
        while (true) {
            // Find entering variable (most negative coefficient)
            int s = -1;
            double minVal = -EPS;
            int minN = Integer.MAX_VALUE;

            for (int j = 0; j <= n; j++) {
                if (phase == 0 && N[j] == -1) continue;  // Skip auxiliary in Phase 2

                double val = D[m + phase][j];
                if (val < minVal || (Math.abs(val - minVal) < EPS && N[j] < minN)) {
                    if (val < -EPS || (val < minVal)) {
                        minVal = val;
                        minN = N[j];
                        s = j;
                    }
                }
            }

            if (s == -1 || D[m + phase][s] > -EPS) {
                return true;  // Optimal found
            }

            // Find leaving variable (minimum ratio test)
            int r = -1;
            double minRatio = INF;
            int minB = Integer.MAX_VALUE;

            for (int i = 0; i < m; i++) {
                if (D[i][s] > EPS) {
                    double ratio = D[i][n + 1] / D[i][s];
                    if (ratio < minRatio || (Math.abs(ratio - minRatio) < EPS && B[i] < minB)) {
                        minRatio = ratio;
                        minB = B[i];
                        r = i;
                    }
                }
            }

            if (r == -1) {
                return false;  // Unbounded
            }

            pivot(D, B, N, m, n, r, s);
        }
    }

    static String formatGroup(int[] group) {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < group.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(group[i]);
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Simple class to hold simplex result
     */
    static class SimplexResult {
        double value;
        double[] x;

        SimplexResult(double value, double[] x) {
            this.value = value;
            this.x = x;
        }
    }
}