package ts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.User;
import technical_service.UserRDG;

public class UserTS {
	/* 0 - userId
	 * 1 - userName
	 * 2 - password
	 * 3 - currentDeck
	 * 4 - gameStatus
	 * 5 - gameId
	 * 6 - handId	 * 
	 * */
	private final static String[]FIELD_NAME={"userId","userName","password","currentDeck","gameStatus","gameId","handId"};
	private int userId = 0;
	private String userName;
	private String password;
	private int currentDeck;
	
	public int getUserId() {
		return userId;
	}
	public  String getUserName() {
		return userName;
	}
	public  String getPassword() {
		return password;
	}
	public  int getCurrentDeck() {
		return currentDeck;
	}	
	public void setUserName(String userName) {
		this.userName = userName;
	}	
	public void setCurrentDeck(int currentDeck) {
		this.currentDeck = currentDeck;
	}
	
	public UserTS(String userName,String password){
		this.userId=getUniqueId();
		this.password=password;
		this.userName=userName;
	}
	public static void assignDeck(int uId, int deckId){
		if(UserRDG.update(uId, FIELD_NAME[3], deckId)>0){
			System.out.println("Current updated ");

		}else{
			System.out.println("Something went wrong. ");
		}
		
				
	}
	 public static int verify(String user,String pass)
	 {
		 UserRDG u = UserRDG.find(user, pass);
		 if(u!=null){
			 return u.getUserId();
		 }else{
			 return -1;
		 }
	 }
	 public static boolean exist(String challengee)
	 {	
		int ichall=Integer.parseInt(challengee);
		List<UserRDG> list =  UserRDG.findAll();
		for(UserRDG val : list){
			if(val.getUserId()==ichall){

				return true;
			}
		}		 
		 
		return false;
	 }
	 public static boolean contains(String userName)
	 {	
		List<UserRDG> list =  UserRDG.findAll();
		for(UserRDG val : list){
			if(val.getUserName().equals(userName)){

				return true;
			}
		}		 
		 
		return false;
	 }
	 public synchronized static UserTS register(String u, String pass){
		 UserTS umod = new UserTS(u, pass);	
		 UserRDG.insert(umod);
		 return umod;
	 }
	 public synchronized static Map<Integer, String> getMap(){
		List<UserRDG> u=UserRDG.findAll();
		Map<Integer, String> m = new HashMap<Integer, String>();

		for(UserRDG val:u){
			m.put(val.getUserId(),val.getUserName() );
			}
		return m;
		 
	 }
	 private synchronized static int getUniqueId(){
		 int uid = UserRDG.getMaxId()+1;
		 return uid ;
	 }
	public static int hasDeck(int chalanger) {
		return UserRDG.find(chalanger).getCurrentDeck();		
	}
	public static void assignToGame(int player, int gameId) {
		UserRDG.update(player, FIELD_NAME[5], gameId);
	}
	public static void assignHand(int player, int handId) {
		UserRDG.update(player, FIELD_NAME[6], handId);
	}
	public static boolean checkGame(int uId, String gId) 
	{
		int temp = Integer.parseInt(gId);
		if(temp == UserRDG.find(uId).getGameId())
			return true;
		else
			return false;

		
	}
	public static void statusPlay(int user) {
		UserRDG.update(user, FIELD_NAME[4], "Playing");
	}
	public static boolean retire(int uid, String gameId) {
		UserRDG u = UserRDG.find(uid);
		if(u.getGameStatus().equals("Retire")){
			return false;

		}
		else{
			UserRDG.update(uid, FIELD_NAME[4], "Retire");
			return true;

		}
		
		
	}
		
}
