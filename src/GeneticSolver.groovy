import groovy.transform.CompileStatic


@CompileStatic
class GeneticSolver {


    public static Individual solve(Closure problem,  int populationCount, int dnaCount) {


        List<Individual> individuals = generateRandomPopulation(populationCount,dnaCount)

        int turn
        double validity = 0;

        evaluation(problem, individuals)

        while (validity < 0.95) {
            selection(individuals, populationCount)
            crossover(individuals, populationCount)
            mutation(individuals)
            evaluation(problem, individuals)

            validity = (((double)individuals.sum { Individual individual -> individual.evaluation }) / individuals.size())

            println("turn: ${turn++} ${validity}")
        }
        println("done")

        return individuals.max { Individual individual -> individual.evaluation }
    }


    private static void evaluation(Closure problem, ArrayList<Individual> individuals) {
        individuals.each {
            it.evaluate(problem)
        }
    }

    private static void selection(ArrayList<Individual> individuals, int populationCount) {

        individuals.sort { it.evaluation }

        ((int) populationCount / 2).times {
            individuals.remove(0)
        }

    }

    private static void crossover(ArrayList<Individual> individuals, int populationCount) {

        Collections.shuffle(individuals)
        for (int i = 0; i < populationCount / 2; i++) {
            individuals.add individuals.get(i).generateChild(individuals.get(i + 1))
        }

    }

    private static void mutation(ArrayList<Individual> individuals) {
        individuals.each {
            it.mutate()
        }
    }


    private static ArrayList<Individual> generateRandomPopulation(int populationCount, int dnaSize) {

        ArrayList<Individual> results = new ArrayList<>()

        populationCount.times {
            results.add(new Individual(dnaSize))
        }

        results.each {
            it.randomize()
        }
        return results
    }
}
