package com.seveneleven.employeeapi.service;

import com.seveneleven.employeeapi.dto.LocationDTO;
import com.seveneleven.employeeapi.model.Location;
import com.seveneleven.employeeapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    
    @Autowired
    private LocationRepository locationRepository;
    
    public LocationDTO createLocation(LocationDTO locationDTO) {
        Location location = new Location();
        location.setCity(locationDTO.getCity());
        location.setState(locationDTO.getState());
        location.setCountry(locationDTO.getCountry());
        Location savedLocation = locationRepository.save(location);
        return convertToDTO(savedLocation);
    }
    
    public LocationDTO getLocationById(Long locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        return convertToDTO(location);
    }
    
    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public LocationDTO updateLocation(Long locationId, LocationDTO locationDTO) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        location.setCity(locationDTO.getCity());
        location.setState(locationDTO.getState());
        location.setCountry(locationDTO.getCountry());
        Location updatedLocation = locationRepository.save(location);
        return convertToDTO(updatedLocation);
    }
    
    public void deleteLocation(Long locationId) {
        locationRepository.deleteById(locationId);
    }
    
    private LocationDTO convertToDTO(Location location) {
        LocationDTO dto = new LocationDTO();
        dto.setLocationId(location.getLocationId());
        dto.setCity(location.getCity());
        dto.setState(location.getState());
        dto.setCountry(location.getCountry());
        return dto;
    }
}
