package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WorkWithParcel {

        StandardParcel standardParcel = new StandardParcel("standardParcel", 600, "Adress_1", 6);
        FragileParcel fragileParcel = new FragileParcel("fragileParcel", 500, "Adress_2", 8);
        PerishableParcel perishableParcel = new PerishableParcel("perishableParcel", 8, "Adress_3", 20, 3);

        ArrayList<StandardParcel> standardParcelList = new ArrayList<>();
        ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(standardParcelList,1200);


    @Test
    public void checkCalculateDeliveryCostForStandardParcel() {
        assertEquals(1200,standardParcel.calculateDeliveryCost());
    }

    @Test
    public void checkCalculateDeliveryCostForFragileParcel() {
        assertEquals(2000,fragileParcel.calculateDeliveryCost());
    }

    @Test
    public void checkCalculateDeliveryCostForPerishableParcel() {
        assertEquals(24,perishableParcel.calculateDeliveryCost());
    }

    @Test
    public void checkIsExpired() {
        assertFalse(perishableParcel.isExpired(21));
    }


    @Test
    public void addNewParcelWithNormWeight() {

        standardParcelBox.addParcel(standardParcel);

        assertEquals("standardParcel",standardParcelBox.parcelBoxList.get(standardParcelBox.parcelBoxList.size()-1).description);
    }

    @Test
    public void addNewParcelWithNormWeight_2() {

        standardParcelBox.addParcel(standardParcel);

        StandardParcel standardParcel_2 = new StandardParcel("standardParcel_2", 600, "Adress_1", 6);

        standardParcelBox.addParcel(standardParcel_2);

        assertEquals("standardParcel_2",standardParcelBox.parcelBoxList.get(standardParcelBox.parcelBoxList.size()-1).description);
    }

    @Test
    public void addNewParcelWithOverweight() {
        standardParcelBox.addParcel(standardParcel);

        StandardParcel standardParcel_3 = new StandardParcel("standardParcel_3", 1000, "Adress_1", 6);

        standardParcelBox.addParcel(standardParcel_3);


        assertNotEquals("standardParcel_3",standardParcelBox.parcelBoxList.get(standardParcelBox.parcelBoxList.size()-1).description);
    }


}

