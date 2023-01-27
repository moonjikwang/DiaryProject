package Diary.view;

public class Diary {

	public static void main(String[] args) {
	      Loading splash = new Loading();
	      try {
	         splash.dispose();
				Login frame = new Login();
				frame.setVisible(true);
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	}
}
