package com.player.model;

public class FactoryModel {
	
	public IGrabable getCGrabable() {
		return new CGrabable();
	}
	
}
