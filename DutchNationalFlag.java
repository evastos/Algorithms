package com.facebook.eva.algorithm;

/**
 * Created by Eva on 15.2.2015..
 */
public class DutchNationalFlag {

    /**
     * Given an array of patient files, sort files by importance.
     * Importance values: low, medium and high.
     * http://www.careercup.com/question?id=5080405046722560
     */

    enum Importance {
        HIGH, MEDIUM, LOW
    }

    public static File[] createTestFiles() {
        final File high = new File(Importance.HIGH);
        final File med = new File(Importance.MEDIUM);
        final File low = new File(Importance.LOW);

        return new File[]{high, low, low, med, high, low};
    }

    static class File {

        public File(Importance importance) {
            this.importance = importance;
        }

        private Importance importance;

        public Importance getImportance() {
            return importance;
        }

        @Override
        public String toString() {
            if (importance != null) {
                return importance.name().toLowerCase();
            } else {
                return "";
            }
        }
    }

    public static File[] sortPatientFilesByImportance(File[] files) {

        if (files == null || files.length < 1) {
            return new File[]{};
        }

        int[] count = new int[]{
                0, 0, 0
        };

        for (int i = 0; i < files.length; i++) {
            count[files[i].getImportance().ordinal()]++;
        }

        final int high = 0;
        final int medium = count[Importance.HIGH.ordinal()];
        final int low = count[Importance.HIGH.ordinal()] + count[Importance.MEDIUM.ordinal()];

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            Importance targetImportance;
            int searchIdx;

            if (i >= high && i < medium) {
                targetImportance = Importance.HIGH;
                searchIdx = medium;
            } else if (i >= medium && i < low) {
                targetImportance = Importance.MEDIUM;
                searchIdx = low;
            } else {
                targetImportance = Importance.LOW;
                searchIdx = i + 1;
            }

            if (file.getImportance() != targetImportance) {
                int swapIdx = searchImportanceIndex(files, searchIdx,
                        targetImportance);
                if (swapIdx != -1) {
                    swap(files, i, swapIdx);
                }
            }

        }
        return files;
    }

    private static int searchImportanceIndex(File[] files, int begin,
                                             Importance importance) {

        if (begin > files.length - 1 || begin < 0) {
            return -1;
        }
        for (int i = begin; i <= files.length - 1; i++) {
            if (files[i].getImportance() == importance) {
                return i;
            }
        }
        return -1;
    }

    private static void swap(File[] files, int i, int j) {
        File temp = files[i];
        files[i] = files[j];
        files[j] = temp;
    }

}


