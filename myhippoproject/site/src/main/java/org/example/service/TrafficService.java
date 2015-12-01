package org.example.service;

import java.util.List;

import org.example.model.Camera;


public interface TrafficService {

	public List<Camera> getTraficCameraInfo();
	public List<Camera> getTraficCameraInfo(String roads);
	public void updateTraficCameraInfo(List<Camera> listCamera);
}
