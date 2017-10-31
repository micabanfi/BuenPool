package root.State;

import root.AlreadyRated;
import root.ExistingRideException;
import root.InvalidCredentials;
import root.InvalidFields;
import root.User.Credential;
import root.Ride.Ride;
import root.Ride.RideAdmin;
import root.User.Person;
import root.User.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Date;


public class State {
   private ArrayList<User> users;
   private LinkedList<RideAdmin> currentRides;
   private ArrayList<RideAdmin> expiredRides;

   public State() {
	   users = new ArrayList<User>();
	   currentRides = new LinkedList<RideAdmin>();
	   expiredRides = new ArrayList<RideAdmin>();
   }

   public User login(Credential cred) throws InvalidCredentials{
        return authorize(cred);
    }

   public User authorize(Credential cred) throws InvalidCredentials{
        for (User user : users){
            if (user.equalCredentials(cred)) {
                return user;
            }

        }
        throw new InvalidCredentials();
    }

   public User register(User user) throws InvalidFields{
        if(!(users.contains(user))) {
        	users.add(user);
        	return user;
        	}
        throw new InvalidFields("User already Exists");
    }

   /* Metodo de Prueba
   private <T> boolean AddToList(T ent){
       boolean flag = false;
       if (ent instanceof  User){
           if (!(users.contains(ent))) {
               users.add((User)ent);
               flag = true;
           }
       }
       if (ent instanceof  Ride){
    	   if (!(currentRides.contains(ent))) {
               currentRides.add((Ride)ent);
               flag = true;
           }
       }
       return flag;
   }*/


   public void AddRideToList(Ride ride) throws ExistingRideException{
	   for(RideAdmin rideAdmin : currentRides) {
		   if(rideAdmin.getRide().equals(ride))
			   throw new ExistingRideException();
	  }
	  RideAdmin aux = new RideAdmin(ride);
	  currentRides.add(aux);
   }

   public void rateRide(Credential cred, Ride ride, boolean goodRating) throws InvalidCredentials, AlreadyRated{

    }
/*
   public RideAdmin saveNewRide(Ride ride) throws InvalidFields{
       if (AddToList(ride))
           return ride;
       throw new InvalidFields("Invalid Ride");
   }
*/
// tenemos que settear una/entender la timezone para/de todo el proyecto. No entendi nada
   public void refreshRides(){
       boolean aux = true;
       Date currentDate = new Date();
       for (int i = 0; aux ; i++) {
           RideAdmin ride = currentRides.get(i);
           if (ride.getRide().getDate().before(currentDate)){
               currentRides.remove(i);
               expiredRides.add(ride);
           }
           else{
               aux = false;
           }
       }
   }

/*   public Ride modifyRide(Ride ride) throws InvalidFields{
        for(int i = 0; i < users.size(); i++){
            Ride aux = currentRides.get(i);
            if (aux.getRide().equals(ride)){
                currentRides.remove(i);
                currentRides.add(ride);
                return ride;
            }
        }
        throw new InvalidFields("No Ride With The Same Characteristics.");
   }
*/
   public LinkedList<RideAdmin> getCurrentRides() { return currentRides; }

   //Creo que no lo necesitamos
   public ArrayList<RideAdmin> getExpiredRides() { return expiredRides; }
}
