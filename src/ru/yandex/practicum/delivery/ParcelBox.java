package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel>{
    ArrayList<T> parcelBoxList;
    int maxWeight;

    public ParcelBox(ArrayList<T> parcelBoxList, int maxWeight) {
        this.parcelBoxList = parcelBoxList;
        this.maxWeight = maxWeight;
    }

    public void addParcel (T newParcel){
        int currentWeight = 0;
        for(T parcel : parcelBoxList){
            currentWeight += parcel.weight;
        }

        if((currentWeight + newParcel.weight)<=maxWeight){
            parcelBoxList.add(newParcel);
        } else {
            System.out.println("Посылке не добавлена. При добавлении будет превышен максимальный вес коробки");
        }

    }

    public void getAllParcels(){
        for(T parcel : parcelBoxList){
            System.out.println(parcel.description);
        }
    }
}
