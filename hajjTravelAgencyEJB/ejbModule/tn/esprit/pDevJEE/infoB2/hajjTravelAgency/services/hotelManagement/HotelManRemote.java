package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.hotelManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Hotel;

@Remote
public interface HotelManRemote {
	public void addHotel(Hotel hotel);
	public void removeHotel(Hotel hotel);
	public void updateHotel(Hotel hotel);
	public List<Hotel> getAllHotels();
	public Hotel findHotelById(int idHotel);
	public List<Hotel> getHotelsByLocation(String location);
	

}
