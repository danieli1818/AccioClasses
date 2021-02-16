package me.danieli1818.accio.classes.accio_classes.utils;

public class ClassProperties {

	private boolean hasStarted;
	private boolean isMagicOn;
	private boolean isChatOn;
	
	public ClassProperties() {
		this.hasStarted = false;
		this.isChatOn = false;
		this.isMagicOn = false;
	}
	
	public void setHasStarted(boolean canPlayersJoin) {
		this.hasStarted = canPlayersJoin;
	}
	
	public void setIsChatOn(boolean isChatOn) {
		this.isChatOn = isChatOn;
	}
	
	public void setIsMagicOn(boolean isMagicOn) {
		this.isMagicOn = isMagicOn;
	}
	
	public boolean hasStarted() {
		return this.hasStarted;
	}
	
	public boolean isMagicOn() {
		return this.isMagicOn;
	}
	
	public boolean isChatOn() {
		return this.isChatOn;
	}
	
}
