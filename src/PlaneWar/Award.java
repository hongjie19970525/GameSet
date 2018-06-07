package PlaneWar;

public interface Award {
	int DOUBLE_FIRE = 0;//双倍火力
	int LIFE = 1;
	
	/**
	 * @return 0:奖励双倍火力；1：奖励一条生命
	 */
	int getType();
}
