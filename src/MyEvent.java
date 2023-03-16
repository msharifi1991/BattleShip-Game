import java.awt.Color;
import java.awt.Component;
import java.awt.event.ComponentEvent;

public class MyEvent extends ComponentEvent {
	int x, y;
	Color color;

	public MyEvent(Component source, int id, int x, int y, Color color) {

		super(source, id);
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColor() {
		return color;
	}
}
