// You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.

// You have two robots that can collect cherries for you:

// Robot #1 is located at the top-left corner (0, 0), and
// Robot #2 is located at the top-right corner (0, cols - 1).
// Return the maximum number of cherries collection using both robots by following the rules below:

// From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
// When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
// When both robots stay in the same cell, only one takes the cherries.
// Both robots cannot move outside of the grid at any moment.
// Both robots should reach the bottom row in grid.
 

// Example 1:


// Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
// Output: 24
// Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
// Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
// Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
// Total of cherries: 12 + 12 = 24.
// Example 2:


// Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
// Output: 28
// Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
// Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
// Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
// Total of cherries: 17 + 11 = 28.
 

// Constraints:

// rows == grid.length
// cols == grid[i].length
// 2 <= rows, cols <= 70
// 0 <= grid[i][j] <= 100

class Solution {
    public int cherryPickup(int[][] grid) {
        
        int rows = grid.length;
        int cols = grid[0].length;
        int[][][] dp = new int[rows][cols][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < cols; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        dp[0][0][cols - 1] = grid[0][0] + grid[0][cols - 1];
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < cols; k++) {
                    if (j == k) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j][k + 1]);
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j + 1][k], dp[i - 1][j][k + 1], dp[i - 1][j + 1][k + 1]);
                    }
                    if (j == 0 && k == cols - 1) {
                        dp[i][j][k] += grid[i][j] + grid[i][k];
                    } else if (j == 0) {
                        dp[i][j][k] += grid[i][j] + grid[i][k];
                    } else if (k == cols - 1) {
                        dp[i][j][k] += grid[i][j] + grid[i][k];
                    } else {
                        dp[i][j][k] += grid[i][j] + grid[i][k];
                    }
                }
            }
        }
        return dp[rows - 1][0][cols - 1];
        
    }
}