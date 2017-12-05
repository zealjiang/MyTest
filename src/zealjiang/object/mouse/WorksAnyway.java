package zealjiang.object.mouse;

import java.util.ArrayList;

public class WorksAnyway {
	public static void main(String[] args) {
	ArrayList mice = new ArrayList();
	for(int i = 0; i < 3; i++)
	mice.add(new Mouse(i));
	for(int i = 0; i < mice.size(); i++) {
	// No cast necessary, automatic
	// call to Object.toString():
	System.out.println(
	"Free mouse: " + mice.get(i));
	MouseTrap.caughtYa(mice.get(i));
	}
	}
}
