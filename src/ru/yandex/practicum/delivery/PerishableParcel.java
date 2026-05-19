package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel{
    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    public int getValueBaseCost(){
        return PERISHABLE_PARCEL_BASE_COST;
    }

    public boolean isExpired(int currentDay){
        return (sendDay + timeToLive) <= currentDay;
    }
}
