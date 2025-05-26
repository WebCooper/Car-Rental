package com.company.carrent.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@JmixEntity
@Table(name = "VEHICLE", indexes = {
        @Index(name = "IDX_VEHICLE_BOOKING", columnList = "")
})
@Entity
public class Vehicle {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "REGISTER_NO", nullable = false)
    @NotNull
    private String registerNo;

    @Column(name = "BRAND", nullable = false)
    @NotNull
    private String brand;

    @Column(name = "MODEL", nullable = false)
    @NotNull
    private String model;

    @Column(name = "SEATS", nullable = false)
    @NotNull
    private Integer seats;

    @Column(name = "PRICE_PER_DAY", nullable = false, precision = 19, scale = 2)
    @NotNull
    private BigDecimal pricePerDay;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "STATUS", nullable = false)
    @NotNull
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Booking getBooking() {
        return booking;
    }

    public VehicleStatus getStatus() {
        return status == null ? null : VehicleStatus.fromId(status);
    }

    public void setStatus(VehicleStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    public String getInstanceName() {
        return String.format("%s-%s %s", brand, model, registerNo);
    }

}