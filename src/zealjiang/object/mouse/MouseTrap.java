package zealjiang.object.mouse;

class MouseTrap {
	static void caughtYa(Object m) {
	Mouse mouse = (Mouse)m; // Cast from Object
	System.out.println("Mouse: " +
	mouse.getNumber());
	}
}
