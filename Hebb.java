public class Hebb{
    public static void main(String[] args)
    {
    // устанавливаем серию паттернов - входов, с повторами для наилучшего обучения системы
        int[][] pattern =new int[][]{{1,1,0,0,1},
                {1,1,0,0,1},
                {1,1,1,0,0},
                {1,1,0,0,1},
                {1,1,0,0,1},
                {1,1,1,0,0},
                {1,1,1,1,1}};
        Net net = new Net(5, pattern);
        net.training();
        net.testing(new int[]{1,1,0,0,0});
        net.testing(new int[]{1,0,0,0,0});
        net.testing(new int[]{0,0,1,0,0});
    }
}

class Net {
    // коэффициент домножения для весов
    double n = 0.1;
    int[][] pattern;
    Neuron[] neurons;

    public Net(int noOfNeurons, int[][] pattern){
        this.pattern = pattern;
    // массив нейронов
        neurons = new Neuron[noOfNeurons];
        for (int i = 0; i < neurons.length; i++) {
            neurons[i] = new Neuron();
        }
    }

    public void training(){
        // проходим циклом по входам
        for (int[] enters : pattern) {
            double output = getNeuralNetOutput(neurons, enters);
            // обновляем веса во всех нейронах
            for (int i = 0; i < neurons.length; i++) {
                neurons[i].updateWeight(output * n * enters[i]);
            }
        }
    }

    public double getNeuralNetOutput(Neuron[] neurons, int[] enters)
    {
        // получаем все результаты и складываем их вместе
        double output = 0;
        for (int i = 0; i < neurons.length; i++)
        {
            output += neurons[i].getOutput(enters[i]);
        }
        return output;
    }

    public void testing (int[] enters){
        double result = getNeuralNetOutput(neurons, enters);
        if (result >= 10) {
            System.out.println("Высокая обучаемость, знакомый паттерн " + (int) result);
        } else if (result >= 5) {
            System.out.println("Средняя обучаемость, смешанный паттерн " + (int) result);
        } else {
            System.out.println("Низкая обучаемость, незнакомый паттерн " + (int) result);
           
        } System.out.println("Неизвестная обучаемость");
        System.out.println("commit3");
        System.out.println("делаем несколько коммитов");
    }

    static class Neuron
    {
        double w;

        public Neuron() {
            w = 1;
        }

        public double getOutput(int x) {
            return x * w;
        }

        public void updateWeight(double update)
        {
            w += update;
        }
    }
}
