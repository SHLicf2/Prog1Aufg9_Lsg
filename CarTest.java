public class CarTest  {  
    
    /* simple acceleration for a Car car, acceleration value in m/s
     * aim_speed in km/h and update_time in milliseconds
     */
    public static void car_accelerate(Car car, float acceleration, 
            float aim_speed, long update_time) {
        while ( car.getSpeed() >= 0 && Math.abs(car.getSpeed() - aim_speed)
                > 0.05 && car.getSpeed() < car.getMaxSpeed() ) {
            car.setSpeed( car.getSpeed() + acceleration * 3.6f /
                    (1000.0f / update_time) );
            Traffic.waitAWhile(update_time);
        }
    }
    public static void main(String[] args) { 
        Car car = new Car();  
        Traffic traffic = new Traffic(car);
        traffic.setSize(1000, 200);
        traffic.setVisible(true);  
     
        // start animation
        traffic.start();
        
        // put your code for car state manipulation here
        System.out.println("Max Speed for 1 second:");
        System.out.println(car.printState());
        
        Traffic.waitAWhile(1000);
        
        System.out.println("Half of max Speed for 1 second:");
        car.setSpeed ( car.getSpeed() / 2.0f ); 
        System.out.println(car.printState());
        
        Traffic.waitAWhile(1000);
        
        /* simple acceleration with 3m/s to max_speed*/
        final float accel = 3.0f, aim1 = 200, aim2 = 0;
        // update every mx
        long update_time = 10;
        
    
        System.out.println("Slowing down from " + car.getSpeed() + 
                " to " + aim2 + " with " + accel + "m/s : "); 
        car_accelerate(car, -accel, aim2, update_time);
        
        car.setSpeed(0);
        
        System.out.println("Accelerating from " + car.getSpeed() +
                " to " + aim1 + " with " + accel + "m/s : "); 
        car_accelerate(car, accel, aim1, update_time);
        
        // wait until we have no fuel
        System.out.println("Waiting until we have no fuel: ");
        while (car.getFuel() != 0.0f )
            Traffic.waitAWhile(2000);
            
        // refuel and drive 
        car.refuel(60);
        car.turn_motor_on();
        car.setSpeed(120);
    } 
}