package net.i77soft.wolf;

public abstract class WereWolfKill {
    private int wolfs = 4;
    private int gods = 4;
    private int villagers = 4;
    private int others = 0;
    private int total = 12;
    private int maxLines = (total + 1) / 2;
    private int[] arena = null;
    private int[] lines = null;

    public WereWolfKill() {
        this.init();
    }

    public WereWolfKill(int wolfs, int gods, int villagers) {
        this.setInfo(wolfs, gods, villagers, 0);
    }

    public WereWolfKill(int wolfs, int gods, int villagers, int others) {
        this.setInfo(wolfs, gods, villagers, others);
    }

    public int getWolfs() {
        return wolfs;
    }

    public void setWolfs(int wolfs) {
        this.wolfs = wolfs;
    }

    public int getGods() {
        return gods;
    }

    public void setGods(int gods) {
        this.gods = gods;
    }

    public int getVillagers() {
        return villagers;
    }

    public void setVillagers(int villagers) {
        this.villagers = villagers;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    private void calcTotal() {
        this.total = this.wolfs + this.gods + this.villagers + this.others;
        this.maxLines = (this.total + 1) / 2;
    }

    public int getTotal() {
        return this.total;
    }

    public int getMaxLines() {
        return this.maxLines;
    }

    public void setInfo(int wolfs, int gods, int villagers, int others) {
        this.setWolfs(wolfs);
        this.setGods(gods);
        this.setVillagers(villagers);
        this.setOthers(others);
        this.calcTotal();
        this.init();
    }

    public int[] getArena() {
        return this.arena;
    }

    public void setArena(int arena[]) {
        this.arena = arena;
    }

    public int getArenaValue(int index) {
        return this.arena[index];
    }

    public void setArenaValue(int index, int value) {
        this.arena[index] = value;
    }

    public int[] getLines() {
        return this.lines;
    }

    public void setLines(int lines[]) {
        this.lines = lines;
    }

    public int getLineValue(int index) {
        return this.lines[index];
    }

    public void setLineValue(int index, int value) {
        this.lines[index] = value;
    }

    public void init() {
        this.arena = new int[total];
        this.lines = new int[maxLines];
        clear();
    }

    public void reset() {
        clear();
    }

    public void clear() {
        int total = getTotal();
        for (int i = 0; i < total; i++) {
            this.arena[i] = -1;
        }

        int maxLines = getMaxLines();
        for (int i = 0; i < maxLines; i++) {
            this.lines[i] = 0;
        }
    }
}
