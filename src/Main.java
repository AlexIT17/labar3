class ChickenOrEgg {

    static class Speaker extends Thread {
        private final String word;
        private final int times;

        Speaker(String word, int times) {
            this.word = word;
            this.times = times;
        }

        @Override
        public void run() {
            for (int i = 0; i < times; i++) {
                System.out.println(word);
                if (i < times - 1) {
                    try {
                        Thread.sleep((long) (Math.random() * 100));
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Speaker chicken = new Speaker("Курица", 5);
        Speaker egg = new Speaker("Яйцо", 5);

        chicken.start();
        egg.start();

        while (chicken.isAlive() && egg.isAlive()) {
            Thread.sleep(1);
        }

        String winner = chicken.isAlive() ? "Курица" : "Яйцо";

        chicken.join();
        egg.join();

        System.out.println("Спор окончен. Победила " + winner);
    }
}