package org.example.rapidracewithoutdatabase.model;

import java.util.ArrayList;
import java.util.List;

public class HorseController {
    private final List<Horse> horses;

    public HorseController() {
        horses = new ArrayList<>();
    }

    public List<Horse> getHorses() {
        return horses;
    }
    public void addHorse(Horse horse) {horses.add(horse);}
    public void deleteHorse(Horse horse) {
        horses.remove(horse);
    }

    public void sortHorsesById() {
        mergeSort(horses, 0, horses.size() - 1);
    }

    private void mergeSort(List<Horse> list, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(list, left, middle);
            mergeSort(list, middle + 1, right);
            merge(list, left, middle, right);
        }
    }

    private void merge(List<Horse> list, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        List<Horse> leftList = new ArrayList<>();
        List<Horse> rightList = new ArrayList<>();

        for (int i = 0; i < n1; ++i)
            leftList.add(list.get(left + i));
        for (int j = 0; j < n2; ++j)
            rightList.add(list.get(middle + 1 + j));

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftList.get(i).getId() <= rightList.get(j).getId()) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    public void updateHorse(Horse updatedHorse) {
        // Find the horse with the same ID as the updatedHorse
        for (Horse horse : horses) {
            if (horse.getId() == updatedHorse.getId()) {
                // Update the horse's attributes with the new values
                horse.setName(updatedHorse.getName());
                horse.setAge(updatedHorse.getAge());
                horse.setBreed(updatedHorse.getBreed());
                horse.setJockeyName(updatedHorse.getJockeyName());
                horse.setRacesParticipated(updatedHorse.getRacesParticipated());
                horse.setRacesWon(updatedHorse.getRacesWon());
                horse.setGroup(updatedHorse.getGroup());
                horse.setImage(updatedHorse.getImage());
                return;
            }
        }
    }
}
