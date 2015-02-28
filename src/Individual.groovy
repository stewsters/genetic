
public class Individual {

    private int dnaCount
    public float evaluation = 0.0
    public boolean[] dna = new boolean[dnaCount]

    public Individual(int dnaCount) {
        this.dnaCount = dnaCount
    }

    def evaluate(Closure problem) {
        evaluation = problem(this)
    }

    def randomize() {
        Random random = new Random()

        for (int x = 0; x < dnaCount; x++) {
            dna[x] = random.nextBoolean()
        }
    }


    def Individual generateChild(Individual other) {
        Random random = new Random()

        def child = new Individual(dnaCount)

        dnaCount.times {
            child.dna[it] = random.nextBoolean() ? dna[it] : other.dna[it]
        }

        return child
    }

    def mutate() {
        Random random = new Random()

        int times = random.nextInt(3);

        times.each {
            int index = random.nextInt(dnaCount)
            dna[index] = !dna[index]
        }
    }
}
