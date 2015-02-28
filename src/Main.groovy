

def closure = {Individual individual->

    float count = 0
    individual.dna.each {

        if (it)
            count++
    }
    evaluation = count / individual.dnaCount

}

Individual individual = GeneticSolver.solve(closure, 100, 100)

individual.dna.each{
    println it
}