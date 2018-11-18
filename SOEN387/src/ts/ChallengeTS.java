package ts;

import java.util.ArrayList;
import java.util.List;

import technical_service.ChallengeRDG;

public class ChallengeTS {
	private int chalId;	
	private int challenger;
	private int challengee;
	private int status;
	public ChallengeTS(int cId, int challenger, int challengee, int status) {
		super();
		this.chalId = cId;
		this.challenger = challenger;
		this.challengee = challengee;
		this.status = status;
	}
	
	public ChallengeTS(int challenger, int challengee, int status) {
		this.chalId = getUniqueId();
		this.challenger = challenger;
		this.challengee = challengee;
		this.status = status;
	}

	public int getChalId() {
		return chalId;
	}
	public int getChallenger() {
		return challenger;
	}
	public int getChallengee() {
		return challengee;
	}
	public int getStatus() {
		return status;
	}
	public void setChallenger(int challenger) {
		this.challenger = challenger;
	}
	public void setChallengee(int challengee) {
		this.challengee = challengee;
	}
	public void setStatus(int status) {
		this.status = status;
	} 
	private synchronized static int getUniqueId(){
		 int uid = ChallengeRDG.getMaxId()+1;		 
		 return uid ;
	}

	public static void challenge(int c1, String c2) {
		ChallengeTS c = new ChallengeTS(c1,Integer.parseInt(c2),0);

		ChallengeRDG.insert(c);
	}

	public static List<ChallengeTS> getList() {
		List<ChallengeTS> chaList = new ArrayList<ChallengeTS>();
		List<ChallengeRDG> chaListRDG = ChallengeRDG.findAll();

		for(ChallengeRDG val:chaListRDG){
			ChallengeTS temp=new ChallengeTS(val.getChalId(),val.getChallenger(),val.getChallengee(),val.getStatus());
			chaList.add(temp);
		}
		return chaList;
	}
	public static boolean yourOwnChallenge(String cId, int userId) {
		ChallengeRDG chal = ChallengeRDG.find(Integer.parseInt(cId));
		if(chal.getChallenger()==userId){
			return true;
		}
		else{
			return false;

		}
	}
	public static boolean yourChallenge(String cId, int userId) {
		ChallengeRDG chal = ChallengeRDG.find(Integer.parseInt(cId));
		if(chal.getChallengee()==userId){
			return true;
		}
		else{
			return false;

		}
	}

	public static void acceptChallenge(String cId) {
		ChallengeRDG.update(cId,3);
	}
	public static void declineChallenge(String cId) {
		ChallengeRDG.update(cId,1);
	}
	public static void withdrawChallenge(String cId) {
		ChallengeRDG.update(cId,2);
	}
}
