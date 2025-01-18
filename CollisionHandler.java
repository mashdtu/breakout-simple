public class CollisionHandler {
    public static boolean checkCollision(Object2D obj1, Object2D obj2) {
        // Check if two objects are colliding with eachother by comparing their positions and dimensions.
        return obj1.x - obj1.getWidth() / 2 < obj2.x + obj2.getWidth() / 2 &&
               obj1.x + obj1.getWidth() / 2 > obj2.x - obj2.getWidth() / 2 &&
               obj1.y - obj1.getHeight() / 2 < obj2.y + obj2.getHeight() / 2 &&
               obj1.y + obj1.getHeight() / 2 > obj2.y - obj2.getHeight() / 2;
    }

    public static void handleCollision (Ball ball, Object2D obj) {
        // Check if the ball is coming in from the top by comparing the center position of the ball to the edge of the object.
        boolean collisionFromTop = (ball.y >= obj.y + obj.getHeight() / 2);
        
        // Check if the ball is coming in from the bottom by comparing the center position of the ball to the edge of the object.
        boolean collisionFromBottom = (ball.y <= obj.y - obj.getHeight() / 2);
        
        // Check if the ball is coming in from the left by comparing the center position of the ball to the edge of the object.
        boolean collisionFromLeft = (ball.x <= obj.x - obj.getWidth() / 2);
        
        // Check if the ball is coming in from the right by comparing the center position of the ball to the edge of the object.
        boolean collisionFromRight = (ball.x >= obj.x + obj.getWidth() / 2);


        // If the ball is colliding with a block:
        if (obj instanceof Block) {
            // If the collision is from the top or bottom, bounce the ball in the Y direction.
            if (collisionFromTop || collisionFromBottom) {
                ball.bounceY();
            }

            // Likewise, if the collision is from the left or right, bounce the ball in the X direction.
            if (collisionFromLeft || collisionFromRight) {
                ball.bounceX();
            }

            // In some cases, if the ball hits the block at a perfect corner, none of the booleans will trigger.
            if (!collisionFromTop && !collisionFromBottom && !collisionFromLeft && !collisionFromRight) {
                ball.bounceY();
                ball.bounceX();
            }

            // Destroy the block the ball collided with.
            ((Block) obj).destroy();
        }


        // If ball is colliding with the platform:
        if (obj instanceof Platform) {
            // If the ball doesnt collide from the top, ignore the collision such that the game ends.
            if (collisionFromTop) {
                // Define the platform instance as p.
                Platform p = (Platform) obj;
                
                // If the platform is moving the ball trajectory will be changed.
                if (p.getVelocity() != 0) {
                    // Add 0.2 x the platform velocity to the ball to redirect the trajectory.
                    ball.vx += p.getVelocity() * 0.2;
                }
                
                // Bounce the ball in the y direction regardless.
                ball.bounceY();
            }
        }
    }
}


