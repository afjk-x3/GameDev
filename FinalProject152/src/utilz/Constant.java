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
		public static final int JUMP = 1;
		public static final int CROUCH = 2;
		public static final int GROUND = 3;
		public static final int HIT = 4;
		public static final int ATTACK = 5;
		public static final int PUGAY = 6;
		public static final int SUNGKIT = 7;
		public static final int LAUNCH = 8;
		public static final int RUNNINGATLEFT = 9;
		public static final int DOWNBLOCK = 10;
		public static final int IDLE = 11;
		public static final int IDLELEFT = 12;
		public static final int ATTACKLEFT = 13;
		
		public static int GetSpriteAmount(int player_action) {
			
			switch(player_action) {
			
			case RUNNING:
				return 12;
				
			case RUNNINGATLEFT:
				return 12;
				
			case IDLE:
				return 6;
				
			case IDLELEFT:
				return 6;
				
			case JUMP:
				return 6;
				
			case CROUCH:
				return 1;
				
//			case GROUND:
//			case HIT:
			case ATTACK:
				
				
				return 5;
				
			case ATTACKLEFT:
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