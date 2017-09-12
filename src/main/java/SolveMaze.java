import edu.illinois.cs.cs125.lib.mazemaker.Maze;

/**
 * Solve a randomly-generated maze.
 *
 * @see <a href="https://github.com/cs125-illinois/mazemaker">Mazemaker on GitHub</a>
 * @see <a href="https://cs125-illinois.github.io/mazemaker/">Mazemaker Documentation</a>
 * @see <a href="https://cs125.cs.illinois.edu/lab/2/#maze">Lab 2 Writeup</a>
 */
@SuppressWarnings("checkstyle:emptyblock")
public class SolveMaze {

    /**
     * Implement your maze solving algorithm here.
     *
     * @param unused unused input arguments
     */

    public static void main(final String[] unused) {
        /*
         * Create a new 10 x 10 maze. Feel free to change these values.
         */
        Maze maze = new Maze(10, 10);

        /*
         * Pick (0, 0), the bottom left corner, as the starting point.
         * Put the end in the top right corner.
         */
        maze.startAtZero();
        maze.endAtTopRight();

        /*
         * You should be able to solve a 10 x 10 maze in (far fewer than) 1000 steps.
         * Feel free to adjust this number if you experiment with other mazes.
         */
        boolean moved = false;
        for (int step = 0; step < 1000; step++) {
            //checks to see if there is a wall to the left and if there isn't a wall in front
            if ((checkLeftWall(maze) == true) && (maze.canMove() == true) && (moved == false)) {
                maze.move();
                moved = true;
            }
            if (moved == false) { //checks if it can move left
                maze.turnLeft();
                if (maze.canMove() == true){
                    maze.move();
                    moved = true;
                } else {
                    maze.turnRight();
                }
            }
            if (moved == false) { //checks if it can move right
                maze.turnRight();
                if (maze.canMove() == true) {
                    maze.move();
                    moved = true;
                } else {
                    maze.turnLeft();
                }
            }
            if (moved == false) { //turns around if all else failed
                maze.turnLeft();
                maze.turnLeft();
                moved = true;
            }
            moved = false;
            if (maze.isFinished()) { //checks if the maze is done
                break;
            }
        }

        if (maze.isFinished()) {
            System.out.println("You solved the maze!");
        } else {
            System.out.println("Try again!");
        }
    }

    //checks to see if there is a wall to the left
    public static boolean checkLeftWall(Maze maze) {
        maze.turnLeft();
        if (maze.canMove() == false) {
            maze.turnRight();
            return true;
        } else {
            maze.turnRight();
            return false;
        }
    }

}
