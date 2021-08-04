package com.example.demo.helper;

public class OrderTotalUpdateWrapper {
    private Double total;
    private Double volume;
    private VolumeUnit volumeUnit;

    public OrderTotalUpdateWrapper() {
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public VolumeUnit getVolumeUnit() {
        return volumeUnit;
    }

    public void setVolumeUnit(VolumeUnit volumeUnit) {
        this.volumeUnit = volumeUnit;
    }
}
