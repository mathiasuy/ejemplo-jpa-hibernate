package com.player.model;

public class FactoryModel {
	
	public IGrabable getIGrabables() {
		return new CGrabable();
	}
	
}
