package utilz;

public class Constant {
	
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class PlayerConstants{
		public static final int RUNNING = 0;
		public static final int IDLE = 1;
		public static final int JUMP = 2;
		public static final int CROUCH = 3;
		public static final int GROUND = 5;
		public static final int HIT = 5;
		public static final int ATTACK = 7;
		public static final int PUGAY = 8;
		public static final int SUNGKIT = 9;
		public static final int LAUNCH = 10;
		public static final int RUNNINGATLEFT = 11;
		public static final int DOWNBLOCK = 12;
		
		public static int GetSpriteAmount(int player_action) {
			
			switch(player_action) {
			case RUNNING:
				return 12;
			case RUNNINGATLEFT:
				return 12;
			case IDLE:
				return 6;
			case JUMP:
				return 6;
			case CROUCH:
				return 1;
//			case GROUND:
//			case HIT:
			case ATTACK:
				return 5;
			case LAUNCH:
				return 6;
			case SUNGKIT:
				return 5;
			case DOWNBLOCK:
				return 1;
			case PUGAY:
				return 7;
			default:
				return 1;
			}
		}

	}
}