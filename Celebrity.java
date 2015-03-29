package com.facebook.eva.algorithm;

/**
 * Created by Eva on 15.2.2015..
 */
public class Celebrity {

    static class Person {

        private String name;
        private boolean isCelebrity = false;

        public Person(String name, boolean isCelebrity) {
            this.name = name;
            this.isCelebrity = isCelebrity;
        }

        public boolean isCelebrity() {
            return isCelebrity;
        }

        public boolean knows(Person person) {
            if (isCelebrity) {
                return false;
            } else if (person.isCelebrity()) {
                return true;
            } else {
                return getRandomBoolean();
            }
        }

    }

    public static boolean getRandomBoolean() {
        double rand = Math.random();
        return rand >= 0.5f;
    }

    public static Person[] getTestPeople() {
        final Person person = new Person("Person", false);
        final Person celebrity = new Person("Celebrity", true);
        return new Person[]{person, person, person, celebrity, person};

    }

    public static int indexOfCelebrity(Person[] people) {
        if (people == null || people.length == 0) {
            return -1;
        }
        if (people.length == 1) {
            return 0;
        }

        int celebIdx = -1;
        int idx1 = 0, idx2 = 1;
        while (idx2 < people.length) {
            Person p1 = people[idx1];
            Person p2 = people[idx2];
            if (p1.knows(p2)) {
                celebIdx = idx2;
                idx1 = idx2;
            } else {
                celebIdx = idx1;
            }
            idx2++;
        }

        if (celebIdx == -1) {
            return -1;
        }

        final Person celebrity = people[celebIdx];
        for (int i = 0; i < people.length; i++) {
            if (i != celebIdx) {
                Person person = people[i];
                if (celebrity.knows(person) || !person.knows(celebrity)) {
                    return -1;
                }
            }
        }
        return celebIdx;
    }
}
