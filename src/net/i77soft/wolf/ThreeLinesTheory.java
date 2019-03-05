package net.i77soft.wolf;

import java.util.ArrayList;

class CheckResult {
    public int passeds = 0;
    public int invalids = 0;
    public int errors = 0;

    public CheckResult(int passeds, int invalids, int errors) {
        this.passeds = passeds;
        this.invalids = invalids;
        this.errors = errors;
    }
}

public class ThreeLinesTheory extends WereWolfKill implements Runnable {

    public ThreeLinesTheory() {
        this.init();
    }

    public ThreeLinesTheory(int wolfs, int gods, int villagers) {
        this.setInfo(wolfs, gods, villagers, 0);
    }

    public ThreeLinesTheory(int wolfs, int gods, int villagers, int others) {
        this.setInfo(wolfs, gods, villagers, others);
    }

    void displayAnswer(int no, int[] numbers) {
        int wolfs = getWolfs();
        if (wolfs > numbers.length)
            wolfs = numbers.length;

        String answer = "";
        for (int i = 0; i < wolfs; i++) {
            if (i < wolfs - 1)
                answer += String.format("%d, ", numbers[i] + 1);
            else
                answer += String.format("%d", numbers[i] + 1);
        }
        System.out.printf("No. %d, answer = %s\n", no + 1, answer);
    }

    void displayAllAnswers(ArrayList<int[]> answers) {
        System.out.println();

        int no = 0;
        for (int[] numbers : answers) {
            displayAnswer(no, numbers);
            no++;
        }

        System.out.println();
    }

    public boolean verifyThreeLines(int[] lines) {
        int len = lines.length;
        int[] wolf_nums = new int[len];

        for (int i = 0; i < len; i++) {
            wolf_nums[i] = 0;
        }

        for (int i = 0; i < len; i++) {
            int line1 = (i % len);
            int line2 = ((i + 1) % len);
            int line3 = ((i + 2) % len);
            wolf_nums[i] = lines[line1] + lines[line2] + lines[line3];
        }

        for (int i = 0; i < len; i++) {
            if (wolf_nums[i] >= 3) {
                return true;
            }
        }
        return false;
    }

    public CheckResult checkThreeLines(ArrayList<int[]> answers) {
        int passeds = 0;
        int invalids = 0;
        int errors = 0;
        int count = 0;

        int wolfs = getWolfs();
        int maxLines = getMaxLines();
        for (int[] numbers : answers) {
            this.reset();
            // Fill the wolf positions
            for (int i = 0; i < wolfs; i++) {
                // Set arena
                int position = numbers[i];
                if (position >= 0) {
                    if (this.getArenaValue(position) == -1)
                        this.setArenaValue(position, i);

                    // Set lines
                    int line = position % maxLines;
                    int oldValue = this.getLineValue(line);
                    this.setLineValue(line, oldValue + 1);
                } else {
                    errors++;
                }
            }

            boolean isMatching = verifyThreeLines(this.getLines());
            if (!isMatching) {
                displayAnswer(invalids, numbers);
            }

            if (isMatching)
                passeds++;
            else
                invalids++;
            count++;
        }

        return new CheckResult(passeds, invalids, errors);
    }

    @Override
    public void run() {
        int wolfs = getWolfs();
        int total = getTotal();

        int C_mn = Probability.combinations(wolfs, total);
        ArrayList<int[]> combins = Probability.combinAll(wolfs, total);
        int totalCombins = combins.size();
        assert C_mn != totalCombins;

        // Display all combinations
        // displayAllAnswers(combins);

        CheckResult result = checkThreeLines(combins);
        System.out.println();
        System.out.println("passeds = " + result.passeds +
                ", invalids = " + result.invalids +
                ", errors = " + result.errors + ", total = " + C_mn +
                ", totalCombins = " + totalCombins);
    }
}
