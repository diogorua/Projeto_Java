package objects;

import java.util.List;
import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Trap extends Objects implements Blocked, Interactable, Support {
	
	private int damage;
	
	public Trap(Point2D initialPosition) {
		super(initialPosition);
		this.damage = 5;
	}
	
	
	public Point2D getPosition() {
		return super.getPosition();
	}
	
	public void setPosition(Point2D position) {
		super.setPosition(position);
	}
	
	@Override
	public String getName() {
		return "Trap";
	}

	@Override
	public int getLayer() {
		return 1;
	}
	
	public int getDamage() {
		return damage;
	}
	
	
	@Override
	public void interact() {
		List<Objects> list = GameEngine.getInstance().getRoom().getList(obj -> obj instanceof Manel);
		
		for(Objects obj : list) {
			if(Room.isNextTo(obj.getPosition(), this)) {      //temos de alterar isto, porque isto está bem para o 1º nível, mas no 2º nível ele já não leva dano da trap
				
				int health = ((Manel) obj).getHealth();
				health -= this.getDamage();
				((Manel) obj).setHealth(health);
					
				ImageGUI.getInstance().setStatusMessage("JumpMan was hit by a trap! Life: " + ((Manel) obj).getHealth() + "/100");
					
				if(((Manel) obj).getHealth() <= 0) {
					((Manel) obj).takeLife();
				}
				
			}
		}
	}
	
}