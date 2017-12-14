public class Car {
    
   private float x, y, x_init;
   private float speed, fuel;
   private float time;
   private boolean motorOn; 
   final float max_speed, max_fuel;
   final float consumtion_factor; 
 
   Car() {
      this(80, 270, 0.05f); 
   }
   
   Car(float max_fuel_t, float max_speed_t, float cf) {
      max_fuel = max_fuel_t; 
      max_speed = max_speed_t;
      speed = max_speed;
      fuel = max_fuel;
      x = 0;
      y = 50;
      motorOn = true;
      setTime(0.0f);
      // in RL this would be 0.01
      consumtion_factor = cf;
   }
   
   public float getX()  {
      return x; 
   }
    
   public float getY()  {
      return y;
   }
    
    
   public boolean getMotorOn() {
      return motorOn;
   }
    
   public float getFuelTankCapacity() {
      return max_fuel;
   }
    
   public float getFuel() {
      return fuel;
   }
    
   public float getMaxSpeed() {
      return max_speed;
   }
    
    public float getSpeed() {
        return speed;
   }
   
    public void turn_motor_on() {
        motorOn = true;       
    }
    
    public void turn_motor_off() {
        motorOn = false;     
    }
    
    public int setSpeed(float speed_t) {
        // 
        int ret = -1;
        
        do {
            
            if( !motorOn )
                break;
        
            speed = speed_t;
            ret = 1; 
            
        } while(false);
        
        return ret;
    }
    
    public void refuel(float amount) { 
        fuel += amount;
    } 
    
    
    public void tick(float timeleft) {
        
        // ??
        setTime(getTime() + timeleft);
        updateState(timeleft);
    }
    
    
    public void updateState(double time_left) {
        
        if( getMotorOn() ) {
            
            double consu = consumption( (double)speed );
            double path = speed * time_left * 1.0 / 3600.0 ;
            x += path * 1000; // path in meters
            double consumed_fuel = consu/100.0 * path;
        
            fuel -= consumed_fuel;
        }
        
        
        if( fuel <= 0 ) {
            fuel = 0.0f;
            turn_motor_off();
            speed = 0;
        }
            
    }
    // in km/h
    private double consumption(double speed_t) {
        return consumtion_factor * (Math.pow(speed_t, 1.4) + 150);
    }
    
    public String printState() {
        return "x Pos: " + x + "," + " y Pos: " + y + "," +
                " Engine State: " + (motorOn ? "On" : "Off") + "\nSpeed: " + speed + "," + 
                " Fuel: " + fuel + "\n";
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
    
    
}