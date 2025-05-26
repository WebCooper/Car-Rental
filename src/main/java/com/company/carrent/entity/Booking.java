package com.company.carrent.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "BOOKING", indexes = {
        @Index(name = "IDX_BOOKING_CUSTOMER", columnList = "CUSTOMER_ID"),
        @Index(name = "IDX_BOOKING_CREATED_BY", columnList = "CREATED_BY_ID"),
        @Index(name = "IDX_BOOKING_STAFF", columnList = "STAFF_ID")
})
@Entity
public class Booking {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "START_DATE", nullable = false)
    @NotNull
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    @NotNull
    private LocalDateTime endDate;

    @Column(name = "TOTAL_PRICE", nullable = false, precision = 19, scale = 2)
    @NotNull
    private BigDecimal totalPrice;

    @Column(name = "BOOKING_STATUS", nullable = false)
    @NotNull
    private String bookingStatus;

    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User customer;

    @JoinColumn(name = "CREATED_BY_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User createdBy;

    @JoinColumn(name = "STAFF_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User staff;

    @JoinColumn(name = "VEHICLE_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getStaff() {
        return staff;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus == null ? null : BookingStatus.fromId(bookingStatus);
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus == null ? null : bookingStatus.getId();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}